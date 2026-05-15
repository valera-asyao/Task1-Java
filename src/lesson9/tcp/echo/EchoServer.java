package lesson9.tcp.echo;

import java.io.*;
import java.net.*;

public class EchoServer {
    private static final int PORT = 8081;

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Эхо-сервер запущен на порту " + PORT);
            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Клиент подключён: " + clientSocket.getInetAddress());
                try (BufferedReader in = new BufferedReader(
                        new InputStreamReader(clientSocket.getInputStream()));
                     PrintWriter out = new PrintWriter(
                             clientSocket.getOutputStream(), true)) {
                    String line;
                    while ((line = in.readLine()) != null) {
                        System.out.println("Получено: " + line);
                        out.println("Эхо: " + line);
                    }
                } catch (IOException e) {
                    System.out.println("Клиент отключился");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}