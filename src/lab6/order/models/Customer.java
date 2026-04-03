package lab6.order.models;

public class Customer {
    public static int totalCustomers = 0;

    private String customerId;
    private String name;
    private Order[] orders;
    private int orderCount;

    public Customer(String customerId, String name) {
        this.customerId = customerId;
        this.name = name;
        this.orders = new Order[50];
        this.orderCount = 0;
        totalCustomers++;
    }

    public String getName() { return name; }
    public int getOrderCount() { return orderCount; }

    public void addOrder(Order order) {
        if (orderCount < orders.length) {
            orders[orderCount++] = order;
        }
    }

    public double getTotalSpent() {
        double total = 0;
        for (int i = 0; i < orderCount; i++) {
            total += orders[i].getTotalAmount();
        }
        return total;
    }
}
