package lab3;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Task3 {
    public static void main(String[] args) {
        //Подсчёт символов, слов и строк в текстовом файле
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите имя файла: ");
        String filename = scanner.nextLine();

        int chars = 0, words = 0, lines = 0;

        try (BufferedReader br = new BufferedReader(new InputStreamReader(
                new FileInputStream(filename), StandardCharsets.UTF_8))) {
            String line;
            while ((line = br.readLine()) != null) {
                lines++;
                chars += line.length() + 1; // +1 за символ новой строки

                //Если при удалении лишних пробелов строка не стала пустой
                if (!line.trim().isEmpty()) {
                    String[] wordsArray = line.trim().split("\\s+");
                    words += wordsArray.length;
                }
            }
        } catch (IOException e) {
            System.err.print("Ошибка чтения файла");
        }
        System.out.printf("Символов: %d\nСлов: %d\nСтрок: %d", chars, words, lines);
    }

}
