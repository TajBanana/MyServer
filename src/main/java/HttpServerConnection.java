import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class HttpServerConnection implements Runnable{
    private Socket socket;
    private int port;
    private String docRoot;
    private HttpServer httpServer;

    public HttpServerConnection(int port, Socket socket, HttpServer httpServer, String docRoot) {
        this.httpServer = httpServer;
        this.socket = socket;
        this.port = port;
        this.docRoot = docRoot;

    }

    @Override
    public void run() {
        // TODO send output and close
        //            HttpWriter httpWriter = new HttpWriter(socket.getOutputStream());
        System.out.println("this is a thread");
    }
}
