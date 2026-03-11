package lab1;

import java.util.Scanner;

public class Task5 {
    //Считает длину окружности и ее площадь
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите радиус окружности: ");

        int r = scanner.nextInt();
        double C = 2 * Math.PI * r;
        double S = Math.PI * Math.pow(r, 2);

        System.out.printf("C = %.3f%nS = %.3f", C, S);

    }
}
//Введите радиус окружности: 12
//C = 75,398
//S = 452,389