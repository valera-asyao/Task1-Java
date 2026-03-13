package lab3;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class Task7 {
    // Шифр Цезаря для файл
    public static void main(String[] args) {
        String input = "input.txt";
        String output = "encrypted.txt";
        int shift = 3;

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(input), StandardCharsets.UTF_8));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(output), StandardCharsets.UTF_8))) {

            int c;
            while ((c = reader.read()) != -1) {
                writer.write(cipher((char) c, shift));
            }
            System.out.println("Шифрование завершено.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static char cipher(char c, int shift) {
        if (!Character.isLetter(c)) return c;

        char start = Character.isLowerCase(c) ? 'а' : 'А';
        int alphabetSize = 32; // Упрощенно для кириллицы без 'ё'

        // Проверка на латиницу
        if (c >= 'a' && c <= 'z' || c >= 'A' && c <= 'Z') {
            start = Character.isLowerCase(c) ? 'a' : 'A';
            alphabetSize = 26;
        }

        return (char) (start + (c - start + shift + alphabetSize) % alphabetSize);
    }
}
