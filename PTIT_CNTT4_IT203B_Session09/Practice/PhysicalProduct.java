package PTIT_CNTT4_IT203B_Session09.Practice;

public class PhysicalProduct extends Product {
    private double weight;

    public PhysicalProduct(String id, String name, double price, double weight) {
        super(id, name, price);
        this.weight = weight;
    }

    @Override
    public void displayInfo() {
        System.out.println("Physical Product: " + name +", Price: " + price + ", Weight: " + weight + "kg");
    }
}
