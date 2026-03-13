package lab3;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Task5 {
    public static void main(String[] args) {
        //Запись в файл с консоли
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите имя файла (пустая строка - конец): ");
        String filename = scanner.nextLine();

        //Пока не пустая строка - записываем
        try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(filename), StandardCharsets.UTF_8))) {
            while (true) {
                System.out.print("> ");
                String input = scanner.nextLine();

                if (input.isEmpty()) break;

                bw.write(input);
                bw.newLine();
            }

        } catch (IOException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }
}
