package lesson10.handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import lesson10.SimpleHttpServer;

import java.io.IOException;
import java.io.InputStream;

public class EchoHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        if (!"POST".equals(exchange.getRequestMethod())) {
            SimpleHttpServer.sendError(exchange, 405, "Method Not Allowed");
            return;
        }

        byte[] body;
        try (InputStream is = exchange.getRequestBody()) {
            body = is.readAllBytes();
        }

        if (body.length == 0) {
            SimpleHttpServer.sendError(exchange, 400, "Empty request body");
            return;
        }

        // Для эхо-ответа можно скопировать Content-Type из запроса, если он есть
        String contentType = exchange.getRequestHeaders().getFirst("Content-Type");
        if (contentType == null) {
            contentType = "text/plain";
        }

        SimpleHttpServer.sendResponse(exchange, 200, contentType, body);
    }
}