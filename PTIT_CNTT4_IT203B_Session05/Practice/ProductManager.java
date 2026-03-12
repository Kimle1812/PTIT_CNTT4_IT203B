package PTIT_CNTT4_IT203B_Session05.Practice;

import java.util.*;
import java.util.stream.Collectors;

public class ProductManager {
    private List<Product> products = new ArrayList<>();

    public void addProduct(Product product) throws InvalidProductException {
        Optional<Product> existing = products.stream().filter(p -> p.getId() == product.getId()).findFirst();
        if (existing.isPresent()) {
            throw new InvalidProductException("ID đã tồn tại!");
        }
        products.add(product);
    }

    public void displayProducts() {
        System.out.printf("%-5s %-15s %-10s %-10s %-10s\n","ID", "Name", "Price", "Quantity", "Category");
        products.forEach(System.out::println);
    }

    public void updateQuantity(int id, int newQuantity) throws InvalidProductException {
        Optional<Product> product = products.stream().filter(p -> p.getId() == id).findFirst();
        if (!product.isPresent()) {
            throw new InvalidProductException("Không tìm thấy sản phẩm với ID này!");
        }
        product.get().setQuantity(newQuantity);
    }

    public void deleteOutOfStock() {
        products = products.stream().filter(p -> p.getQuantity() > 0).collect(Collectors.toList());
    }
}
