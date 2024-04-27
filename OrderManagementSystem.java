import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 订单类
class Order {
    private int orderId;
    private String customerName;
    private String address;
    // 其他订单信息

    public Order(int orderId, String customerName, String address) {
        this.orderId = orderId;
        this.customerName = customerName;
        this.address = address;
        // 可以在这里初始化其他订单信息
    }

    // Getter 方法
    public int getOrderId() {
        return orderId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getAddress() {
        return address;
    }
}

// 订单管理系统类
class OrderManagementSystem {
    private Map<Integer, Order> orders; // 用于存储订单信息
    private List<String> feedbacks; // 用于存储问题反馈信息

    public OrderManagementSystem() {
        this.orders = new HashMap<>();
        this.feedbacks = new ArrayList<>();
    }

    // 添加订单
    public void addOrder(int orderId, String customerName, String address) {
        Order order = new Order(orderId, customerName, address);
        orders.put(orderId, order);
    }

    // 查看订单列表
    public void displayOrders() {
        System.out.println("Order ID | Customer Name | Address");
        for (Order order : orders.values()) {
            System.out.println(order.getOrderId() + " | " + order.getCustomerName() + " | " + order.getAddress());
        }
    }

    // 查看订单详情
    public void displayOrderDetails(int orderId) {
        Order order = orders.get(orderId);
        if (order != null) {
            System.out.println("Order ID: " + order.getOrderId());
            System.out.println("Customer Name: " + order.getCustomerName());
            System.out.println("Address: " + order.getAddress());
            // 还可以打印其他订单详情信息
        } else {
            System.out.println("Order not found!");
        }
    }

    // 提交问题反馈
    public void submitFeedback(String feedback) {
        feedbacks.add(feedback);
        System.out.println("Feedback submitted successfully!");
    }

    // 显示问题反馈
    public void displayFeedbacks() {
        System.out.println("Feedbacks:");
        for (String feedback : feedbacks) {
            System.out.println(feedback);
        }
    }
}

public class Main {
    public static void main(String[] args) {
        OrderManagementSystem orderManagementSystem = new OrderManagementSystem();

        // 添加一些订单
        orderManagementSystem.addOrder(1, "John Doe", "123 Main St");
        orderManagementSystem.addOrder(2, "Jane Smith", "456 Oak St");

        // 显示订单列表
        System.out.println("Order List:");
        orderManagementSystem.displayOrders();

        // 查看订单详情
        System.out.println("\nOrder Details:");
        orderManagementSystem.displayOrderDetails(1);

        // 提交问题反馈
        orderManagementSystem.submitFeedback("The charging station was not working properly.");

        // 显示问题反馈
        System.out.println("\nFeedbacks:");
        orderManagementSystem.displayFeedbacks();
    }
}