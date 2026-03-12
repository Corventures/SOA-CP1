package br.com.fiap.application;

import java.io.IOException;
import java.net.InetSocketAddress;

import javax.xml.ws.Endpoint;
import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpServer;

import br.com.fiap.service.AulaService;

public class Publicador {
    public static void main(String[] args) throws IOException {
        String url = "http://localhost:8080/aula";
        int port = 8080;

        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
        HttpContext context = server.createContext("/aula");

        context.getFilters().add(new CORSFilter());

        Endpoint.create(new AulaService()).publish(context);

        server.start();
        System.out.println("Web Service publicado com CORS em: " + url + "?wsdl");
        System.out.println("Servidor iniciado na porta: " + port);
    }
}