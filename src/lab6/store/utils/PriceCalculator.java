package lab6.store.utils;

import lab6.store.models.Product;
import java.util.List;

public class PriceCalculator {
    public static double calculateTotalPrice(List<Product> products) {
        double total = 0;
        for (Product p : products) {
            total += p.getPrice() * p.getQuantity(); // Стоимость всех остатков переданных товаров
        }
        return total;
    }
}
