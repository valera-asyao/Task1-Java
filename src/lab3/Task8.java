package lab3;

import java.io.*;

public class Task8 {
    //Сравнение скорости чтения с буфером и без
    public static void main(String[] args) throws IOException {
        String bigFile = "large_test.txt";
        System.out.print("Создание тестового файла... ");
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(bigFile))) {
            for (int i = 0; i < 300000; i++) {
                bw.write("Это тестовая строка для проверки скорости чтения. ");
            }
        }
        System.out.println("Готово (" + new File(bigFile).length() + " байт)");

        // 1. Побайтово
        long start = System.currentTimeMillis();
        try (FileInputStream fis = new FileInputStream(bigFile)) {
            while (fis.read() != -1);
        }
        long time1 = System.currentTimeMillis() - start;
        System.out.println("Чтение побайтово (FileInputStream): " + time1 + " мс");

        // 2. С буфером
        start = System.currentTimeMillis();
        try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(bigFile))) {
            byte[] buf = new byte[4096];
            while (bis.read(buf) != -1);
        }
        long time2 = System.currentTimeMillis() - start;
        System.out.println("Чтение с буфером 4096 байт: " + time2 + " мс");

        System.out.println("Вывод: буферизация быстрее в " + (time1 / Math.max(1, time2)) + " раз!");
    }
}
