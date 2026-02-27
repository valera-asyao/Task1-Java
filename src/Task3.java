import java.util.Scanner;

public class Task3 {
    // Арифметические действия с двумя числами
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите два числа через пробел: ");

        int a = scanner.nextInt();
        int b = scanner.nextInt();

        System.out.println(a + " + " + b + " = " + (a+b));
        System.out.println(a + " - " + b + " = " + (a-b));
        System.out.println(a + " * " + b + " = " + (a*b));
        System.out.println(a + " / " + b + " = " + (a/b));
        System.out.println(a + " % " + b + " = " + (a%b));
        System.out.println("max(a,b) = " + Math.max(a,b));

    }
}
//Введите два числа через пробел: 6 2
//        6 + 2 = 8
//        6 - 2 = 4
//        6 * 2 = 12
//        6 / 2 = 3
//        6 % 2 = 0
//max(a,b) = 6