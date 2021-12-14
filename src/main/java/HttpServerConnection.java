import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class HttpServerConnection implements Runnable{
    private Socket socket;
    private int port;
    private String docRoot;
    private HttpServer httpServer;

    public HttpServerConnection(int port, Socket socket) {
        this.socket = socket;
        this.port = port;
    }

    @Override
    public void run() {
        BufferedReader in = null;
        HttpWriter out = null;
        String line = "";


        try {
            BufferedInputStream bis = new BufferedInputStream(socket.getInputStream());
            in = new BufferedReader(new InputStreamReader(bis));
            out = new HttpWriter(socket.getOutputStream());

            line = in.readLine();

//            Action 1
            if (!line.contains("GET")) {
                out.writeString("HTTP/1.1 405 Method Not Allowed\r\n");
                out.writeString(("\r\n"));
                String[] method = line.split(" ", 1);
                out.writeString(method + " not supported \r\n");
                out.close();
            }

//            Action 2
            String[] lineStrArray = line.split(" ");
            String index = lineStrArray[1];

            if (index.equals("/")) {
                index = "index.html";
            }
            File indexFile = new File("static/" + index);
            if (!indexFile.exists()) {
                out.writeString("HTTP/1.1 404 Not Found\r\n");
                out.writeString("\r\n");
                out.writeString(index + " not found\r\n");
            }

//            Action 3








        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // TODO send output and close
        // HttpWriter httpWriter = new HttpWriter(socket.getOutputStream());
        System.out.println("this is a thread");
    }
}
