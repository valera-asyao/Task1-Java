package lab6.university.utils;

public class StringUtils {
    // Делает первую букву заглавной
    public static String capitalize(String str) {
        if (str == null || str.trim().isEmpty()) return str;
        return str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase();
    }

    // Проверяет, что имя содержит только буквы и пробелы
    public static boolean isValidName(String name) {
        return name != null && name.matches("^[a-zA-Zа-яА-Я\\s]+$");
    }
}
