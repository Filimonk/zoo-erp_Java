package orders;

import com.sun.net.httpserver.HttpServer;
import orders.controller.OrderController;
import java.io.IOException;
import java.net.InetSocketAddress;

public class OrdersApp {
    public static void main(String[] args) {
        try {
            HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
            server.createContext("/orders", new OrderController());
            server.start();
            System.out.println("Orders Service запущен на порту 8080");
        } catch (IOException e) {
            System.err.println("Ошибка запуска сервера: " + e.getMessage());
        }
    }
}

