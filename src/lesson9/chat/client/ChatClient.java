package lesson9.chat.client;

import java.io.*;
import java.net.*;

public class ChatClient {
    private static final String HOST = "localhost";
    private static final int PORT = 8083;

    public static void main(String[] args) {
        try (Socket socket = new Socket(HOST, PORT);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader console = new BufferedReader(new InputStreamReader(System.in))) {

            System.out.println("Подключено к чат-серверу. Введите сообщения (/exit для выхода)");

            // Поток для чтения сообщений от сервера
            Thread readerThread = new Thread(() -> {
                try {
                    String serverMsg;
                    while ((serverMsg = in.readLine()) != null) {
                        // Доп. задание 4: Скрытый ответ на пинг сервера
                        if ("/ping".equals(serverMsg)) {
                            out.println("/pong");
                        } else {
                            System.out.println(serverMsg);
                        }
                    }
                } catch (IOException e) {
                    System.out.println("Соединение с сервером потеряно");
                }
            });
            readerThread.setDaemon(true); // Устанавливаем как демон, чтобы не блокировал завершение
            readerThread.start();

            // Главный поток для отправки сообщений
            String userInput;
            while ((userInput = console.readLine()) != null) {
                if ("/exit".equalsIgnoreCase(userInput)) {
                    break;
                }
                out.println(userInput);
            }
        } catch (UnknownHostException e) {
            System.err.println("Неизвестный хост: " + e.getMessage());
        } catch (SocketException e) {
            System.err.println("Ошибка сокета: " + e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}