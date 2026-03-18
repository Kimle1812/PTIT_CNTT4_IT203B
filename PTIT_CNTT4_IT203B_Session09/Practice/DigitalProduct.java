package PTIT_CNTT4_IT203B_Session09.Practice;

public class DigitalProduct extends Product {
    private double sizeMB;

    public DigitalProduct(String id, String name, double price, double sizeMB) {
        super(id, name, price);
        this.sizeMB = sizeMB;
    }

    @Override
    public void displayInfo() {
        System.out.println("Digital Product: " + name +", Price: " + price +", Size: " + sizeMB + "MB");
    }
}
