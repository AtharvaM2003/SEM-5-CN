import java.io.*;
import java.net.*;

public class FileTransferServer {
    public static void main(String[] args) {
        int port = 12345;
        String filePath = "server-file.txt";

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server is waiting for connections on port " + port);
            Socket clientSocket = serverSocket.accept();

            FileInputStream fileInputStream = new FileInputStream(filePath);
            OutputStream out = clientSocket.getOutputStream();
            byte[] buffer = new byte[1024];
            int bytesRead;

            while ((bytesRead = fileInputStream.read(buffer)) != -1) {
                out.write(buffer, 0, bytesRead);
            }

            System.out.println("File sent to the client");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
