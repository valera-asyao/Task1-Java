package lesson10.handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import lesson10.SimpleHttpServer;
import lesson10.utils.QueryParser;

import java.io.IOException;
import java.util.Map;

public class HelloHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        if (!"GET".equals(exchange.getRequestMethod())) {
            SimpleHttpServer.sendError(exchange, 405, "Method Not Allowed");
            return;
        }

        String query = exchange.getRequestURI().getQuery();
        Map<String, String> params = QueryParser.parse(query);

        // Если параметра name нет вообще (дополнительные параметры игнорируются)
        if (!params.containsKey("name")) {
            SimpleHttpServer.sendResponse(exchange, 200, "text/plain", "Hello, World!");
            return;
        }

        String name = params.get("name");
        // Если параметр передан, но он пустой (?name=)
        if (name.trim().isEmpty()) {
            SimpleHttpServer.sendError(exchange, 400, "Missing or empty name parameter");
            return;
        }

        // Успешный ответ
        SimpleHttpServer.sendResponse(exchange, 200, "text/plain", "Hello, " + name + "!");
    }
}