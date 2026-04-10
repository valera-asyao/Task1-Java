package lab7.task9.main;
import lab7.task9.models.User;

public class Main {
    public static void main(String[] args) {
        User user1 = new User.Builder()
                .setFirstName("Иван")
                .setLastName("Иванов")
                .setEmail("ivan@example.com")
                .setAge(25)
                .setPhone("+79991234567")
                .setAddress("Москва")
                .build();

        User user2 = new User.Builder()
                .setFirstName("Анна")
                .setLastName("Петрова")
                .setEmail("anna@test.ru")
                .build();

        System.out.println(user1);
        System.out.println(user2);

        try {
            User badUser = new User.Builder().setFirstName("Ошибка").build();
        } catch (IllegalStateException e) {
            System.err.println("\nОшибка создания: " + e.getMessage());
        }
    }
}
