package lab7.task5.main;
import lab7.task5.models.Car;

public class Main {
    public static void main(String[] args) {
        System.out.println("Сценарий 1: Создание с допустимым цветом");
        Car car1 = new Car("Tesla Model S", "White", 2023);
        car1.printInfo();

        System.out.println("\nСценарий 2: Создание с недопустимым цветом (Green)");
        try {
            Car car2 = new Car("Lada Vesta", "Green", 2024);
        } catch (IllegalArgumentException e) {
            System.out.println("Поймано исключение: " + e.getMessage());
        }

        System.out.println("\nСценарий 3: Добавление нового цвета");
        Car.addNewColor("Purple");
        System.out.println("Текущие цвета: " + Car.getAvailableColors());

        System.out.println("\nСценарий 4: Создание авто с новым цветом");
        Car car3 = new Car("Skyline GT-R", "Purple", 1999);
        car3.printInfo();

        System.out.println("\n>>> Сценарий 5: Попытка смены цвета");
        try {
            car1.changeColor("Black"); // Успешно
            car1.changeColor("Yellow"); // Ошибка
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка при покраске: " + e.getMessage());
        }

        car1.printInfo();
    }
}
