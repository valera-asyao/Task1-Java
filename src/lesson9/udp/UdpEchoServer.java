package lesson9.udp;

import java.net.*;

public class UdpEchoServer {
    private static final int PORT = 8082;
    private static final int BUFFER_SIZE = 1024;

    public static void main(String[] args) throws Exception {
        try (DatagramSocket socket = new DatagramSocket(PORT)) {
            byte[] buffer = new byte[BUFFER_SIZE];
            System.out.println("UDP эхо-сервер запущен на порту " + PORT);
            while (true) {
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                socket.receive(packet);
                String received = new String(packet.getData(), 0, packet.getLength());
                System.out.println("Получено: " + received);
                // Отправляем обратно тому же отправителю
                DatagramPacket response = new DatagramPacket(
                        packet.getData(), packet.getLength(),
                        packet.getAddress(), packet.getPort());
                socket.send(response);
            }
        }
    }
}