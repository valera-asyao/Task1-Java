import java.util.Scanner;

public class Task1 {
    // Выводит имя и возраст через 5 лет
    public static void main(String[] args) {
        // Считываем строку
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите через пробел имя и возраст: ");
        //Интерполированная строка. scanner.next() возвращает string, поэтому возраст находим через scanner.nextInt(), 5 добавляем через метод valueOf
        //преображаем в нужный тип данных - string
        System.out.println("Привет, " + scanner.next() + "! Через 5 лет тебе будет " + String.valueOf(scanner.nextInt() + 5)  + " лет");
    }
}
//Введите через пробел имя и возраст: Валера 20
//Привет, Валера! Через 5 лет тебе будет 25 лет