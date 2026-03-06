package lab2;

public class MagicCheck{
    public static boolean IsMagic(int[][] matrix){
        int n = matrix.length;

        // Сумма первой строки
        int magicSum = 0;
        for (int j = 0; j < n; j++) {
            magicSum += matrix[0][j];
        }

        // Проверка сумм строк
        for (int i = 0; i < n; i++) {
            int rowSum = 0;
            for (int j = 0; j < n; j++) {
                rowSum += matrix[i][j];
            }
            if (rowSum != magicSum) return false;
        }

        // Проверка сумм столбцов
        for (int j = 0; j < n; j++) {
            int colSum = 0;
            for (int i = 0; i < n; i++) {
                colSum += matrix[i][j];
            }
            if (colSum != magicSum) return false;
        }

        // Проверка главной диагонали
        int diag1Sum = 0;
        for (int i = 0; i < n; i++) {
            diag1Sum += matrix[i][i];
        }
        if (diag1Sum != magicSum) return false;

        // Проверка побочной диагонали
        int diag2Sum = 0;
        for (int i = 0; i < n; i++) {
            diag2Sum += matrix[i][n - 1 - i];
        }
        if (diag2Sum != magicSum) return false;

        return true;
    }
}
