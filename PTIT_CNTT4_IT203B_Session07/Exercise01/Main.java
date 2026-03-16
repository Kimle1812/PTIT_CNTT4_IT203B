package Exercise01;

import java.util.*;

class Product {
    String id;
    String name;
    double price;

    public Product(String id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }
}

class Customer {
    String name;
    String email;
    String address;

    public Customer(String name, String email, String address) {
        this.name = name;
        this.email = email;
        this.address = address;
    }
}

class OrderItem {
    Product product;
    int quantity;

    public OrderItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }
}

class Order {
    String orderId;
    Customer customer;
    List<OrderItem> items;
    double total;

    public Order(String orderId, Customer customer) {
        this.orderId = orderId;
        this.customer = customer;
        this.items = new ArrayList<>();
    }

    public void addItem(Product product, int quantity) {
        items.add(new OrderItem(product, quantity));
    }
}

class OrderCalculator {
    public double calculateTotal(Order order) {
        double total = 0;
        for (OrderItem item : order.items) {
            total += item.product.price * item.quantity;
        }
        return total;
    }
}

class OrderRepository {
    List<Order> orders = new ArrayList<>();

    public void save(Order order) {
        orders.add(order);
        System.out.println("Đã lưu đơn hàng " + order.orderId);
    }
}

class EmailService {
    public void sendEmail(Customer customer, String message) {
        System.out.println("Đã gửi email đến " + customer.email + ": " + message);
    }
}

public class Main {
    public static void main(String[] args) {

        Product p1 = new Product("SP01", "Laptop", 15000000);
        Product p2 = new Product("SP02", "Chuột", 300000);
        System.out.println("Đã thêm sản phẩm SP01, SP02");

        Customer customer = new Customer("Nguyễn Văn A", "a@example.com", "Hà Nội");
        System.out.println("Đã thêm khách hàng");

        Order order = new Order("ORD001", customer);
        order.addItem(p1, 1);
        order.addItem(p2, 2);
        System.out.println("Đơn hàng ORD001 được tạo");

        System.out.println("Tính tổng tiền");
        OrderCalculator calculator = new OrderCalculator();
        order.total = calculator.calculateTotal(order);
        System.out.println("Tổng tiền: " + (long) order.total);

        System.out.println("Lưu đơn hàng");
        OrderRepository repo = new OrderRepository();
        repo.save(order);

        System.out.println("Gửi email xác nhận");
        EmailService emailService = new EmailService();
        emailService.sendEmail(customer, "Đơn hàng " + order.orderId + " đã được tạo");
    }
}