package br.com.fiap.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.dto.Aula;
import br.com.fiap.enums.DiaDaSemana;
import br.com.fiap.enums.StatusAula;
import br.com.fiap.enums.TipoAula;
import br.com.fiap.factory.ConnectionFactory;

public class AulaDAO {

    public Aula cadastrar(Aula aula) {
        String sql = "INSERT INTO FIAP_T_AULA (id_aula, nm_disciplina, nm_professor, nr_sala, ds_dia_semana, hr_inicio, hr_fim, tp_aula, st_aula) "
                + "VALUES (FIAP_SQ_AULA.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = ConnectionFactory.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql, new String[] { "id_aula" })) {

            preencherStatement(ps, aula);
            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    aula.setId(rs.getLong(1));
                }
            }
            return aula;

        } catch (SQLException e) {
            System.err.println("Erro ao cadastrar aula: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    public List<Aula> listarTodas() {
        List<Aula> aulas = new ArrayList<>();
        String sql = "SELECT * FROM FIAP_T_AULA ORDER BY id_aula";

        try (Connection conn = ConnectionFactory.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                aulas.add(mapearResultSetParaAula(rs));
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar aulas: " + e.getMessage());
        }
        return aulas;
    }

    public Aula buscarPorId(Long id) {
        String sql = "SELECT * FROM FIAP_T_AULA WHERE id_aula = ?";

        try (Connection conn = ConnectionFactory.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapearResultSetParaAula(rs);
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar aula por ID: " + e.getMessage());
        }
        return null;
    }

    public List<Aula> listarPorDia(DiaDaSemana dia) {
        List<Aula> aulas = new ArrayList<>();
        String sql = "SELECT * FROM FIAP_T_AULA WHERE ds_dia_semana = ?";

        try (Connection conn = ConnectionFactory.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, dia.name());
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    aulas.add(mapearResultSetParaAula(rs));
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar aulas por dia: " + e.getMessage());
        }
        return aulas;
    }

    public List<Aula> listarPorDisciplina(String disciplina) {
        List<Aula> aulas = new ArrayList<>();

        String sql = "SELECT * FROM FIAP_T_AULA WHERE LOWER(nm_disciplina) LIKE LOWER(?)";

        try (Connection conn = ConnectionFactory.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, "%" + disciplina + "%");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    aulas.add(mapearResultSetParaAula(rs));
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar aulas por disciplina: " + e.getMessage());
        }
        return aulas;
    }

    public Aula atualizar(Aula aula) {
        String sql = "UPDATE FIAP_T_AULA SET nm_disciplina = ?, nm_professor = ?, nr_sala = ?, "
                + "ds_dia_semana = ?, hr_inicio = ?, hr_fim = ?, tp_aula = ?, st_aula = ? "
                + "WHERE id_aula = ?";

        try (Connection conn = ConnectionFactory.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            preencherStatement(ps, aula);
            ps.setLong(9, aula.getId());

            int linhasAfetadas = ps.executeUpdate();
            if (linhasAfetadas > 0) {
                return aula;
            }
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar aula: " + e.getMessage());
        }
        return null;
    }

    public boolean excluir(Long id) {
        String sql = "DELETE FROM FIAP_T_AULA WHERE id_aula = ?";

        try (Connection conn = ConnectionFactory.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, id);
            int linhasAfetadas = ps.executeUpdate();
            return linhasAfetadas > 0;

        } catch (SQLException e) {
            System.err.println("Erro ao excluir aula: " + e.getMessage());
            return false;
        }
    }

    private void preencherStatement(PreparedStatement ps, Aula aula) throws SQLException {
        ps.setString(1, aula.getDisciplina());
        ps.setString(2, aula.getProfessor());
        ps.setString(3, aula.getSala());
        ps.setString(4, aula.getDiaDaSemana() != null ? aula.getDiaDaSemana().name() : null);
        ps.setString(5, aula.getHorarioInicio());
        ps.setString(6, aula.getHorarioFim());
        ps.setString(7, aula.getTipo() != null ? aula.getTipo().name() : null);
        ps.setString(8, aula.getStatus() != null ? aula.getStatus().name() : null);
    }

    private Aula mapearResultSetParaAula(ResultSet rs) throws SQLException {
        Aula aula = new Aula();
        aula.setId(rs.getLong("id_aula"));
        aula.setDisciplina(rs.getString("nm_disciplina"));
        aula.setProfessor(rs.getString("nm_professor"));
        aula.setSala(rs.getString("nr_sala"));

        String diaStr = rs.getString("ds_dia_semana");
        if (diaStr != null)
            aula.setDiaDaSemana(DiaDaSemana.valueOf(diaStr));

        aula.setHorarioInicio(rs.getString("hr_inicio"));
        aula.setHorarioFim(rs.getString("hr_fim"));

        String tipoStr = rs.getString("tp_aula");
        if (tipoStr != null)
            aula.setTipo(TipoAula.valueOf(tipoStr));

        String statusStr = rs.getString("st_aula");
        if (statusStr != null)
            aula.setStatus(StatusAula.valueOf(statusStr));

        return aula;
    }
}