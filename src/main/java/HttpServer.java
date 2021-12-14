import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;

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
