package br.com.fiap.application;

import javax.xml.ws.Endpoint;

import br.com.fiap.service.AulaService;

public class Publicador {
    public static void main(String[] args) {
        Endpoint.publish(
                "http://localhost:8080/aula",
                new AulaService());
        System.out.println("Web Service publicado!");
    }
}
