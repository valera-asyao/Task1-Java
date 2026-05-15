package lesson9.chat.server;

import java.io.*;
import java.net.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.*;

public class ChatServer {
    private static final int PORT = 8083;
    private static final List<ClientHandler> clients = new ArrayList<>();
    private static final ExecutorService pool = Executors.newCachedThreadPool();

    // Доп. задание 1: Пул для асинхронного логирования
    private static final ExecutorService loggerPool = Executors.newSingleThreadExecutor();
    private static final String LOG_FILE = "server_log.txt";

    // Доп. задание 4: Планировщик для проверки соединений (пинг)
    private static final ScheduledExecutorService pingerPool = Executors.newSingleThreadScheduledExecutor();
    private static final int PING_INTERVAL_SEC = 30;
    private static final int TIMEOUT_SEC = 5; // 30 сек на интервал + 10 сек на ответ

    public static void main(String[] args) {
        startPinger();

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Чат-сервер запущен на порту " + PORT);
            logMessage("Сервер запущен");

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Новый клиент: " + socket.getInetAddress());
                ClientHandler handler = new ClientHandler(socket);
                synchronized (clients) {
                    clients.add(handler);
                }
                pool.execute(handler);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            pool.shutdown();
            loggerPool.shutdown();
            pingerPool.shutdown();
        }
    }

    public static void broadcast(String message, ClientHandler sender) {
        logMessage("Сообщение от " + sender.getAddress() + ": " + message);
        synchronized (clients) {
            for (ClientHandler client : clients) {
                if (client != sender) {
                    client.sendMessage(message);
                }
            }
        }
    }

    public static void removeClient(ClientHandler client) {
        synchronized (clients) {
            clients.remove(client);
        }
        System.out.println("Клиент отключён. Всего активных: " + clients.size());
    }

    // Доп. задание 1: Асинхронное логирование
    public static void logMessage(String message) {
        loggerPool.execute(() -> {
            try (FileWriter fw = new FileWriter(LOG_FILE, true);
                 PrintWriter pw = new PrintWriter(fw)) {
                String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                pw.println("[" + timestamp + "] " + message);
            } catch (IOException e) {
                System.err.println("Ошибка записи в лог-файл: " + e.getMessage());
            }
        });
    }

    // Доп. задание 4: Рассылка пингов и отключение зависших клиентов
    private static void startPinger() {
        pingerPool.scheduleAtFixedRate(() -> {
            long currentTime = System.currentTimeMillis();
            synchronized (clients) {
                Iterator<ClientHandler> iterator = clients.iterator();
                while (iterator.hasNext()) {
                    ClientHandler client = iterator.next();
                    // Если клиент не проявлял активность дольше таймаута
                    if ((currentTime - client.getLastActivityTime()) > (TIMEOUT_SEC * 1000L)) {
                        System.out.println("Клиент " + client.getAddress() + " отключен по таймауту.");
                        client.closeSilently();
                        iterator.remove();
                    } else {
                        client.sendMessage("/ping");
                    }
                }
            }
        }, PING_INTERVAL_SEC, PING_INTERVAL_SEC, TimeUnit.SECONDS);
    }
}