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
    private ServerSocket serverSocket;
    private Socket socket;
    private int port;

    public void checkPath(String docRoot) {
        File file = new File(docRoot);
        if (file.isDirectory() && Files.isReadable(Path.of(docRoot)) && file.exists()) {
        } else {
            System.exit(1);
        }
    }
}
