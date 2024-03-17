import java.io.*;
import java.net.*;

public class FileTransferClient {
    public static void main(String[] args) {
        String serverAddress = "localhost";
        int serverPort = 12345;
        String filePath = "client-file.txt";

        try (Socket socket = new Socket(serverAddress, serverPort)) {
            InputStream in = socket.getInputStream();
            FileOutputStream fileOutputStream = new FileOutputStream(filePath);
            byte[] buffer = new byte[1024];
            int bytesRead;

            while ((bytesRead = in.read(buffer)) != -1) {
                fileOutputStream.write(buffer, 0, bytesRead);
            }

            System.out.println("File received and saved as " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
