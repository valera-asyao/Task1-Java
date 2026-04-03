package lab6.store.models;

public class Supplier {
    private String supplierName;
    private String contactInfo;
    private Product[] productsSupplied;
    private int productCount;

    public Supplier(String supplierName, String contactInfo) {
        this.supplierName = supplierName;
        this.contactInfo = contactInfo;
        this.productsSupplied = new Product[100];
        this.productCount = 0;
    }
    public void addProduct(Product p) {
        if (productCount < productsSupplied.length) productsSupplied[productCount++] = p;
    }
}
