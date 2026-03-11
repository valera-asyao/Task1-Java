package lab3;

import java.io.*;
import java.util.*;

public class Task6 {
    public static void main(String[] args) {
        File file = new File("numbers.txt");
        if (!file.exists()) {
            System.out.println("Файл не найден.");
            return;
        }

        List<Integer> nums = new ArrayList<>();
        try (Scanner fs = new Scanner(file)) {
            while (fs.hasNextInt()) {
                nums.add(fs.nextInt());
            }
        } catch (FileNotFoundException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }

        if (nums.isEmpty()) {
            System.out.println("Файл пуст или не содержит чисел.");
            return;
        }

        long sum = 0;
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        for (int n : nums) {
            sum += n;
            if (n < min) min = n;
            if (n > max) max = n;
        }

        System.out.println("Сумма: " + sum);
        System.out.println("Среднее: " + (double) sum / nums.size());
        System.out.println("Минимум: " + min);
        System.out.println("Максимум: " + max);
    }
}
