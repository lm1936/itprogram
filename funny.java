import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 接口定义用户操作
interface IUser {
    void updateContactInfo(String email, String phoneNumber);
}

class User implements IUser {
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

    public boolean authenticate(String username, String password) {
        return this.username.equals(username) && this.password.equals(password);
    }

    @Override
    public void updateContactInfo(String email, String phoneNumber) {
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "Username: " + username + ", Email: " + email + ", Phone: " + phoneNumber;
    }
}

// 用户管理
class UserManager {
    private Map<String, User> userMap = new HashMap<>();

    public void registerUser(String username, String password, String email, String phoneNumber) {
        if (!userMap.containsKey(username)) {
            userMap.put(username, new User(username, password, email, phoneNumber));
        }
    }

    public User loginUser(String username, String password) {
        User user = userMap.get(username);
        if (user != null && user.authenticate(username, password)) {
            return user;
        }
        return null;
    }
}

// 充电服务
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

// 预约管理
class AppointmentManager {
    private Map<User, List<ChargingService>> appointments = new HashMap<>();

    public void bookService(User user, ChargingService service) {
        appointments.computeIfAbsent(user, k -> new ArrayList<>()).add(service);
    }

    public List<ChargingService> getUserAppointments(User user) {
        return appointments.getOrDefault(user, new ArrayList<>());
    }
}

// 支付系统
class PaymentSystem {
    public double calculateCharge(ChargingService service) {
        // 假设固定费用示例
        return 75.0; // 固定费用
    }

    public void processPayment(User user, double amount, String paymentMethod) {
        System.out.println("Processed payment of " + amount + " for user " + user.toString() + " using " + paymentMethod);
    }
}

// 订单管理
class OrderManagement {
    private Map<User, List<ChargingService>> orders = new HashMap<>();

    public void addOrder(User user, ChargingService service) {
        orders.computeIfAbsent(user, k -> new ArrayList<>()).add(service);
    }

    public List<ChargingService> getUserOrders(User user) {
        return orders.getOrDefault(user, new ArrayList<>());
    }

    public void recordFeedback(User user, String feedback) {
        System.out.println("Feedback from user " + user.toString() + ": " + feedback);
    }
}

// 充电监控
class ChargingMonitor {
    public void monitor(ChargingService service) {
        System.out.println("Monitoring charging for: " + service.toString());
    }

    public void notifyCompletion() {
        System.out.println("Charging completed!");
    }
}

// 测试主程序
public class ChargingRobotApp {
    public static void main(String[] args) {
        UserManager userManager = new UserManager();
        userManager.registerUser("bob", "securepass", "bob@example.com", "987654321");
        User user = userManager.loginUser("bob", "securepass");

        if (user != null) {
            System.out.println("User logged in: " + user.toString());

            ChargingService service = new ChargingService("Type-B", "Location-2", LocalDateTime.now().plusHours(3));

            AppointmentManager appointmentManager = new AppointmentManager();
            appointmentManager.bookService(user, service);

            PaymentSystem paymentSystem = new PaymentSystem();
            double fee = paymentSystem.calculateCharge(service);
            paymentSystem.processPayment(user, fee, "Online Payment");

            OrderManagement orderManagement = new OrderManagement();
            orderManagement.addOrder(user, service);

            ChargingMonitor monitor = new ChargingMonitor();
            monitor.monitor(service);

            // 充电完成
            monitor.notifyCompletion();

            orderManagement.recordFeedback(user, "很满意，服务周到！");
        } else {
            System.out.println("Login failed.");
        }
    }
}
