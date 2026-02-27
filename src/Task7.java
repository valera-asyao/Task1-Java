import java.util.Scanner;

public class Task7 {
    // Переводит минуты в часы и минуты
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите минуты: ");

        int min = scanner.nextInt();
        System.out.printf("%d ч %d мин", min/60, min%60);
    }
}
