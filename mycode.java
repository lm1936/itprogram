// 用户类，包含用户信息和操作
class User {
    private String username;
    private String password;
    private String email;
    private String phoneNumber;

    public User(String username, String password, String email, String phoneNumber) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    // 验证用户凭证
    public boolean validateCredentials(String username, String password) {
        return this.username.equals(username) && this.password.equals(password);
    }

    @Override
    public String toString() {
        return "Username: " + username + ", Email: " + email + ", Phone: " + phoneNumber;
    }
}

// 用户管理类，负责用户注册、登录和修改信息
class UserManager {
    private List<User> users = new ArrayList<>();

    public void registerUser(String username, String password, String email, String phoneNumber) {
        User user = new User(username, password, email, phoneNumber);
        users.add(user);
    }

    public User loginUser(String username, String password) {
        for (User user : users) {
            if (user.validateCredentials(username, password)) {
                return user;
            }
        }
        return null;
    }

    public void updateUser(User user, String newEmail, String newPhoneNumber) {
        user.setEmail(newEmail);
        user.setPhoneNumber(newPhoneNumber);
    }
}

// 充电服务类，包含预约信息
class ChargingService {
    private String robotType;
    private String location;
    private LocalDateTime appointmentTime;

    public ChargingService(String robotType, String location, LocalDateTime appointmentTime) {
        this.robotType = robotType;
        this.location = location;
        this.appointmentTime = appointmentTime;
    }

    @Override
    public String toString() {
        return "Robot Type: " + robotType + ", Location: " + location + ", Time: " + appointmentTime;
    }
}

// 预约管理类，负责预约充电服务和查询预约状态
class AppointmentManager {
    private Map<User, List<ChargingService>> appointments = new HashMap<>();

    public void bookChargingService(User user, String robotType, String location, LocalDateTime appointmentTime) {
        ChargingService service = new ChargingService(robotType, location, appointmentTime);
        if (!appointments.containsKey(user)) {
            appointments.put(user, new ArrayList<>());
        }
        appointments.get(user).add(service);
    }

    public List<ChargingService> getUserAppointments(User user) {
        return appointments.getOrDefault(user, Collections.emptyList());
    }
}

// 支付管理类，负责费用计算和支付方式
class PaymentManager {
    public double calculateCost(ChargingService service) {
        // 简单费用计算，根据机器人类型和时长计算费用
        return 50.0; // 固定费用示例
    }

    public void makePayment(User user, double amount, String paymentMethod) {
        System.out.println("User " + user.toString() + " paid " + amount + " using " + paymentMethod);
    }
}

// 订单管理类，负责查看订单和反馈问题
class OrderManager {
    private Map<User, List<ChargingService>> orders = new HashMap<>();

    public void addOrder(User user, ChargingService service) {
        if (!orders.containsKey(user)) {
            orders.put(user, new ArrayList<>());
        }
        orders.get(user).add(service);
    }

    public List<ChargingService> getUserOrders(User user) {
        return orders.getOrDefault(user, Collections.emptyList());
    }

    public void feedback(User user, String feedback) {
        System.out.println("User " + user.toString() + " feedback: " + feedback);
    }
}

// 充电监控类，负责查看充电进度和状态反馈
class ChargingMonitor {
    public void monitorCharging(ChargingService service) {
        System.out.println("Monitoring charging: " + service.toString());
    }

    public void notifyChargingComplete() {
        System.out.println("Charging completed!");
    }
}

// 测试系统
public class ChargingRobotApp {
    public static void main(String[] args) {
        // 用户管理
        UserManager userManager = new UserManager();
        userManager.registerUser("alice", "password123", "alice@example.com", "123456789");
        User user = userManager.loginUser("alice", "password123");

        if (user != null) {
            System.out.println("User logged in: " + user.toString());

            // 预约充电服务
            AppointmentManager appointmentManager = new AppointmentManager();
            appointmentManager.bookChargingService(user, "Type-A", "Location-1", LocalDateTime.now().plusHours(2));

            // 支付
            PaymentManager paymentManager = new PaymentManager();
            double cost = paymentManager.calculateCost(appointmentManager.getUserAppointments(user).get(0));
            paymentManager.makePayment(user, cost, "Credit Card");

            // 订单管理
            OrderManager orderManager = new OrderManager();
            orderManager.addOrder(user, appointmentManager.getUserAppointments(user).get(0));
            System.out.println("User orders: " + orderManager.getUserOrders(user));

            // 充电监控
            ChargingMonitor chargingMonitor = new ChargingMonitor();
            chargingMonitor.monitorCharging(appointmentManager.getUserAppointments(user).get(0));
            chargingMonitor.notifyChargingComplete();

            // 反馈订单问题
            orderManager.feedback(user, "充电时间过长！");
        } else {
            System.out.println("Login failed.");
        }
    }
}
