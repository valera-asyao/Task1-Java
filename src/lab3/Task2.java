package lab3;

import java.io.*;
import java.io.BufferedInputStream;
import java.util.Scanner;

public class Task2 {
    public static void main(String[] args) {
        //Копирование файла с буферизацией
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите путь к исходному файлу: ");
        String sourcePath = scanner.nextLine();
        System.out.print("Введите путь к целевому файлу: ");
        String destPath = scanner.nextLine();

        long startTime = System.currentTimeMillis();

        //Чтение и запись данных с файла
        try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(sourcePath));
             BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(destPath))) {
            byte[] buffer = new byte[8192];
            int bytesRead;
            while ((bytesRead = bis.read(buffer)) != -1) {
                bos.write(buffer, 0, bytesRead);
            }

            long endTime = System.currentTimeMillis();
            System.out.println("Размер файла: " + sourcePath.length() + " байт");
            System.out.println("Время копирования: " + (endTime - startTime) + " мс");
            System.out.println("Файл успешно скопирован!");

        } catch (IOException e) {
            System.err.print("Ошибка при копировании файла");
        }

    }
}
/*
Введите путь к исходному файлу: input.bin
Введите путь к целевому файлу: output.bin
Размер файла: 9 байт
Время копирования: 1 мс
Файл успешно скопирован!
 */