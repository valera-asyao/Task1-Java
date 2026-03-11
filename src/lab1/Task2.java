package lab1;

import java.util.Scanner;

public class Task2 {
    // Переводит температуру в Цельсиях в градусы Фаренгейта и Кельвина
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите температуру в цельсиях: ");
        // Считываем градусы в Цельсиях
        double C = scanner.nextDouble();
        // Считаем температуры в Фаренгейтах и Кельвинах
        double F = C * 9/5 + 32;
        double K = C + 273.15;

        // Форматированный вывод, %.Nf - количество цифр (N) после запятой
        System.out.printf("F = %.2f%nK = %.2f%n", F, K);
    }
}
//Введите температуру в цельсиях: 20
//F = 68,00
//K = 293,15