package br.com.fiap.application;

import com.sun.net.httpserver.Filter;
import com.sun.net.httpserver.HttpExchange;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class CORSFilter extends Filter {

    private static final String ALLOWED_METHODS = "GET, POST, PUT, DELETE, OPTIONS, HEAD";
    private static final Set<String> ALLOWED_HEADERS = new HashSet<>(Arrays.asList(
            "Content-Type",
            "Accept",
            "X-Requested-With",
            "SOAPAction"));

    @Override
    public void doFilter(HttpExchange exchange, Chain chain) throws IOException {
        // Permite requisições de qualquer origem. Em produção, substitua "*" por um domínio específico.
        exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");

        if (exchange.getRequestMethod().equalsIgnoreCase("OPTIONS")) {
            exchange.getResponseHeaders().add("Access-Control-Allow-Methods", ALLOWED_METHODS);
            
            String requestedHeaders = exchange.getRequestHeaders().getFirst("Access-Control-Request-Headers");
            if (requestedHeaders != null) {
                exchange.getResponseHeaders().add("Access-Control-Allow-Headers", requestedHeaders);
            } else {
                 exchange.getResponseHeaders().add("Access-Control-Allow-Headers", String.join(", ", ALLOWED_HEADERS));
            }

            exchange.sendResponseHeaders(204, -1); // No Content
            return;
        }

        chain.doFilter(exchange);
    }

    @Override
    public String description() {
        return "CORS filter to handle pre-flight and cross-origin requests";
    }
}