package lab6.order.models;

public class OrderItem {
    private String productName;
    private double price;
    private int quantity;

    public OrderItem(String productName, double price, int quantity) {
        this.productName = productName;
        setPrice(price);
        setQuantity(quantity);
    }

    public void setPrice(double price) {
        if (price >= 0) this.price = price;
        else throw new IllegalArgumentException("Цена не может быть отрицательной");
    }

    public void setQuantity(int quantity) {
        if (quantity > 0) this.quantity = quantity;
        else throw new IllegalArgumentException("Количество должно быть больше нуля");
    }

    public double getTotalPrice() { return price * quantity; }
}
