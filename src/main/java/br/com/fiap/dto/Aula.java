package br.com.fiap.dto;

import br.com.fiap.enums.DiaDaSemana;
import br.com.fiap.enums.StatusAula;
import br.com.fiap.enums.TipoAula;

public class Aula {

    private Long id;
    private String disciplina;
    private String professor;
    private String sala;
    private DiaDaSemana diaDaSemana;
    private String horarioInicio;
    private String horarioFim;
    private TipoAula tipo;
    private StatusAula status;

    public Aula() {
    }

    public Aula(String disciplina, String professor, String sala,
            DiaDaSemana diaDaSemana, String horarioInicio, String horarioFim,
            TipoAula tipo, StatusAula status) {
        this.disciplina = disciplina;
        this.professor = professor;
        this.sala = sala;
        this.diaDaSemana = diaDaSemana;
        this.horarioInicio = horarioInicio;
        this.horarioFim = horarioFim;
        this.tipo = tipo;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(String disciplina) {
        this.disciplina = disciplina;
    }

    public String getProfessor() {
        return professor;
    }

    public void setProfessor(String professor) {
        this.professor = professor;
    }

    public String getSala() {
        return sala;
    }

    public void setSala(String sala) {
        this.sala = sala;
    }

    public DiaDaSemana getDiaDaSemana() {
        return diaDaSemana;
    }

    public void setDiaDaSemana(DiaDaSemana diaDaSemana) {
        this.diaDaSemana = diaDaSemana;
    }

    public String getHorarioInicio() {
        return horarioInicio;
    }

    public void setHorarioInicio(String horarioInicio) {
        this.horarioInicio = horarioInicio;
    }

    public String getHorarioFim() {
        return horarioFim;
    }

    public void setHorarioFim(String horarioFim) {
        this.horarioFim = horarioFim;
    }

    public TipoAula getTipo() {
        return tipo;
    }

    public void setTipo(TipoAula tipo) {
        this.tipo = tipo;
    }

    public StatusAula getStatus() {
        return status;
    }

    public void setStatus(StatusAula status) {
        this.status = status;
    }

    @Override
    public String toString() {
        String dia = diaDaSemana != null ? diaDaSemana.name() : "N/A";
        String inicio = horarioInicio != null ? horarioInicio : "--:--";
        String fim = horarioFim != null ? horarioFim : "--:--";
        String tipoStr = tipo != null ? tipo.name() : "N/A";
        String statusStr = status != null ? status.name() : "N/A";

        return String.format(
                "Aula [ID: %d] - %s\n" +
                        "  ├─ Prof/Local: %s | Sala: %s\n" +
                        "  ├─ Horário   : %s, das %s às %s\n" +
                        "  └─ Tipo      : %s | Status: %s\n",
                id,
                disciplina,
                professor,
                sala,
                dia,
                inicio,
                fim,
                tipoStr,
                statusStr);
    }
}