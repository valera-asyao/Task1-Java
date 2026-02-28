package lab2;
import java.util.Scanner;

public class Task4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите месяц: ");

        int month = scanner.nextInt();
        switch (month) {
            case 1:
                System.out.println("31 день");
                break;
            case 2:
                System.out.println("28 или 29 дней");
                break;
            case 3:
                System.out.println("31 день");
                break;
            case 4:
                System.out.println("30 дней");
            case 5:
                System.out.println("31 день");
                break;
            case 6:
                System.out.println("30 дней");
            case 7:
                System.out.println("31 день");
                break;
            case 8:
                System.out.println("31 день");
                break;
            case 9:
                System.out.println("30 дней");
            case 10:
                System.out.println("31 день");
                break;
            case 11:
                System.out.println("30 дней");
            case 12:
                System.out.println("31 день");
                break;
            default:
                System.out.println("Ошибка");
                break;
        }
    }
}
