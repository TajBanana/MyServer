import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
//        if --port arg[0], --docroot rag[1]

        int port = 3000;
        String docRoot = "static";
        List<String> argsArray = new ArrayList<>();


        if (args != null && args.length>=1) {
            argsArray.addAll(Arrays.asList(args));

            if (!(argsArray.contains("--port")) && !(argsArray.contains("--docRoot"))) {
//            System.out.println(argsArray);
                System.out.println("please input correct commands");
                return;
            }
        }

        if ((argsArray.contains("--port")) && (argsArray.contains("--docRoot"))) {
            String portString = argsArray.get(argsArray.indexOf("--port") + 1);
            port = Integer.parseInt(portString);

            String docRootInput = argsArray.get(argsArray.indexOf("--docRoot") + 1);
            docRoot = docRootInput.substring(docRootInput.indexOf(":")+1);

        } else if ((argsArray.contains("--port"))) {
            String portString = argsArray.get(argsArray.indexOf("--port") + 1);
            port = Integer.parseInt(portString);

        } else if ((argsArray.contains("--docRoot"))) {
            String docRootInput = argsArray.get(argsArray.indexOf("--docRoot") + 1);
            docRoot = docRootInput.substring(docRootInput.indexOf(":")+1);
        }


        System.out.println(docRoot);
        System.out.println(port);

//  ----------------------------------------------------------------------------------------    OPEN SERVER & PORT
        HttpServer httpServer = new HttpServer();
        httpServer.openPort(port);



 /*       ServerSocket server = new ServerSocket(3000);
        System.out.println("[SERVER] Waiting for client connection...");
        Socket serverSocket = server.accept();
        System.out.println("[SERVER] Connection established");*/



    }
}
