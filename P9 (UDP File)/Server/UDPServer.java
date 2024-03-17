import java.io.FileOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UDPServer {
    public static void main(String[] args) {
        int port = 12345;
        try {
            DatagramSocket socket = new DatagramSocket(port);
            System.out.println("Server is listening on port " + port);

            byte[] buffer = new byte[1024];

            // Create a DatagramPacket to receive data
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);

            // Receive the file name from the client
            socket.receive(packet);
            String fileName = new String(packet.getData(), 0, packet.getLength());

            System.out.println("Receiving file: " + fileName);

            // Create a FileOutputStream to write the received file
            FileOutputStream fileOutputStream = new FileOutputStream(fileName);

            while (true) {
                socket.receive(packet);
                int bytesRead = packet.getLength();

                if (bytesRead <= 0) {
                    break; // End of file
                }

                // Write the received data to the file
                fileOutputStream.write(packet.getData(), 0, bytesRead);
            }

            fileOutputStream.close();
            socket.close();
            System.out.println("File received and saved as: " + fileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
