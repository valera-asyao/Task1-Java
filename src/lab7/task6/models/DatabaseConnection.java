package lab7.task6.models;

public class DatabaseConnection {
    private static DatabaseConnection instance;

    private static String url;
    private static String username;
    private static String password;

    static {
        url = "jdbc:h2:mem:test";
        username = "sa";
        password = "";
        System.out.println("Параметры БД по умолчанию установлены");
    }

    private DatabaseConnection() {
        System.out.println("Создание единственного объекта DatabaseConnection");
    }

    public static DatabaseConnection getInstance() {
        if (instance == null) {
            instance = new DatabaseConnection();
        }
        return instance;
    }

    public static void configure(String newUrl, String newUser, String newPass) {
        url = newUrl;
        username = newUser;
        password = newPass;
        System.out.println("Конфигурация обновлена: " + url);
    }

    public void connect() {
        System.out.println("Подключение к БД установлено (" + url + ", user: " + username + ")");
    }

    public void disconnect() {
        System.out.println("Отключено от базы данных.");
    }

    public void executeQuery(String sql) {
        System.out.println("Выполнение запроса: [" + sql + "]");
    }
}
