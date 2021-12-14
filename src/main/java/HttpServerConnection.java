import java.io.*;
import java.net.Socket;
import java.nio.file.Files;

public class HttpServerConnection implements Runnable{
    private Socket socket;
    private int port;
    private String docRoot;
    private HttpServer httpServer;

    public HttpServerConnection(int port, Socket socket, String docRoot) {
        this.socket = socket;
        this.port = port;
        this.docRoot = docRoot;
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

//  ----------------------------------------------------------------------------------------    ACTION 1
            if (!line.contains("GET")) {
                out.writeString("HTTP/1.1 405 Method Not Allowed\r\n");
                out.writeString((""));
                String[] method = line.split(" ", 1);
                out.writeString(method + " not supported \r\n");
                out.close();
                socket.close();
                return;
            }

//  ----------------------------------------------------------------------------------------    ACTION 2
            String[] lineStrArray = line.split(" ");
//            get second word in string
            String fileName = lineStrArray[1];

//            defining new file based on input directory
            if (fileName.equals("/")) {
                fileName = "/index.html";
            }
//            add docRoot to find actual file location
            File file = new File( docRoot + fileName);
            System.out.println(docRoot + fileName);

            if (!file.exists()) {
                out.writeString("HTTP/1.1 404 Not Found\r\n");
                out.writeString("");
                out.writeString(fileName + " not found\r\n");
                out.close();
                socket.close();
                return;
            }

//  ----------------------------------------------------------------------------------------    ACTION 3
            if(file.exists()) {
                out.writeString("HTTP/1.1 200 ok\r\n");
                out.writeString("");
                byte[] fileContent = Files.readAllBytes(file.toPath());
                out.writeBytes(fileContent);
                out.close();
                socket.close();
                return;
            }

//  ----------------------------------------------------------------------------------------    ACTION 4
//            find PNG suffix
            String fileSuffix = fileName.substring(fileName.indexOf(".") + 1).toLowerCase();
            if (file.exists() && fileSuffix.equals("png")) {
                out.writeString("HTTP/1.1 200 OK\r\n");
                out.writeString("Content-Type: image/png\r\n");
                out.writeString("");
                byte[] fileContent = Files.readAllBytes(file.toPath());
                out.writeBytes(fileContent);
                out.close();
                socket.close();
                return;
            }
//
        } catch (Exception e) {
            e.printStackTrace();
        }

        // TODO send output and close
        // HttpWriter httpWriter = new HttpWriter(socket.getOutputStream());
        System.out.println("this is a thread");
    }
}
