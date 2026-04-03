package lab6.store.services;
import lab6.store.models.Product;
import java.util.ArrayList;
import java.util.List;

public class InventoryService {
    // Хранилище склада
    private static List<Product> inventory = new ArrayList<>();

    public static void addProduct(Product product) {
        if (product != null) {
            inventory.add(product);
            System.out.println("Товар добавлен на склад: " + product.getName());
        }
    }

    public static void removeProduct(String productId, int quantity) {
        for (Product p : inventory) {
            if (p.getProductId().equals(productId)) {
                if (p.getQuantity() >= quantity) {
                    p.setQuantity(p.getQuantity() - quantity);
                    System.out.println("Списано " + quantity + " шт. товара " + p.getName());
                } else {
                    System.out.println("Недостаточно товара " + p.getName() + " на складе!");
                }
                return;
            }
        }
        System.out.println("Товар с ID " + productId + " не найден.");
    }

    public static List<Product> checkLowStock(int threshold) {
        List<Product> lowStock = new ArrayList<>();
        for (Product p : inventory) {
            if (p.getQuantity() < threshold) {
                lowStock.add(p);
            }
        }
        return lowStock;
    }
}
