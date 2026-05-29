package lesson10.handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import lesson10.SimpleHttpServer;

import java.io.IOException;

public class StatusHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        if (!"GET".equals(exchange.getRequestMethod())) {
            SimpleHttpServer.sendError(exchange, 405, "Method Not Allowed");
            return;
        }

        String json = String.format(
                "{\n\"status\": \"ok\",\n\"requests\": %d,\n\"uptime\": %d\n}",
                SimpleHttpServer.getRequestCount(),
                SimpleHttpServer.getUptimeSeconds()
        );
        SimpleHttpServer.sendResponse(exchange, 200, "application/json", json);
    }
}