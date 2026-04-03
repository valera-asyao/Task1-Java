package lab5;


import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ShoppingCart {
    // Поля
    private Map<Product, Integer> items;

    // Конструктор
    public ShoppingCart() { items = new HashMap<>(); }

    // Добавление продукта в корзину
    public void addProduct(Product product, int quantity) throws Exception {
        if (!product.isAvailable())
            throw new Exception("Нельзя взять товар ");
        if (quantity <= 0)
            throw new Exception("Количество > 0");
        if (product.getQuantityInStock() < quantity)
            throw new Exception("Товара не хватает на складе");
        if (items.containsKey(product)) {
            items.compute(product, (key, value) -> value += quantity);
            product.reduceStock(quantity);
        }
        else {
            items.put(product, quantity);
            product.reduceStock(quantity);
        }
    }
    // Удаление продукта из корзины
    public void removeProduct(String productName) throws Exception {
        if (productName == null)
            throw new NullPointerException("Имя товара не может быть пустым");
        Map.Entry<Product, Integer> p = null;
        for (var elem : items.entrySet())
            if (Objects.equals(elem.getKey().getName(), productName))
                p = elem;
        if (p != null) {
            items.remove(p.getKey());
            p.getKey().increaseStock(p.getValue());
        }
    }

    public double getTotalPrice() {
        double sum = 0.0;
        for (var elem : items.entrySet())
            sum += elem.getKey().getPrice() * elem.getValue();
        return sum;
    }

    public void checkout() {
        if (items.isEmpty())
            System.out.println("Вы ничего не купили :(");
        else {
            items.clear();
            System.out.println("Заказ оформлен");
        }
    }

    public void printCart() {
        for (var elem : items.entrySet()){
            elem.getKey().printInfo();
            System.out.println("Количество в корзине: " + elem.getValue() + "\n");
        }
        System.out.println("Общая цена: " + getTotalPrice());
    }

    public static void main(String[] args){
        try{
        Product product1 = new Product("MSI katana", 5000.0, 5, "Электроника");
        Product product2 = new Product("VXE 200", 2500.0, 20, "Электроника");
        Product product3 = new Product("ARDOR", 500.0, 15, "Электроника");
        Product product4 = new Product("DELL", 5000.0, 8, "Электроника");
        Product product5 = new Product("USB-кабель", 500.0, 50, "Аксессуары");

        // Вывод информации о всех товарах
        System.out.println("Информация о товарах");
        product1.printInfo();
        System.out.println();
        product2.printInfo();
        System.out.println();
        product3.printInfo();
        System.out.println();
        product4.printInfo();
        System.out.println();
        product5.printInfo();
        System.out.println();

        ShoppingCart cart = new ShoppingCart();

        System.out.println("Добавление товаров в корзину");

        cart.addProduct(product1, 1);
        cart.addProduct(product2, 2);
        cart.addProduct(product3, 1);
        cart.addProduct(product5, 3);

        System.out.println("Содержимое корзины");
        cart.printCart();

        System.out.println("Товары после добавления в корзину");
        product1.printInfo();
        System.out.println();
        product2.printInfo();
        System.out.println();
        product5.printInfo();
        System.out.println();

        System.out.println("Удаление товара из корзины");
        cart.removeProduct("VXE 200");
        cart.printCart();

        System.out.println("Товары на складе после удаления из корзины");
        product2.printInfo();
        System.out.println();

        cart.addProduct(product4, 2);

        System.out.println("Итоговая корзина");
        cart.printCart();

        System.out.println("Оформление заказа");
        cart.checkout();

        System.out.println("Корзина после оформления заказ");
        cart.printCart();

        System.out.println("Итоговые остатки на складе");
        product1.printInfo();
        System.out.println();
        product2.printInfo();
        System.out.println();
        product3.printInfo();
        System.out.println();
        product4.printInfo();
        System.out.println();
        product5.printInfo();
        System.out.println();

        System.out.println("Добавление в оформленную корзину");
        cart.addProduct(product1, 1); // Теперь можно добавить, корзина пуста
        cart.printCart();
    }
    catch (Exception e){
        System.err.println(e.getMessage());
    }
}
}



