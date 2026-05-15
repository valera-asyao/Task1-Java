package lesson9.tcp.echo;

import java.io.*;
import java.net.*;

public class EchoClient {
    private static final String HOST = "localhost";
    private static final int PORT = 8081;

    public static void main(String[] args) {
        try (Socket socket = new Socket(HOST, PORT);
             BufferedReader in = new BufferedReader(
                     new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(
                     socket.getOutputStream(), true);
             BufferedReader console = new BufferedReader(
                     new InputStreamReader(System.in))) {
            System.out.println("Подключено к серверу. Введите сообщение (exit для выхода):");
            String userInput;
            while ((userInput = console.readLine()) != null) {
                if ("exit".equalsIgnoreCase(userInput)) break;
                out.println(userInput);
                System.out.println("Ответ сервера: " + in.readLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}