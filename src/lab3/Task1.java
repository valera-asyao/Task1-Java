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
/*
Записано 100 чисел в input.bin
Прочитанные числа:
274 687 724 921 751 47 310 970 596 0 403 234 434 657 774 818 221 216 425 570 808 831 909 344 23 302 828 32 85 357 138 816 486 3 970 782 767 368 293 664 418 555 117 560 237 17 607 346 9 555 423 859 929 666 590 289 977 778 990 65 253 232 902 761 171 265 952 538 361 305 408 809 192 605 264 986 299 973 598 401 56 225 759 855 708 143 228 814 438 7 666 362 441 971 625 867 517 669 66 882
Чтение завершено.
 */