package lab6.store.models;

public class Product {
    public static int totalProducts = 0;

    private String productId;
    private String name;
    private double price;
    private int quantity;
    private Category category;
    private Supplier supplier;

    public Product(String productId, String name, double price, int quantity, Category category, Supplier supplier) {
        this.productId = productId;
        this.name = name;
        setPrice(price);
        setQuantity(quantity);
        this.category = category;
        this.supplier = supplier;

        if (supplier != null) supplier.addProduct(this);
        totalProducts++;
    }

    public void setPrice(double price) {
        if (price >= 0) this.price = price;
        else throw new IllegalArgumentException("Цена не может быть отрицательной.");
    }
    public double getPrice() { return price; }

    public void setQuantity(int quantity) {
        if (quantity >= 0) this.quantity = quantity;
        else throw new IllegalArgumentException("Количество не может быть отрицательным.");
    }
    public int getQuantity() { return quantity; }
    public String getProductId() { return productId; }
    public String getName() { return name; }

    public boolean isInStock() {
        return quantity > 0;
    }
}
