package lab7.task6.main;
import lab7.task6.models.DatabaseConnection;

public class Main {
    public static void main(String[] args) {
        // 1. Настройка до первого вызова
        DatabaseConnection.configure("jdbc:mysql://localhost:3306/mydb", "admin", "12345");

        System.out.println("Попытка получить первое соединение...");
        DatabaseConnection conn1 = DatabaseConnection.getInstance();
        conn1.connect();
        conn1.executeQuery("SELECT * FROM users");

        System.out.println("\nПопытка получить второе соединение...");
        DatabaseConnection conn2 = DatabaseConnection.getInstance();
        conn2.executeQuery("UPDATE users SET name = 'John'");

        // 2. Проверка на идентичность объектов
        System.out.println("\nПроверка Singleton");
        System.out.println("Ссылка на conn1: " + conn1.hashCode());
        System.out.println("Ссылка на conn2: " + conn2.hashCode());

        if (conn1 == conn2) {
            System.out.println("Успех: Оба объекта идентичны (это одна и та же ссылка)!");
        } else {
            System.out.println("Ошибка: Создано несколько объектов!");
        }

        conn1.disconnect();
    }
}
