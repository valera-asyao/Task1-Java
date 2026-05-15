package lesson9.udp;

import java.net.*;
import java.io.*;

public class UdpEchoClient {
    private static final String HOST = "localhost";
    private static final int PORT = 8082;

    public static void main(String[] args) throws Exception {
        try (DatagramSocket socket = new DatagramSocket();
             BufferedReader console = new BufferedReader(new InputStreamReader(System.in))) {
            InetAddress address = InetAddress.getByName(HOST);
            System.out.println("UDP клиент запущен. Введите сообщение (exit для выхода):");
            String line;
            while ((line = console.readLine()) != null) {
                if ("exit".equalsIgnoreCase(line)) break;
                byte[] data = line.getBytes();
                DatagramPacket packet = new DatagramPacket(data, data.length, address, PORT);
                socket.send(packet);
                // Ждём ответ
                byte[] buffer = new byte[1024];
                DatagramPacket response = new DatagramPacket(buffer, buffer.length);
                socket.receive(response);
                String echo = new String(response.getData(), 0, response.getLength());
                System.out.println("Ответ от сервера: " + echo);
            }
        }
    }
}