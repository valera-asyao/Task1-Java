package lab3;

import java.io.*;
import java.nio.ByteBuffer;
import java.util.Random;

public class Task1 {
    // Чтение и запись байтов
    public static void main(String[] args) {
        String filename = "input.bin";
        Random random = new Random();
        int count = 100;

        // Запись 100 случайных целых чисел
        try (FileOutputStream fos = new FileOutputStream(filename)) {
            ByteBuffer buffer = ByteBuffer.allocate(4); // Буфер на 4 байта для одного int

            for (int i = 0; i < count; i++) {
                int num = random.nextInt(1000);
                buffer.putInt(0, num); // Кладём число в буфер
                fos.write(buffer.array()); // Записываем массив из 4 байтов в файл
            }
            System.out.println("Записано 100 чисел в " + filename);
        } catch (IOException e) {
            System.err.println("Ошибка при записи: " + e.getMessage());
        }

        // Чтение из файла
        try (FileInputStream fis = new FileInputStream(filename)) {
            System.out.println("Прочитанные числа:");
            byte[] bytes = new byte[4]; // Массив для чтения одного числа

            while (fis.read(bytes) != -1) {
                // Превращаем 4 байта обратно в int
                int num = ByteBuffer.wrap(bytes).getInt();
                System.out.print(num + " ");
            }
            System.out.println("\nЧтение завершено.");
        } catch (IOException e) {
            System.err.println("Ошибка при чтении: " + e.getMessage());
        }
    }
}