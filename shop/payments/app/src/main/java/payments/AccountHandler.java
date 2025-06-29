package payments;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import payments.domain.Account;
import payments.service.AccountService;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.math.BigDecimal;

public class AccountHandler implements HttpHandler {
    private final AccountService accountService;
    private final Gson gson = new Gson();

    public AccountHandler(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
        exchange.getResponseHeaders().add("Access-Control-Allow-Headers", "*,Content-Type");
        exchange.getResponseHeaders().add("Access-Control-Allow-Methods", "GET,POST,OPTIONS");

        if ("OPTIONS".equalsIgnoreCase(exchange.getRequestMethod())) {
            exchange.sendResponseHeaders(204, -1);
            return;
        }

        String method = exchange.getRequestMethod();
        String path = exchange.getRequestURI().getPath();

        try {
            if ("POST".equalsIgnoreCase(method) && "/payments/signup".equals(path)) {
                handleSignup(exchange);
            } else if ("GET".equalsIgnoreCase(method) && path.startsWith("/payments/balance")) {
                handleBalance(exchange);
            } else if ("POST".equalsIgnoreCase(method) && "/payments/deposit".equals(path)) {
                handleDeposit(exchange);
            } else {
                String errJson = String.format(
                        "{\"error\": \"Путь %s не поддерживается для метода %s\", \"status\": 404}\n", path, method);
                sendJson(exchange, 404, errJson);
            }
        } catch (Exception e) {
            e.printStackTrace();
            sendJson(exchange, 500, "{\"error\":\"Внутренняя ошибка сервера\"}\n");
        }
    }

    private void handleSignup(HttpExchange exchange) throws IOException {
        InputStream is = exchange.getRequestBody();
        String requestBody = new String(is.readAllBytes(), StandardCharsets.UTF_8);
        SignupRequest req = gson.fromJson(requestBody, SignupRequest.class);

        if (req == null || req.username == null || req.username.isBlank()) {
            sendJson(exchange, 400, "{\"error\":\"Не указано имя пользователя\"}\n");
            return;
        }

        Account acc = accountService.createAccount(req.username.trim());
        if (acc == null) {
            sendJson(exchange, 409, "{\"error\":\"Пользователь уже существует\"}\n");
        } else {
            sendJson(exchange, 201, gson.toJson(acc) + "\n");
        }
    }

    private void handleBalance(HttpExchange exchange) throws IOException {
        String query = exchange.getRequestURI().getQuery();
        String requested = null;
        if (query != null) {
            for (String p : query.split("&")) {
                if (p.startsWith("username=")) {
                    requested = java.net.URLDecoder.decode(p.substring("username=".length()), StandardCharsets.UTF_8);
                }
            }
        }

        if (requested == null || requested.isBlank()) {
            sendJson(exchange, 400, "{\"error\":\"Не передано имя пользователя\"}\n");
            return;
        }
        Account acc = accountService.getAccountByUsername(requested.trim());
        if (acc == null) {
            sendJson(exchange, 404, "{\"error\":\"Пользователь не найден\"}\n");
            return;
        }
        String resp = gson.toJson(new BalanceResponse(acc.getUsername(), acc.getBalance()));
        sendJson(exchange, 200, resp + "\n");
    }

    private void handleDeposit(HttpExchange exchange) throws IOException {
        InputStream is = exchange.getRequestBody();
        String requestBody = new String(is.readAllBytes(), StandardCharsets.UTF_8);
        DepositRequest req = gson.fromJson(requestBody, DepositRequest.class);

        if (req == null || req.username == null || req.username.isBlank() || req.amount == null || req.amount.compareTo(BigDecimal.ZERO) <= 0) {
            sendJson(exchange, 400, "{\"error\":\"Некорректные параметры для пополнения\"}\n");
            return;
        }

        BigDecimal newBalance = accountService.depositToAccount(req.username.trim(), req.amount);
        if (newBalance == null) {
            sendJson(exchange, 404, "{\"error\":\"Пользователь не найден\"}\n");
        } else {
            sendJson(exchange, 200, String.format("{\"username\":\"%s\",\"newBalance\":%s}\n", req.username.trim(), newBalance.stripTrailingZeros().toPlainString()));
        }
    }

    private void sendJson(HttpExchange exchange, int status, String json) throws IOException {
        byte[] bytes = json.getBytes(StandardCharsets.UTF_8);
        exchange.getResponseHeaders().set("Content-Type", "application/json; charset=utf-8");
        exchange.sendResponseHeaders(status, bytes.length);
        try (OutputStream os = exchange.getResponseBody()) {
            os.write(bytes);
        }
    }

    private static class SignupRequest { public String username; }
    private static class DepositRequest { public String username; public BigDecimal amount; }
    private static class BalanceResponse {
        String username;
        java.math.BigDecimal balance;
        BalanceResponse(String username, java.math.BigDecimal balance) {
            this.username = username;
            this.balance = balance;
        }
    }
}

