package gateway;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

public class GatewayHandler implements HttpHandler {
    private static final String ORDERS_SERVICE_URL = System.getenv("ORDERS_SERVICE_URL");
    private static final String PAYMENTS_SERVICE_URL = System.getenv("PAYMENTS_SERVICE_URL");
    private static final int BUFFER_SIZE = 4096;

    @Override
    public void handle(HttpExchange exchange) {
        try {
            String path = exchange.getRequestURI().getPath();
            String method = exchange.getRequestMethod();
            System.out.println("Обработка запроса: " + method + " " + path);

            String targetUrl = getTargetServiceUrl(path);
            if (targetUrl == null) {
                sendErrorResponse(exchange, 404, "Сервис не найден");
                System.out.println("Обработка запроса " + method + " " + path + " завершена со статусом 404 - Сервис не найдет");
                return;
            }

            String fullUrl = targetUrl + path;
            System.out.println("Перенаправление на: " + fullUrl);
            forwardRequest(exchange, fullUrl);

            System.out.println("Обработка запроса " + method + " " + path + " завершена");
        } catch (Exception e) {
            System.err.println("Ошибка при обработке запроса: " + e.getMessage());
            sendErrorResponse(exchange, 500, "Внутренняя ошибка сервера");
        }
    }

    private String getTargetServiceUrl(String path) {
        if (path.startsWith("/orders")) {
            return ORDERS_SERVICE_URL;
        } else if (path.startsWith("/payments")) {
            return PAYMENTS_SERVICE_URL;
        }
        return null;
    }

    private void forwardRequest(HttpExchange exchange, String targetUrl) {
        HttpURLConnection connection = null;
        try {
            URL url = new URL(targetUrl);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod(exchange.getRequestMethod());
            connection.setDoOutput(true);

            // Копируем заголовки запроса
            for (Map.Entry<String, List<String>> header : exchange.getRequestHeaders().entrySet()) {
                String key = header.getKey();
                if (!key.equalsIgnoreCase("Content-Length") && !key.equalsIgnoreCase("Host")) {
                    for (String value : header.getValue()) {
                        connection.addRequestProperty(key, value);
                    }
                }
            }

            // Отправляем тело запроса, если есть
            if (exchange.getRequestBody() != null &&
                ("POST".equalsIgnoreCase(exchange.getRequestMethod()) ||
                 "PUT".equalsIgnoreCase(exchange.getRequestMethod()))) {
                try (OutputStream os = connection.getOutputStream();
                     InputStream is = exchange.getRequestBody()) {
                    byte[] buffer = new byte[BUFFER_SIZE];
                    int bytesRead;
                    while ((bytesRead = is.read(buffer)) != -1) {
                        os.write(buffer, 0, bytesRead);
                    }
                }
            }

            // Получаем код ответа
            int responseCode = connection.getResponseCode();

            // Копируем заголовки ответа
            for (Map.Entry<String, List<String>> header : connection.getHeaderFields().entrySet()) {
                if (header.getKey() != null) {
                    exchange.getResponseHeaders().put(header.getKey(), header.getValue());
                }
            }

            // Отправляем заголовки ответа клиенту
            exchange.sendResponseHeaders(responseCode, 0);

            // Определяем правильный поток для чтения (input/error)
            InputStream responseStream = responseCode < 400 ? 
                connection.getInputStream() : connection.getErrorStream();

            // Копируем тело ответа
            try (OutputStream os = exchange.getResponseBody();
                 InputStream is = responseStream) {
                byte[] buffer = new byte[BUFFER_SIZE];
                int bytesRead;
                while ((bytesRead = is.read(buffer)) != -1) {
                    os.write(buffer, 0, bytesRead);
                }
            }
        } catch (Exception e) {
            System.err.println("Ошибка при перенаправлении запроса: " + e.getMessage());
            sendErrorResponse(exchange, 500, "Ошибка шлюза: " + e.getMessage());
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }

    private void sendErrorResponse(HttpExchange exchange, int statusCode, String response) {
        try {
            exchange.getResponseHeaders().set("Content-Type", "text/plain; charset=utf-8");
            byte[] bytes = response.getBytes(StandardCharsets.UTF_8);
            exchange.sendResponseHeaders(statusCode, bytes.length);
            try (OutputStream os = exchange.getResponseBody()) {
                os.write(bytes);
            }
        } catch (IOException e) {
            System.err.println("Ошибка при отправке ответа: " + e.getMessage());
        }
    }
}

