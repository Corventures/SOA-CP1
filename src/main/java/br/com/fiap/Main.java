package br.com.fiap;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import br.com.fiap.dto.Aula;
import br.com.fiap.enums.DiaDaSemana;
import br.com.fiap.enums.StatusAula;
import br.com.fiap.enums.TipoAula;
import br.com.fiap.service.IAulaService;

public class Main {
    public static void main(String[] args) throws Exception {
        IAulaService aulaService = getAulaService();

        System.out.println("Conexão estabelecida com sucesso!\n");

        printTitulo("Testando Cadastro");
        Aula aulaSOA = criarAulaSOA();
        Aula aulaOS = criarAulaOS();

        Aula aulaCadastradaSOA = aulaService.cadastrar(aulaSOA);
        Aula aulaCadastradaOS = aulaService.cadastrar(aulaOS);

        System.out.println("Aulas cadastradas com sucesso! IDs gerados: " + aulaCadastradaSOA.getId() + " e "
                + aulaCadastradaOS.getId());
        printTitulo("Testando Listagem de Todas as Aulas");
        printAulas(aulaService.listarTodas());

        printTitulo("Testando Busca por Dia da Semana (" + aulaOS.getDiaDaSemana() + ")");
        printAulas(aulaService.listarPorDia(aulaOS.getDiaDaSemana()));

        printTitulo("Testando Atualização");
        Aula aulaParaAtualizar = aulaService.buscarPorId(aulaCadastradaSOA.getId());
        if (aulaParaAtualizar != null) {
            aulaParaAtualizar.setSala("202");
            aulaParaAtualizar.setStatus(StatusAula.MUDANCA_DE_SALA);
            aulaParaAtualizar.setTipo(TipoAula.HIBRIDO);
            Aula aulaAtualizada = aulaService.atualizar(aulaParaAtualizar);
            System.out.println("Aula atualizada com sucesso:");
            printAula(aulaAtualizada);
        }

        printTitulo("Testando Exclusão");
        boolean foiExcluida = aulaService.excluir(aulaCadastradaOS.getId());
        System.out.println("Aula com ID " + aulaCadastradaOS.getId() + " excluída: " + foiExcluida);

        printTitulo("Listagem Final");
        printAulas(aulaService.listarTodas());

        // Apenas para limpar a aula de teste, para testar novamente sem precisar reiniciar o servidor
        aulaService.excluir(aulaCadastradaSOA.getId());
    }

    private static IAulaService getAulaService() throws MalformedURLException {
        URL url = URI.create("http://localhost:8080/aula?wsdl").toURL();
        QName qname = new QName("http://service.fiap.com.br/", "AulaService");
        Service service = Service.create(url, qname);
        IAulaService aulaService = service.getPort(IAulaService.class);
        return aulaService;
    }

    private static Aula criarAulaSOA() {
        Aula aula = new Aula();
        aula.setDisciplina("Arquitetura Orientada a Serviços (SOA)");
        aula.setProfessor("Salatiel Luz Marinho");
        aula.setSala("105");
        aula.setTipo(TipoAula.PRESENCIAL);
        aula.setStatus(StatusAula.CONFIRMADA);
        aula.setDiaDaSemana(DiaDaSemana.SEGUNDA);
        aula.setHorarioInicio("19:00");
        aula.setHorarioFim("21:00");
        return aula;
    }

    private static Aula criarAulaOS() {
        Aula aula = new Aula();
        aula.setDisciplina("Operating Systems");
        aula.setProfessor("Adeilton da Silva Meneses");
        aula.setSala("Teams");
        aula.setTipo(TipoAula.REMOTO);
        aula.setStatus(StatusAula.CONFIRMADA);
        aula.setDiaDaSemana(DiaDaSemana.QUARTA);
        aula.setHorarioInicio("21:15");
        aula.setHorarioFim("22:55");
        return aula;
    }

    private static void printTitulo(String titulo) {
        System.out.println("\n" + "=".repeat(40));
        System.out.println(" ".repeat((40 - titulo.length()) / 2) + titulo);
        System.out.println("=".repeat(40));
    }

    private static void printAula(Aula aula) {
        if (aula == null) {
            System.out.println("Aula não encontrada.");
            return;
        }
        System.out.println(aula);
    }

    private static void printAulas(java.util.List<Aula> aulas) {
        if (aulas == null || aulas.isEmpty()) {
            System.out.println("Nenhuma aula encontrada.");
            return;
        }
        System.out.println("Total de aulas: " + aulas.size() + "\n");
        for (Aula a : aulas) {
            printAula(a);
        }
    }
}