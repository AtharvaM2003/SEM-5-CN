import java.io.*;
import java.net.*;

public class UDPClient {
    public static void main(String[] args) {
        String serverHost = "127.0.0.1";
        int serverPort = 12345;

        // Specify the file to be sent
        String fileName = "sample.txt"; // Change this to the file you want to send

        try {
            DatagramSocket socket = new DatagramSocket();
            InetAddress serverAddress = InetAddress.getByName(serverHost);

            // Send the file name to the server
            byte[] fileNameBytes = fileName.getBytes();
            DatagramPacket fileNamePacket = new DatagramPacket(fileNameBytes, fileNameBytes.length, serverAddress, serverPort);
            socket.send(fileNamePacket);

            // Open the file to send
            File fileToSend = new File(fileName);
            FileInputStream fileInputStream = new FileInputStream(fileToSend);
            byte[] buffer = new byte[1024];
            int bytesRead;

            while ((bytesRead = fileInputStream.read(buffer)) != -1) {
                DatagramPacket packet = new DatagramPacket(buffer, bytesRead, serverAddress, serverPort);
                socket.send(packet);
            }

            // Send an empty packet to indicate the end of the file
            byte[] endOfFile = new byte[0];
            DatagramPacket endPacket = new DatagramPacket(endOfFile, endOfFile.length, serverAddress, serverPort);
            socket.send(endPacket);

            fileInputStream.close();
            socket.close();
            System.out.println("File sent: " + fileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
