
package Exercise03;

interface PaymentMethod {
    void pay(double amount);
}

interface CODPayable extends PaymentMethod {
    void processCOD(double amount);
}

interface CardPayable extends PaymentMethod {
    void processCreditCard(double amount);
}

interface EWalletPayable extends PaymentMethod {
    void processMomo(double amount);
}

class CODPayment implements CODPayable {
    public void processCOD(double amount) {
        System.out.println("Xử lý thanh toán COD: " + (long) amount + " - Thành công");
    }

    public void pay(double amount) {
        processCOD(amount);
    }
}

class CreditCardPayment implements CardPayable {
    public void processCreditCard(double amount) {
        System.out.println("Xử lý thanh toán thẻ tín dụng: " + (long) amount + " - Thành công");
    }

    public void pay(double amount) {
        processCreditCard(amount);
    }
}

class MomoPayment implements EWalletPayable {
    public void processMomo(double amount) {
        System.out.println("Xử lý thanh toán MoMo: " + (long) amount + " - Thành công");
    }

    public void pay(double amount) {
        processMomo(amount);
    }
}

class PaymentProcessor {
    private PaymentMethod paymentMethod;

    public PaymentProcessor(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public void process(double amount) {
        paymentMethod.pay(amount);
    }
}

public class Main {
    public static void main(String[] args) {

        System.out.println("COD");
        PaymentProcessor p1 = new PaymentProcessor(new CODPayment());
        p1.process(500000);

        System.out.println();

        System.out.println("Thẻ tín dụng");
        PaymentProcessor p2 = new PaymentProcessor(new CreditCardPayment());
        p2.process(1000000);

        System.out.println();

        System.out.println("Ví MoMo");
        PaymentProcessor p3 = new PaymentProcessor(new MomoPayment());
        p3.process(750000);

        System.out.println();

        System.out.println("Kiểm tra LSP");
        PaymentMethod method = new CreditCardPayment();
        PaymentProcessor p4 = new PaymentProcessor(method);
        p4.process(1000000);

        method = new MomoPayment();
        p4 = new PaymentProcessor(method);
        p4.process(750000);
    }
}