package lab2;
import java.util.Scanner;

public class Task3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите первое число, второе и операцию: ");
        
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        String op = scanner.next();

        switch (op) {
            case "+":
                System.out.printf("%d + %d = %d", a, b, a+b);
                break;
            case "-":
                System.out.printf("%d - %d = %d", a, b, a-b);
                break;
            case "*":
                System.out.printf("%d * %d = %d", a, b, a*b);
                break;
            case "/":
                assert b == 0: "Делитель не может быть нулем";
                System.out.printf("%d / %d = %.2f%n", a, b, a/(double)b);
                break;    
            default:
                break;
        }

    }
}
