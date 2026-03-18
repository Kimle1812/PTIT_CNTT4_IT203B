package PTIT_CNTT4_IT203B_Session09.Practice;

import java.util.*;

public class ProductDatabase {
    private static ProductDatabase instance;
    private List<Product> products;

    private ProductDatabase() {
        products = new ArrayList<>();
    }

    public static ProductDatabase getInstance() {
        if (instance == null) {
            instance = new ProductDatabase();
        }
        return instance;
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public void updateProduct(String id, Product newProduct) {
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).id.equals(id)) {
                products.set(i, newProduct);
                return;
            }
        }
    }

    public void deleteProduct(String id) {
        products.removeIf(p -> p.id.equals(id));
    }

    public List<Product> getProducts() {
        return products;
    }
}
