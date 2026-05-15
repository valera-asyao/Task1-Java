package lesson9.chat.server;

import java.io.*;
import java.net.*;

public class ClientHandler implements Runnable {
    private final Socket socket;
    private PrintWriter out;
    private BufferedReader in;
    private volatile long lastActivityTime; // Время последней активности

    public ClientHandler(Socket socket) {
        this.socket = socket;
        this.lastActivityTime = System.currentTimeMillis();
    }

    @Override
    public void run() {
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
            String message;

            while ((message = in.readLine()) != null) {
                lastActivityTime = System.currentTimeMillis(); // Обновляем время активности

                // Доп. задание 4: Обрабатываем системный ответ на пинг
                if ("/pong".equalsIgnoreCase(message)) {
                    continue;
                }

                System.out.println("Сообщение от " + socket.getInetAddress() + ": " + message);
                ChatServer.broadcast(message, this);
            }
        } catch (IOException e) {
            System.out.println("Клиент " + getAddress() + " разорвал соединение");
        } finally {
            closeSilently();
            ChatServer.removeClient(this);
        }
    }

    public void sendMessage(String msg) {
        if (out != null) {
            out.println(msg);
        }
    }

    public long getLastActivityTime() {
        return lastActivityTime;
    }

    public String getAddress() {
        return socket.getInetAddress().toString();
    }

    public void closeSilently() {
        try { if (in != null) in.close(); } catch (IOException e) {}
        try { if (out != null) out.close(); } catch (Exception e) {}
        try { if (socket != null) socket.close(); } catch (IOException e) {}
    }
}