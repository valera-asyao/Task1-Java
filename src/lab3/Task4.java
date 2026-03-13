package lab3;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Task4 {
    public static void main(String[] args) {
        //Поиск слова в файле
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите имя файла: ");
        String filename = scanner.nextLine();
        System.out.print("Введите слово для поиска: ");
        String word = scanner.nextLine();
        word = word.toLowerCase();

        int cntFinds = 0;
        int pos = 0;

        try (BufferedReader br = new BufferedReader(new InputStreamReader(
                new FileInputStream(filename), StandardCharsets.UTF_8))) {

            String line;
            //Ищем слово в файле по строкам
            while ((line = br.readLine()) != null) {
                pos++;
                if (line.toLowerCase().contains(word)) {
                    System.out.println("Строка " + pos + ": " + line);
                    cntFinds++;
                }
            }
            if (cntFinds == 0) System.out.println("Слово не найдено.");
            else System.out.println("Всего найдено вхождений: " + cntFinds);

        } catch (IOException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }
}
/*
Введите имя файла: findWord.bin
Введите слово для поиска: Никита
Строка 1: Никита Железников
Строка 2: Железников Никита
Всего найдено вхождений: 2
 */
