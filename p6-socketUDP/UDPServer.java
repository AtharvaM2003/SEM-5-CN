import java.net.*;
import java.io.*;

public class UDPServer {
    public static void main(String[] args) {
        int port = 12345;
        try (DatagramSocket socket = new DatagramSocket(port)) {
            byte[] receiveData = new byte[1024];
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            socket.receive(receivePacket);
            String clientMessage = new String(receivePacket.getData(), 0, receivePacket.getLength());
            System.out.println("Client: " + clientMessage);

            // Reply to the client
            InetAddress clientAddress = receivePacket.getAddress();
            int clientPort = receivePacket.getPort();
            String replyMessage = "Hello from the server!";
            byte[] sendData = replyMessage.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, clientAddress, clientPort);
            socket.send(sendPacket);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
