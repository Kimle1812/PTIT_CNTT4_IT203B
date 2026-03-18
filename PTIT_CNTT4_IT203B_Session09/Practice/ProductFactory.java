package PTIT_CNTT4_IT203B_Session09.Practice;

public class ProductFactory {
    public static Product createProduct(int type, String id, String name, double price, double extra) {
        if (type == 1) {
            return new PhysicalProduct(id, name, price, extra);
        } else if (type == 2) {
            return new DigitalProduct(id, name, price, extra);
        }
        return null;
    }
}
