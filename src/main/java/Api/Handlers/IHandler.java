package Api.Handlers;

import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;

public interface IHandler {
    public void execute(HttpExchange exchange) throws IOException;
}
