import java.io.*;
import java.net.Socket;
import java.nio.file.Files;

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
                socket.close();
                return;
            }

//            Action 2
            String[] lineStrArray = line.split(" ");
            String fileName = lineStrArray[1];

            if (fileName.equals("/")) {
                fileName = "index.html";
            }
            File file = new File( docRoot + "/" + fileName);
            if (!file.exists()) {
                out.writeString("HTTP/1.1 404 Not Found\r\n");
                out.writeString("\r\n");
                out.writeString(fileName + " not found\r\n");
                out.close();
                socket.close();
                return;
            }

//            Action 3
            if(file.exists()) {
                out.writeString("HTTP/1.1 200 ok/r/n");
                out.writeString("\r\n");
                byte[] fileContent = Files.readAllBytes(file.toPath());
                out.writeBytes(fileContent);
                out.close();
                socket.close();
                return;
            }





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
