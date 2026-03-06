package lab2;
import java.util.Scanner;
import static lab2.MagicCheck.IsMagic;
public class Task8 {
    //Магический квадрат
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите размер матрицы: ");

        int n = scanner.nextInt();

        //Вывод матрицы
        int [][]matrix = new int[n][n];
        for(int i = 0; i < n; i++)
        {
            for(int j = 0; j < n; j++) {
                matrix[i][j] = (int) (Math.random() * 10);
                System.out.print(matrix[i][j] + "\t");
            }
            System.out.println();
        }
        if (IsMagic(matrix))
            System.out.print("Матрица - магическая");
        else
            System.out.print("Матрица - не магическая");

    }

}
