package lab6.order.services;

public class DiscountService {
    public static double getSeasonalDiscount() {
        return 0.1; // 10%
    }

    public static double getLoyaltyDiscount(int orderCount) {
        return orderCount > 5 ? 0.05 : 0.0; // 5% если заказов больше 5
    }
}
