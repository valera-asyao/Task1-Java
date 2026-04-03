package lab6.order.main;
import lab6.order.models.*;
import lab6.order.services.DiscountService;
public class Main {
    public static void main(String[] args) {
        Customer c1 = new Customer("C-001", "Дмитрий");

        OrderItem i1 = new OrderItem("Ноутбук", 100000, 1);
        OrderItem i2 = new OrderItem("Мышка", 2000, 2);

        Order order1 = new Order(c1, new OrderItem[]{i1, i2});

        System.out.println("Сумма заказа до скидки: " + order1.getTotalAmount() + " руб.");

        // Применяем сезонную скидку
        double discount = DiscountService.getSeasonalDiscount();
        order1.applyDiscount(discount);

        System.out.println("Сумма заказа после сезонной скидки (10%): " + order1.getTotalAmount() + " руб.");
        System.out.println("Всего потрачено клиентом " + c1.getName() + ": " + c1.getTotalSpent() + " руб.");

        System.out.println("\n--- Общая статистика ---");
        System.out.println("Всего клиентов: " + Customer.totalCustomers);
        System.out.println("Всего заказов: " + Order.totalOrders);
    }
}
