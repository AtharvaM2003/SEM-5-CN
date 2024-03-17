import java.net.*;
import java.io.*;

public class UDPClient {
    public static void main(String[] args) {
        String serverAddress = "localhost";
        int serverPort = 12345;

        try (DatagramSocket socket = new DatagramSocket()) {
            InetAddress serverInetAddress = InetAddress.getByName(serverAddress);
            String message = "Hello from the client!";
            byte[] sendData = message.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverInetAddress, serverPort);
            socket.send(sendPacket);

            byte[] receiveData = new byte[1024];
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            socket.receive(receivePacket);
            String serverMessage = new String(receivePacket.getData(), 0, receivePacket.getLength());
            System.out.println("Server: " + serverMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
