package PTIT_CNTT4_IT203B_Session05.miniProject.model;

public class Dessert extends MenuItem {
    public Dessert(String id, String name, double price, int stock) {
        super(id, name, price, stock);
    }

    @Override
    public double calculatePrice() {
        return getPrice();
    }
}
