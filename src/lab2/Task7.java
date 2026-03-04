package lab2;
import java.util.Scanner;

public class Task7 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите число от 1 до 10: ");

        int n = scanner.nextInt();
        assert (n < 1) || (n > 10) : "Число не может быть меньше 1 и больше 10";

        for (int i = n; i < 11; i++){
            System.out.printf("%d * %d = %d\n", i, n, i*n);
        }
    }
}
