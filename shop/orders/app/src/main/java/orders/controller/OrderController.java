package orders.controller;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.IOException;
import java.io.OutputStream;

public class OrderController implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) {
        try {
            String response = "Orders Service работает!";
            exchange.sendResponseHeaders(200, response.getBytes().length);
            try (OutputStream os = exchange.getResponseBody()) {
                os.write(response.getBytes());
            }
        } catch (IOException e) {
            System.err.println("Ошибка обработки запроса: " + e.getMessage());
        }
    }
}

