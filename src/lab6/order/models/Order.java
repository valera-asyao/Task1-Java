package lab6.order.models;
import lab6.order.utils.OrderIdGenerator;
import lab6.order.models.*;

import java.util.Date;

public class Order {
    public static int totalOrders = 0;

    private String orderId;
    private Customer customer;
    private OrderItem[] items;
    private Date orderDate;

    public Order(Customer customer, OrderItem[] items) {
        this.orderId = generateOrderId();
        this.customer = customer;
        this.items = items;
        this.orderDate = new Date();

        customer.addOrder(this);
        totalOrders++;
    }

    public static String generateOrderId() {
        return OrderIdGenerator.generate();
    }

    public double getTotalAmount() {
        double total = 0;
        for (OrderItem item : items) {
            total += item.getTotalPrice();
        }
        return total;
    }

    public void applyDiscount(double percent) {
        if (percent > 0 && percent <= 1) {
            for (OrderItem item : items) {
                // Применяем скидку, обновляя цену за единицу
                double currentPrice = item.getTotalPrice() / (item.getTotalPrice() / item.getTotalPrice()); // достаем исходную цену
                item.setPrice(currentPrice * (1 - percent));
            }
        }
    }
}
