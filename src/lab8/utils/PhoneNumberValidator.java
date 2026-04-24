package lab8.utils;

public class PhoneNumberValidator {
    public static boolean isValid(String phone) {
        if (phone == null) return false;
        // Удаляем все пробелы для проверки длины значащих символов
        String cleanPhone = phone.replaceAll("\\s+", "");
        if (cleanPhone.length() < 5 || cleanPhone.length() > 15) {
            return false;
        }
        // Регулярное выражение для проверки допустимых символов
        return phone.matches("^[\\d+\\-()\\s]+$");
    }

    public static String formatPhone(String phone) {
        if (phone == null) return "";
        return phone.replaceAll("[\\s\\-()]+", "");
    }
}