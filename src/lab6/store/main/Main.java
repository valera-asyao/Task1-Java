package lab6.store.main;
import lab6.store.models.*;
import lab6.store.services.InventoryService;
import lab6.store.utils.PriceCalculator;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Category electronics = new Category("Электроника", "Гаджеты и устройства");
        Supplier appleSupp = new Supplier("Apple Inc.", "apple@example.com");

        Product p1 = new Product("P001", "iPhone 15", 90000, 10, electronics, appleSupp);
        Product p2 = new Product("P002", "AirPods Pro", 25000, 3, electronics, appleSupp);

        InventoryService.addProduct(p1);
        InventoryService.addProduct(p2);

        // Продаем часть товара
        InventoryService.removeProduct("P001", 2);
        InventoryService.removeProduct("P002", 3); // Теперь AirPods 0 штук

        System.out.println("\n--- Отчет по товарам с низким остатком (меньше 5 шт) ---");
        List<Product> lowStock = InventoryService.checkLowStock(5);
        for (Product p : lowStock) {
            System.out.println(p.getName() + " — осталось " + p.getQuantity() + " шт. (В наличии? " + p.isInStock() + ")");
        }

        System.out.println("Общая стоимость остатков товаров с низким запасом: " + PriceCalculator.calculateTotalPrice(lowStock) + " руб.");
    }
}
