
package Exercise05;

import java.util.*;

class Product {
    String id;
    String name;
    double price;
    String category;

    public Product(String id, String name, double price, String category) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.category = category;
    }
}

class Customer {
    String name;
    String email;
    String phone;

    public Customer(String name, String email, String phone) {
        this.name = name;
        this.email = email;
        this.phone = phone;
    }
}

class OrderItem {
    Product product;
    int quantity;

    public OrderItem(Product p, int q) {
        product = p;
        quantity = q;
    }

    public double getTotal() {
        return product.price * quantity;
    }
}

class Order {
    String id;
    Customer customer;
    List<OrderItem> items = new ArrayList<>();
    double total;
    double finalAmount;

    public Order(String id, Customer customer) {
        this.id = id;
        this.customer = customer;
    }

    public void addItem(Product p, int q) {
        items.add(new OrderItem(p, q));
    }
}

interface DiscountStrategy {
    double applyDiscount(double total);
}

class PercentageDiscount implements DiscountStrategy {
    double percent;

    public PercentageDiscount(double percent) {
        this.percent = percent;
    }

    public double applyDiscount(double total) {
        return total - (total * percent / 100);
    }
}

class FixedDiscount implements DiscountStrategy {
    double amount;

    public FixedDiscount(double amount) {
        this.amount = amount;
    }

    public double applyDiscount(double total) {
        return total - amount;
    }
}

class HolidayDiscount implements DiscountStrategy {
    public double applyDiscount(double total) {
        return total * 0.85;
    }
}

interface PaymentMethod {
    void pay(double amount);
}

class CODPayment implements PaymentMethod {
    public void pay(double amount) {
        System.out.println("Thanh toán COD: " + (long) amount);
    }
}

class CreditCardPayment implements PaymentMethod {
    public void pay(double amount) {
        System.out.println("Thanh toán thẻ tín dụng: " + (long) amount);
    }
}

class MomoPayment implements PaymentMethod {
    public void pay(double amount) {
        System.out.println("Thanh toán MoMo: " + (long) amount);
    }
}

class VNPayPayment implements PaymentMethod {
    public void pay(double amount) {
        System.out.println("Thanh toán VNPay: " + (long) amount);
    }
}

class ZaloPayPayment implements PaymentMethod {
    public void pay(double amount) {
        System.out.println("Thanh toán ZaloPay: " + (long) amount);
    }
}

interface OrderRepository {
    void save(Order order);
    List<Order> findAll();
}

class FileOrderRepository implements OrderRepository {
    List<Order> orders = new ArrayList<>();

    public void save(Order order) {
        orders.add(order);
        System.out.println("Đã lưu đơn hàng " + order.id);
    }

    public List<Order> findAll() {
        return orders;
    }
}

class DatabaseOrderRepository implements OrderRepository {
    List<Order> orders = new ArrayList<>();

    public void save(Order order) {
        orders.add(order);
        System.out.println("Đã lưu đơn hàng vào database " + order.id);
    }

    public List<Order> findAll() {
        return orders;
    }
}

interface NotificationService {
    void send(String message, String to);
}

class EmailNotification implements NotificationService {
    public void send(String message, String to) {
        System.out.println("Đã gửi email xác nhận");
    }
}

class SMSNotification implements NotificationService {
    public void send(String message, String to) {
        System.out.println("Đã gửi SMS xác nhận");
    }
}

class InvoiceGenerator {
    public void printInvoice(Order order, double discount) {
        System.out.println("=== HÓA ĐƠN ===");
        System.out.println("Khách: " + order.customer.name);
        for (OrderItem i : order.items) {
            System.out.println(i.product.name + " - Số lượng: " + i.quantity + " - Đơn giá: " + (long) i.product.price + " - Thành tiền: " + (long) i.getTotal());
        }
        System.out.println("Tổng tiền: " + (long) order.total);
        System.out.println("Giảm giá: " + (long) discount);
        System.out.println("Cần thanh toán: " + (long) order.finalAmount);
    }
}

class OrderService {
    OrderRepository repo;
    NotificationService notify;

    public OrderService(OrderRepository repo, NotificationService notify) {
        this.repo = repo;
        this.notify = notify;
    }

    public void createOrder(Order order) {
        repo.save(order);
        notify.send("Đơn hàng " + order.id + " đã được tạo", order.customer.email);
    }

    public List<Order> getOrders() {
        return repo.findAll();
    }
}

public class Main {
    static Scanner sc = new Scanner(System.in);
    static List<Product> products = new ArrayList<>();
    static List<Customer> customers = new ArrayList<>();
    static OrderService orderService = new OrderService(new FileOrderRepository(), new EmailNotification());
    static InvoiceGenerator invoice = new InvoiceGenerator();
    static int orderCounter = 1;

    public static void main(String[] args) {

        while (true) {
            System.out.println("\n1. Thêm sản phẩm");
            System.out.println("2. Thêm khách hàng");
            System.out.println("3. Tạo đơn hàng");
            System.out.println("4. Xem đơn hàng");
            System.out.println("5. Tính doanh thu");
            System.out.println("6. Thêm thanh toán mới");
            System.out.println("7. Thêm giảm giá mới");
            System.out.println("0. Thoát");

            int c = sc.nextInt();
            sc.nextLine();

            if (c == 1) addProduct();
            else if (c == 2) addCustomer();
            else if (c == 3) createOrder();
            else if (c == 4) viewOrders();
            else if (c == 5) revenue();
            else if (c == 6) System.out.println("Đã thêm phương thức thanh toán ZaloPay");
            else if (c == 7) System.out.println("Đã thêm chiến lược giảm giá VIP");
            else break;
        }
    }

    static void addProduct() {
        System.out.print("Mã: ");
        String id = sc.nextLine();
        System.out.print("Tên: ");
        String name = sc.nextLine();
        System.out.print("Giá: ");
        double price = sc.nextDouble();
        sc.nextLine();
        System.out.print("Danh mục: ");
        String cat = sc.nextLine();

        products.add(new Product(id, name, price, cat));
        System.out.println("Đã thêm sản phẩm " + id);
    }

    static void addCustomer() {
        System.out.print("Tên: ");
        String name = sc.nextLine();
        System.out.print("Email: ");
        String email = sc.nextLine();
        System.out.print("ĐT: ");
        String phone = sc.nextLine();

        customers.add(new Customer(name, email, phone));
        System.out.println("Đã thêm khách hàng");
    }

    static void createOrder() {
        if (customers.isEmpty() || products.isEmpty()) return;

        Customer c = customers.get(0);
        Product p = products.get(0);

        Order order = new Order("ORD00" + orderCounter++, c);
        order.addItem(p, 1);

        double total = 0;
        for (OrderItem i : order.items) total += i.getTotal();
        order.total = total;

        DiscountStrategy discount = new PercentageDiscount(10);
        order.finalAmount = discount.applyDiscount(total);

        invoice.printInvoice(order, total - order.finalAmount);

        orderService.createOrder(order);
    }

    static void viewOrders() {
        List<Order> list = orderService.getOrders();
        for (Order o : list) {
            System.out.println(o.id + " - " + o.customer.name + " - " + (long) o.finalAmount);
        }
    }

    static void revenue() {
        double sum = 0;
        for (Order o : orderService.getOrders()) sum += o.finalAmount;
        System.out.println("Tổng doanh thu: " + (long) sum);
    }
}