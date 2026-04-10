package lab7.task10.main;
import lab7.task10.models.*;

public class Main {
    public static void main(String[] args) {
        System.out.println(">>> Шаг 1: Обращение к классу и создание первого объекта Child <<<");
        Child child1 = new Child();

        System.out.println("\n" + "=".repeat(50) + "\n");

        System.out.println(">>> Шаг 2: Создание второго объекта Child <<<");
        Child child2 = new Child();
    }
}
