package lab2;
import java.util.Scanner;

public class Task5 {
    public static void main(String[] args) {
        //Поиск максимума и минимума
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите размер массива: ");

        int n = scanner.nextInt();
        assert n < 0: "Длина массива не может быть <0";
        int[] arr = new int[n];
        int max = -1000; int ind_max = 0;
        int min = 1000; int ind_min = 0;
        for(int i = 0; i < n; i++){
            arr[i] = (int) (Math.random() * (50 + 50 + 1) - 50);
            if(arr[i] > max)
            {
                max = arr[i];
                ind_max = i;
            }
            if(arr[i] < min){
                min = arr[i];
                ind_min = i;
            }
            System.out.printf("%d: %d\n", i, arr[i]);
        }

        System.out.printf("Max, pos in arr: %d %d\nMin, pos in arr: %d %d\n", max, ind_max, min, ind_min);
    }
}
