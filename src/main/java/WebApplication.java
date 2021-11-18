import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

import com.sun.net.httpserver.HttpServer;

public class WebApplication {
    public static void main(String[] args) throws IOException {
        int serverPort = 8080;
        HttpServer server = HttpServer.create(new InetSocketAddress(serverPort), 0);
        server.createContext("/api/endpoint", (exchange -> {
            String message = "Hello World!";
            exchange.sendResponseHeaders(200, message.getBytes().length);
            OutputStream output = exchange.getResponseBody();
            output.write(message.getBytes());
            output.flush();
            exchange.close();
        }));
        server.setExecutor(null);
        server.start();
    }
}
