package lab2;
import java.util.Scanner;

public class Task1 {
    // Определение времени суток
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Который сейчас час? ");

        int hour = scanner.nextInt();

        //проверка, что число введено корректно
        assert hour < 0 || hour > 23: "Такого часа нет.";

        //проверка числа, вывод
        if(hour >= 0 && hour <= 5)
            System.out.println("Ночь");
        else if(hour >= 6 && hour <= 11)
            System.out.println("Утро");
        else if(hour >= 12 && hour <= 17)
            System.out.println("День");
        else if(hour >= 18 && hour <= 23)
            System.out.println("Вечер");
    }
}
