package br.com.fiap.application;

import java.io.IOException;
import java.net.InetSocketAddress;

import javax.xml.ws.Endpoint;
import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpServer;

import br.com.fiap.service.AulaService;

public class Publicador {
    public static void main(String[] args) throws IOException {
        // Define o endereço e a porta do servidor
        String url = "http://localhost:8080/aula";
        int port = 8080;

        // Cria o servidor HTTP
        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);

        // Cria um contexto para o serviço SOAP
        HttpContext context = server.createContext("/aula");

        // Adiciona o filtro CORS ao contexto
        context.getFilters().add(new CORSFilter());

        // Publica o Endpoint no contexto criado
        Endpoint.create(new AulaService()).publish(context);

        // Inicia o servidor
        server.start();
        System.out.println("Web Service publicado com CORS em: " + url + "?wsdl");
        System.out.println("Servidor iniciado na porta: " + port);
    }
}