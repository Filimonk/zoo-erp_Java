package payments;

import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;
import payments.service.AccountService;

public class PaymentsApp {
    public static void main(String[] args) throws IOException {
        int port = 8080;
        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
        server.setExecutor(Executors.newFixedThreadPool(10));
        AccountService accountService = new AccountService();
        server.createContext("/payments/signup", new AccountHandler(accountService));
        server.createContext("/payments/balance", new AccountHandler(accountService));
        server.start();
        System.out.println("Платежный сервис запущен на порту " + port);
    }
}

