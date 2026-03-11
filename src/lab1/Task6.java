package lab1;

import java.util.Scanner;

public class Task6 {
    //Расстояние между 2 точками
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите (x1, y1) через пробел: ");

        //Считывание чисел из строки
        double x1 = scanner.nextDouble();
        double y1 = scanner.nextDouble();

        Scanner scanner1 = new Scanner(System.in);
        System.out.print("Введите (x2, y2) через пробел: ");
        //Считывание чисел из строки
        double x2 = scanner1.nextDouble();
        double y2 = scanner1.nextDouble();

        // Math.sqrt считает корень, Math.pow - степень числа, %f - для чисел типа double, float
        System.out.printf("Расстояние между точками (%f, %f) и (%f, %f) = %f", x1, y1, x2, y2, Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2)));
    }
}
//Введите (x1, y1) через пробел: 2 4
//Введите (x2, y2) через пробел: 2 6
//Расстояние между точками (2,000000, 4,000000) и (2,000000, 6,000000) = 2,000000