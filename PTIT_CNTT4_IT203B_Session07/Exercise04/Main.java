
package Exercise04;

import java.util.*;

class Order {
    String id;

    public Order(String id) {
        this.id = id;
    }
}

interface OrderRepository {
    void save(Order order);
    List<Order> findAll();
}

interface NotificationService {
    void send(String message, String recipient);
}

class FileOrderRepository implements OrderRepository {
    List<Order> orders = new ArrayList<>();

    public void save(Order order) {
        orders.add(order);
        System.out.println("Lưu đơn hàng vào file: " + order.id);
    }

    public List<Order> findAll() {
        return orders;
    }
}

class DatabaseOrderRepository implements OrderRepository {
    List<Order> orders = new ArrayList<>();

    public void save(Order order) {
        orders.add(order);
        System.out.println("Lưu đơn hàng vào database: " + order.id);
    }

    public List<Order> findAll() {
        return orders;
    }
}

class EmailService implements NotificationService {
    public void send(String message, String recipient) {
        System.out.println("Gửi email: " + message);
    }
}

class SMSNotification implements NotificationService {
    public void send(String message, String recipient) {
        System.out.println("Gửi SMS: " + message);
    }
}

class OrderService {
    private OrderRepository repository;
    private NotificationService notification;

    public OrderService(OrderRepository repository, NotificationService notification) {
        this.repository = repository;
        this.notification = notification;
    }

    public void createOrder(String id, String recipient) {
        Order order = new Order(id);
        repository.save(order);
        notification.send("Đơn hàng " + id + " đã được tạo", recipient);
    }
}

public class Main {
    public static void main(String[] args) {

        System.out.println("Dùng FileOrderRepository và EmailService");
        OrderService service1 = new OrderService(new FileOrderRepository(), new EmailService());
        System.out.println("Tạo đơn hàng ORD001");
        service1.createOrder("ORD001", "a@example.com");

        System.out.println();

        System.out.println("Đổi sang DatabaseOrderRepository và SMSNotification");
        OrderService service2 = new OrderService(new DatabaseOrderRepository(), new SMSNotification());
        System.out.println("Tạo đơn hàng ORD002");
        service2.createOrder("ORD002", "0900000000");

        System.out.println();

        System.out.println("OrderService hoạt động với implementation mới");
    }
}