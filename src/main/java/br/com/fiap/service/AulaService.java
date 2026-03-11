package br.com.fiap.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import javax.jws.WebService;

import br.com.fiap.dto.Aula;
import br.com.fiap.enums.DiaDaSemana;
import br.com.fiap.enums.StatusAula;
import br.com.fiap.enums.TipoAula;

@WebService(endpointInterface = "br.com.fiap.service.IAulaService", serviceName = "AulaService")
public class AulaService implements IAulaService {

        private final List<Aula> aulas = new ArrayList<>();
        private final AtomicInteger idGenerator = new AtomicInteger(0);

        public AulaService() {
                popularAulasIniciais();
        }

        @Override
        public List<Aula> listarTodas() {
                return new ArrayList<>(aulas);
        }

        @Override
        public Aula buscarPorId(Long id) {
                return aulas.stream()
                                .filter(aula -> aula.getId().equals(id))
                                .findFirst()
                                .orElse(null);
        }

        @Override
        public List<Aula> listarPorDia(DiaDaSemana dia) {
                return aulas.stream()
                                .filter(aula -> aula.getDiaDaSemana() == dia)
                                .collect(Collectors.toList());
        }

        @Override
        public List<Aula> listarPorDisciplina(String disciplina) {
                return aulas.stream()
                                .filter(aula -> aula.getDisciplina() != null &&
                                                aula.getDisciplina().toLowerCase().contains(disciplina.toLowerCase()))
                                .collect(Collectors.toList());
        }

        @Override
        public Aula cadastrar(Aula aula) {
                aula.setId((long) idGenerator.incrementAndGet());
                aulas.add(aula);
                return aula;
        }

        @Override
        public Aula atualizar(Aula aula) {
                Aula aulaExistente = buscarPorId(aula.getId());

                if (aulaExistente != null) {
                        aulaExistente.setDisciplina(aula.getDisciplina());
                        aulaExistente.setProfessor(aula.getProfessor());
                        aulaExistente.setSala(aula.getSala());
                        aulaExistente.setDiaDaSemana(aula.getDiaDaSemana());
                        aulaExistente.setHorarioInicio(aula.getHorarioInicio());
                        aulaExistente.setHorarioFim(aula.getHorarioFim());
                        aulaExistente.setTipo(aula.getTipo());
                        aulaExistente.setStatus(aula.getStatus());
                        return aulaExistente;
                }
                return null;
        }

        @Override
        public boolean excluir(Long id) {
                return aulas.removeIf(aula -> aula.getId().equals(id));
        }

        @SuppressWarnings("unused")
        private void popularAulasIniciais() {
                // --- SEGUNDA-FEIRA ---
                this.cadastrar(new Aula("ARQUITETURA ORIENTADA A SERVIÇOS (SOA) E WEB SERVICES", "SALATIEL LUZ MARINHO",
                                "105", DiaDaSemana.SEGUNDA, "19:20", "21:00", TipoAula.PRESENCIAL,
                                StatusAula.CONFIRMADA));
                this.cadastrar(new Aula("MOBILE DEVELOPMENT & IOT", "ADEILTON DA SILVA MENESES",
                                "105", DiaDaSemana.SEGUNDA, "21:15", "22:55", TipoAula.PRESENCIAL,
                                StatusAula.CONFIRMADA));

                // --- TERÇA-FEIRA ---
                this.cadastrar(new Aula("INTELIGÊNCIA ARTIFICIAL & MACHINE LEARNING", "DANILO RODRIGUES DE ASSIS ELIAS",
                                "105", DiaDaSemana.TERCA, "19:20", "21:00", TipoAula.PRESENCIAL,
                                StatusAula.CONFIRMADA));
                this.cadastrar(new Aula("C# SOFTWARE DEVELOPMENT", "RAFAEL SANTOS NOVO PEREIRA",
                                "105", DiaDaSemana.TERCA, "21:15", "22:55", TipoAula.PRESENCIAL,
                                StatusAula.CONFIRMADA));

                // --- QUARTA-FEIRA ---
                this.cadastrar(new Aula("TESTING, COMPLIANCE & QUALITY ASSURANCE",
                                "ANDRE LUIZ DE ALCANTARA DA SILVA LEITE",
                                "105", DiaDaSemana.QUARTA, "19:20", "21:00", TipoAula.REMOTO, StatusAula.CONFIRMADA));
                this.cadastrar(new Aula("OPERATING SYSTEMS", "BRUNO LUIZ DE ALMEIDA",
                                "105", DiaDaSemana.QUARTA, "21:15", "22:55", TipoAula.REMOTO, StatusAula.CONFIRMADA));

                // --- SEXTA-FEIRA ---
                this.cadastrar(new Aula("PHYSICAL COMPUTING: IOT & IOB", "YAN GABRIEL COELHO",
                                "105", DiaDaSemana.SEXTA, "19:20", "21:00", TipoAula.PRESENCIAL,
                                StatusAula.CONFIRMADA));
                this.cadastrar(new Aula("CYBERSECURITY", "VITOR MIGUEL LASSE SILVA",
                                "105", DiaDaSemana.SEXTA, "21:15", "22:55", TipoAula.PRESENCIAL,
                                StatusAula.CONFIRMADA));

        }
}