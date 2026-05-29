package lesson10;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import lesson10.handlers.*;
import lesson10.utils.AsyncLogger;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;

public class SimpleHttpServer {
    private static final int PORT = 8080;

    // Глобальное потокобезопасное состояние
    private static final AtomicLong requestsCount = new AtomicLong(0);
    private static final long startTime = System.currentTimeMillis();
    public static final Map<String, String> dataStore = new ConcurrentHashMap<>();

    public static void main(String[] args) {
        try {
            HttpServer server = HttpServer.create(new InetSocketAddress(PORT), 0);

            // Регистрация эндпоинтов через обертку для логирования и обработки ошибок
            server.createContext("/hello", wrap(new HelloHandler()));
            server.createContext("/status", wrap(new StatusHandler()));
            server.createContext("/echo", wrap(new EchoHandler()));
            server.createContext("/data", wrap(new DataHandler()));

            server.setExecutor(Executors.newCachedThreadPool());
            server.start();

            System.out.println("Сервер запущен на порту " + PORT);
            System.out.println("Логи пишутся в access.log");
        } catch (IOException e) {
            System.err.println("Не удалось запустить сервер: " + e.getMessage());
        }
    }

    // Метод-обертка для выполнения общих требований ко всем запросам
    private static HttpHandler wrap(HttpHandler targetHandler) {
        return exchange -> {
            requestsCount.incrementAndGet(); // Считаем абсолютно все запросы
            long start = System.currentTimeMillis();
            try {
                targetHandler.handle(exchange);
            } catch (Exception e) {
                e.printStackTrace();
                sendError(exchange, 500, "Internal Server Error: " + e.getMessage());
            } finally {
                long duration = System.currentTimeMillis() - start;
                int code = exchange.getResponseCode();
                if (code == -1) code = 500; // Если ответ так и не был отправлен

                AsyncLogger.log(exchange.getRequestMethod(), exchange.getRequestURI().toString(), code, duration);
                exchange.close();
            }
        };
    }

    public static long getRequestCount() {
        return requestsCount.get();
    }

    public static long getUptimeSeconds() {
        return (System.currentTimeMillis() - startTime) / 1000;
    }

    public static void sendResponse(HttpExchange exchange, int statusCode, String contentType, String response) throws IOException {
        byte[] bytes = response.getBytes(StandardCharsets.UTF_8);
        sendResponse(exchange, statusCode, contentType, bytes);
    }

    public static void sendResponse(HttpExchange exchange, int statusCode, String contentType, byte[] responseBytes) throws IOException {
        exchange.getResponseHeaders().set("Content-Type", contentType + "; charset=UTF-8");
        exchange.sendResponseHeaders(statusCode, responseBytes.length);
        if (responseBytes.length > 0) {
            try (OutputStream os = exchange.getResponseBody()) {
                os.write(responseBytes);
            }
        }
    }

    public static void sendError(HttpExchange exchange, int statusCode, String message) throws IOException {
        sendResponse(exchange, statusCode, "text/plain", message);
    }
}