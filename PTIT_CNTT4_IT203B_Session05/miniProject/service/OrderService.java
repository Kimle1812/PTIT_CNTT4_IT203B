package PTIT_CNTT4_IT203B_Session05.miniProject.service;

import PTIT_CNTT4_IT203B_Session05.miniProject.exception.InsufficientStockException;
import PTIT_CNTT4_IT203B_Session05.miniProject.exception.InvalidOrderIdException;
import PTIT_CNTT4_IT203B_Session05.miniProject.model.MenuItem;
import PTIT_CNTT4_IT203B_Session05.miniProject.model.Order;
import PTIT_CNTT4_IT203B_Session05.miniProject.repository.OrderRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class OrderService {
    private OrderRepository repo = new OrderRepository();

    public Order createOrder() {
        String id = UUID.randomUUID().toString();
        Order o = new Order(id);
        repo.add(o);
        return o;
    }

    public void addItemToOrder(String orderId, MenuItem item, int qty)
            throws InvalidOrderIdException, InsufficientStockException {
        Order o = repo.findById(orderId).orElseThrow(() -> new InvalidOrderIdException("Order not found"));
        if (item.getStock() < qty) {
            throw new InsufficientStockException("Not enough stock for item " + item.getName());
        }
        item.reduceStock(qty);
        o.addItem(item, qty);
    }

    public void applyDiscount(String orderId, double amount) throws InvalidOrderIdException {
        Order o = repo.findById(orderId).orElseThrow(() -> new InvalidOrderIdException("Order not found"));
        o.setDiscount(amount);
    }

    public void setServiceFee(String orderId, double fee) throws InvalidOrderIdException {
        Order o = repo.findById(orderId).orElseThrow(() -> new InvalidOrderIdException("Order not found"));
        o.setServiceFee(fee);
    }

    public void updateStatus(String orderId, Order.Status status) throws InvalidOrderIdException {
        Order o = repo.findById(orderId).orElseThrow(() -> new InvalidOrderIdException("Order not found"));
        o.setStatus(status);
    }

    public double revenueForDate(LocalDate date) {
        return repo.totalRevenueForDate(date);
    }

    public List<Order> getAllOrders() {
        return repo.getAll();
    }
}
