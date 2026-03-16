package Exercise06;

import java.util.*;

class Product {
    String name;
    double price;

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }
}

class Order {
    Product product;
    double total;
    double finalAmount;

    public Order(Product product) {
        this.product = product;
        this.total = product.price;
    }
}

interface DiscountStrategy {
    double applyDiscount(double total);
}

interface PaymentMethod {
    void pay(double amount);
}

interface NotificationService {
    void notifyUser(String message);
}

class WebsiteDiscount implements DiscountStrategy {
    public double applyDiscount(double total) {
        System.out.println("Áp dụng giảm giá 10% cho đơn hàng website");
        return total * 0.9;
    }
}

class MobileDiscount implements DiscountStrategy {
    public double applyDiscount(double total) {
        System.out.println("Áp dụng giảm giá 15% cho lần đầu");
        return total * 0.85;
    }
}

class POSDiscount implements DiscountStrategy {
    public double applyDiscount(double total) {
        System.out.println("Áp dụng giảm giá thành viên tại cửa hàng");
        return total * 0.95;
    }
}

class CreditCardPayment implements PaymentMethod {
    public void pay(double amount) {
        System.out.println("Xử lý thanh toán thẻ tín dụng qua cổng thanh toán online");
    }
}

class MomoPayment implements PaymentMethod {
    public void pay(double amount) {
        System.out.println("Xử lý thanh toán MoMo tích hợp");
    }
}

class CashPayment implements PaymentMethod {
    public void pay(double amount) {
        System.out.println("Thanh toán tiền mặt tại quầy");
    }
}

class EmailNotification implements NotificationService {
    public void notifyUser(String message) {
        System.out.println("Gửi email xác nhận");
    }
}

class PushNotification implements NotificationService {
    public void notifyUser(String message) {
        System.out.println("Gửi push notification: " + message);
    }
}

class PrintInvoice implements NotificationService {
    public void notifyUser(String message) {
        System.out.println("In hóa đơn giấy tại POS");
    }
}

interface SalesChannelFactory {
    DiscountStrategy createDiscount();
    PaymentMethod createPayment();
    NotificationService createNotification();
}

class WebsiteFactory implements SalesChannelFactory {
    public DiscountStrategy createDiscount() {
        return new WebsiteDiscount();
    }

    public PaymentMethod createPayment() {
        return new CreditCardPayment();
    }

    public NotificationService createNotification() {
        return new EmailNotification();
    }
}

class MobileFactory implements SalesChannelFactory {
    public DiscountStrategy createDiscount() {
        return new MobileDiscount();
    }

    public PaymentMethod createPayment() {
        return new MomoPayment();
    }

    public NotificationService createNotification() {
        return new PushNotification();
    }
}

class POSFactory implements SalesChannelFactory {
    public DiscountStrategy createDiscount() {
        return new POSDiscount();
    }

    public PaymentMethod createPayment() {
        return new CashPayment();
    }

    public NotificationService createNotification() {
        return new PrintInvoice();
    }
}

class OrderService {
    DiscountStrategy discount;
    PaymentMethod payment;
    NotificationService notification;

    public OrderService(SalesChannelFactory factory) {
        discount = factory.createDiscount();
        payment = factory.createPayment();
        notification = factory.createNotification();
    }

    public void processOrder(Product product) {
        Order order = new Order(product);
        order.finalAmount = discount.applyDiscount(order.total);
        payment.pay(order.finalAmount);
        notification.notifyUser("Đơn hàng thành công");
    }
}

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("Chọn kênh bán");
        System.out.println("1. Website");
        System.out.println("2. Mobile App");
        System.out.println("3. POS");

        int choice = sc.nextInt();

        SalesChannelFactory factory;

        if (choice == 1) {
            factory = new WebsiteFactory();
            System.out.println("Bạn đã chọn kênh Website");
        } else if (choice == 2) {
            factory = new MobileFactory();
            System.out.println("Bạn đã chọn kênh Mobile App");
        } else {
            factory = new POSFactory();
            System.out.println("Bạn đã chọn kênh POS");
        }

        OrderService service = new OrderService(factory);

        if (choice == 1) {
            System.out.println("Tạo đơn hàng (Website)");
            Product p = new Product("Laptop", 15000000);
            service.processOrder(p);
        }

        if (choice == 2) {
            System.out.println("Chuyển kênh Mobile App");
            Product p = new Product("Điện thoại", 12000000);
            service.processOrder(p);
        }

        if (choice == 3) {
            System.out.println("Tạo đơn hàng tại POS");
            Product p = new Product("Tai nghe", 2000000);
            service.processOrder(p);
        }
    }
}