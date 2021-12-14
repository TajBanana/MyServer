import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class HttpServer {
    private ServerSocket httpServer;
    private Socket serverSocket;
    private int port;


    public boolean checkDir() {
        return false;
    }

    public void openPort(int port) throws IOException {
        ServerSocket myServer = new ServerSocket(3000);
        System.out.println("[SERVER] Waiting for client connection...");
        Socket serverSocket = myServer.accept();
        System.out.println("[SERVER] Connection established");
    }

    public void checkPath(String docRoot) {

    }

}
