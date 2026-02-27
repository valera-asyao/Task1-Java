import java.util.Scanner;

public class Task4 {
    //Сумма цифр числа
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите 3-х значное число: ");

        int n = scanner.nextInt();
        //Считаем цифры в числе
        int first = n / 100;
        int second = (n / 10) % 10;
        int third = n % 10;

        System.out.print("Сумма цифр: " + String.valueOf(first + second + third));

    }
}
//Введите 3-х значное число: 123
//Сумма цифр: 6