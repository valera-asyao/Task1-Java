package lab7.task3.models;

public class InitializationDemo {
    private static String staticField = initStatic();
    private  String instanceField = initInstance();

    static {
        System.out.println("--- Выполняется статический блок инициализации ---");
    }

    // 4. Нестатический блок инициализации
    {
        System.out.println("--- Выполняется нестатический блок инициализации ---");
    }

    // Статический метод для инициализации поля
    private static String initStatic() {
        System.out.println("--- Выполняется статический метод инициализации поля ---");
        return "static";
    }

    // Метод экземпляра для инициализации поля
    private String initInstance() {
        System.out.println("--- Выполняется метод экземпляра для инициализации поля ---");
        return "instance";
    }

    // 5. Конструктор с параметром
    public InitializationDemo(String name) {
        System.out.println("--- Выполняется конструктор с параметром: " + name + " ---");
    }

    // 6. Конструктор по умолчанию
    public InitializationDemo() {
        this("default"); // Вызов другого конструктора через this()
        System.out.println("--- Выполняется конструктор по умолчанию ---");
    }

}
