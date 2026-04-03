package lab6.order.utils;

public class OrderIdGenerator {
    private static int counter = 1000;
    public static String generate() {
        return "ORD-" + (++counter);
    }
}
