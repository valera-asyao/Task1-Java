package lesson9.tcp.multi;

import java.io.*;
import java.net.*;
import java.util.concurrent.*;

public class MultiThreadEchoServer {
    private static final int PORT = 8081;
    private static final int THREAD_POOL_SIZE = 10;

    public static void main(String[] args) {
        ExecutorService pool = Executors.newFixedThreadPool(THREAD_POOL_SIZE);
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Многопоточный эхо-сервер запущен на порту " + PORT);
            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Клиент подключён: " + clientSocket.getInetAddress());
                pool.submit(new ClientHandler(clientSocket));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            pool.shutdown();
        }
    }
}