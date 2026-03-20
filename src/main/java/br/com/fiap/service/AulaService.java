package br.com.fiap.service;

import java.util.List;

import javax.jws.WebService;

import br.com.fiap.dao.AulaDAO;
import br.com.fiap.dto.Aula;
import br.com.fiap.enums.DiaDaSemana;

@WebService(endpointInterface = "br.com.fiap.service.IAulaService", serviceName = "AulaService")
public class AulaService implements IAulaService {

        private final AulaDAO aulaDAO = new AulaDAO();

        public AulaService() {
        }

        @Override
        public List<Aula> listarTodas() {
                return aulaDAO.listarTodas();
        }

        @Override
        public Aula buscarPorId(Long id) {
                return aulaDAO.buscarPorId(id);
        }

        @Override
        public List<Aula> listarPorDia(DiaDaSemana dia) {
                return aulaDAO.listarPorDia(dia);
        }

        @Override
        public List<Aula> listarPorDisciplina(String disciplina) {
                return aulaDAO.listarPorDisciplina(disciplina);
        }

        @Override
        public Aula cadastrar(Aula aula) {
                return aulaDAO.cadastrar(aula);
        }

        @Override
        public Aula atualizar(Aula aula) {
                return aulaDAO.atualizar(aula);
        }

        @Override
        public boolean excluir(Long id) {
                return aulaDAO.excluir(id);
        }
}