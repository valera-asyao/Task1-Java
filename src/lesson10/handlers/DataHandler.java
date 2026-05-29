package lesson10.handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import lesson10.SimpleHttpServer;
import lesson10.utils.QueryParser;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class DataHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String method = exchange.getRequestMethod();
        Map<String, String> params = QueryParser.parse(exchange.getRequestURI().getQuery());
        String key = params.get("key");

        if (key == null || key.trim().isEmpty()) {
            SimpleHttpServer.sendError(exchange, 400, "Missing 'key' query parameter");
            return;
        }

        if ("PUT".equals(method)) {
            byte[] body;
            try (InputStream is = exchange.getRequestBody()) {
                body = is.readAllBytes();
            }
            if (body.length == 0) {
                SimpleHttpServer.sendError(exchange, 400, "Empty request body");
                return;
            }
            // Сохраняем в память
            SimpleHttpServer.dataStore.put(key, new String(body, StandardCharsets.UTF_8));
            SimpleHttpServer.sendResponse(exchange, 201, "text/plain", "Created");

        } else if ("DELETE".equals(method)) {
            // Удаляем данные и проверяем, существовал ли ключ
            if (SimpleHttpServer.dataStore.remove(key) != null) {
                exchange.sendResponseHeaders(204, -1); // 204 No Content не требует тела
            } else {
                SimpleHttpServer.sendError(exchange, 404, "Key not found");
            }

        } else {
            SimpleHttpServer.sendError(exchange, 405, "Method Not Allowed. Use PUT or DELETE.");
        }
    }
}
