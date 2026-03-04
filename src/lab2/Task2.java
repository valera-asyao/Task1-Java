package lab2;
import java.util.Scanner;

public class Task2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите число элементов массива: ");

        int n = scanner.nextInt();
        assert n < 0: "Длина массива не может быть <0";
        int[] arr = new int[n];
        double sum = 0;
        for(int i = 0; i < n; i++){
            arr[i] = (int) (Math.random() * 50 + 1);
            sum += arr[i];
            System.out.printf("%d: %d\n", i, arr[i]);
        }
        System.out.printf("Сумма элементов: %f\nСреднее арифметическое: %f", sum, sum/n);
        
    }
}
