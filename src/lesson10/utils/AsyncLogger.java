package lesson10.utils;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AsyncLogger {
    // Однопоточный Executor для безопасной последовательной записи в один файл
    private static final ExecutorService executor = Executors.newSingleThreadExecutor();
    private static final String LOG_FILE = "access.log";
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");

    public static void log(String method, String url, int statusCode, long durationMs) {
        String timestamp = LocalDateTime.now().format(formatter);
        String logLine = String.format("[%s] %s %s %d %dms", timestamp, method, url, statusCode, durationMs);

        executor.submit(() -> {
            System.out.println(logLine); // Опциональный вывод в консоль
            try (FileWriter fw = new FileWriter(LOG_FILE, true);
                 PrintWriter pw = new PrintWriter(fw)) {
                pw.println(logLine);
            } catch (IOException e) {
                System.err.println("Ошибка записи лога: " + e.getMessage());
            }
        });
    }
}