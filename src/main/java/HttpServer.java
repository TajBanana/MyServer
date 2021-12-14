import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HttpServer {
    private Socket socket;
    private int port;

    public Socket getSocket() {
        return socket;
    }

    public HttpServer() throws IOException {
    }

    public boolean checkDir() {
        return false;
    }

    public void openPort(int port) throws IOException {
        ServerSocket httpServer = new ServerSocket(port);
        System.out.println("[SERVER] Waiting for client connection...");
        this.socket = httpServer.accept();
        System.out.println("[SERVER] Connection established");
    }

    public void checkPath(String docRoot) {
        File file = new File(docRoot);
        if (file.isDirectory() && Files.isReadable(Path.of(docRoot)) && file.exists()) {
            System.out.println("is a directory: " + file.isDirectory());
            System.out.println("is readable: " + Files.isReadable(Path.of(docRoot)));
            System.out.println("exists: " + file.exists());
        } else {
            System.exit(1);
        }
    }
}
