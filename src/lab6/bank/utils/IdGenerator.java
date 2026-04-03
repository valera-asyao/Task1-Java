package lab6.bank.utils;

public class IdGenerator {
    public static String generateId() {
        // Текущее время + случайное число от 0 до 999
        return System.currentTimeMillis() + "-" + (int)(Math.random() * 1000);
    }
}