
import java.io.*;
import java.net.*;

public class TCPServer {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(12345)) {
            System.out.println("Server is waiting for connections on port 12345");
            Socket clientSocket = serverSocket.accept();
            
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            
            out.println("Hello from the server!");
            
            String clientMessage = in.readLine();
            System.out.println("Client: " + clientMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
