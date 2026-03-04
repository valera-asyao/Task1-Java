package lab2;
import java.util.Scanner;

public class Task6 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите размер массива: ");

        int n = scanner.nextInt();
        assert n < 0 : "Длина массива не может быть <0";

        int[] arr = new int[n];

        int cnt_pos = 0;
        int cnt_neg = 0;
        int cnt_zero = 0;
        for (int i = 0; i < n; i++) {
            arr[i] = (int) (Math.random() * 21) - 10;
            System.out.printf("%d: %d\n", i, arr[i]);
            if (arr[i] > 0)
                cnt_pos += 1;
            else if (arr[i] < 0) {
                cnt_neg += 1;
            } else cnt_zero += 1;
        }
        System.out.printf("Число положительных: %d\n" +
                "Число отрицательных: %d\n" +
                "Число нулей: %d", cnt_pos, cnt_neg, cnt_zero);
    }
}
