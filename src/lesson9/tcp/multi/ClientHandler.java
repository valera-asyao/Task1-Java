package lesson9.tcp.multi;

import java.io.*;
import java.net.*;

public class ClientHandler implements Runnable {
    private final Socket socket;

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try (BufferedReader in = new BufferedReader(
                new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(
                     socket.getOutputStream(), true)) {
            String line;
            while ((line = in.readLine()) != null) {
                System.out.println("Получено: " + line);
                out.println("Эхо: " + line);
            }
        } catch (IOException e) {
            System.out.println("Клиент отключился: " + e.getMessage());
        } finally {
            try { socket.close(); } catch (IOException e) {}
        }
    }
}