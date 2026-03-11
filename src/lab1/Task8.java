package lab1;

import java.util.Scanner;

public class Task8 {
    public static void main(String[] args) {
        //Трехзначное число наоборот
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите 3-х значное число: ");

        int n = scanner.nextInt();
        System.out.printf("Трёхзначное число наоборот: %d", (n%10)*100 + ((n/10)%10)*10 + n/100);
    }
}
