package br.com.fiap.service;

import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import br.com.fiap.dto.Aula;
import br.com.fiap.enums.DiaDaSemana;

@WebService
public interface IAulaService {

    @WebMethod
    List<Aula> listarTodas();

    @WebMethod
    Aula buscarPorId(@WebParam(name = "id") Long id);

    @WebMethod
    List<Aula> listarPorDia(@WebParam(name = "diaDaSemana") DiaDaSemana dia);

    @WebMethod
    List<Aula> listarPorDisciplina(@WebParam(name = "disciplina") String disciplina);

    @WebMethod
    Aula cadastrar(@WebParam(name = "aula") Aula aula);

    @WebMethod
    Aula atualizar(@WebParam(name = "aula") Aula aula);

    @WebMethod
    boolean excluir(@WebParam(name = "id") Long id);
}