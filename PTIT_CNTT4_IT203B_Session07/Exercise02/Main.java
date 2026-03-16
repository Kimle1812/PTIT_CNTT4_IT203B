
package Exercise02;

interface DiscountStrategy {
    double applyDiscount(double totalAmount);
}

class PercentageDiscount implements DiscountStrategy {
    private double percent;

    public PercentageDiscount(double percent) {
        this.percent = percent;
    }

    public double applyDiscount(double totalAmount) {
        return totalAmount - (totalAmount * percent / 100);
    }
}

class FixedDiscount implements DiscountStrategy {
    private double amount;

    public FixedDiscount(double amount) {
        this.amount = amount;
    }

    public double applyDiscount(double totalAmount) {
        return totalAmount - amount;
    }
}

class NoDiscount implements DiscountStrategy {
    public double applyDiscount(double totalAmount) {
        return totalAmount;
    }
}

class HolidayDiscount implements DiscountStrategy {
    public double applyDiscount(double totalAmount) {
        return totalAmount - (totalAmount * 0.15);
    }
}

class OrderCalculator {
    private DiscountStrategy discountStrategy;

    public OrderCalculator(DiscountStrategy discountStrategy) {
        this.discountStrategy = discountStrategy;
    }

    public double calculate(double totalAmount) {
        return discountStrategy.applyDiscount(totalAmount);
    }
}

public class Main {
    public static void main(String[] args) {

        double total = 1000000;

        System.out.println("Đơn hàng: tổng tiền 1.000.000, áp dụng PercentageDiscount 10%");
        OrderCalculator calc1 = new OrderCalculator(new PercentageDiscount(10));
        System.out.println("Số tiền sau giảm: " + (long) calc1.calculate(total));

        System.out.println();

        System.out.println("Đơn hàng: tổng tiền 1.000.000, áp dụng FixedDiscount 50.000");
        OrderCalculator calc2 = new OrderCalculator(new FixedDiscount(50000));
        System.out.println("Số tiền sau giảm: " + (long) calc2.calculate(total));

        System.out.println();

        System.out.println("Đơn hàng: tổng tiền 1.000.000, áp dụng NoDiscount");
        OrderCalculator calc3 = new OrderCalculator(new NoDiscount());
        System.out.println("Số tiền sau giảm: " + (long) calc3.calculate(total));

        System.out.println();

        System.out.println("Thêm HolidayDiscount 15% (không sửa code cũ)");
        OrderCalculator calc4 = new OrderCalculator(new HolidayDiscount());
        System.out.println("Số tiền sau giảm: " + (long) calc4.calculate(total));
    }
}