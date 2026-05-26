package dao;

import model.Docente;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DocenteDAO {

    private Docente map(ResultSet rs) throws SQLException {
        Docente docente = new Docente();
        docente.setId(rs.getInt("id"));
        docente.setNome(rs.getString("nome"));
        docente.setEmail(rs.getString("email"));
        docente.setTelefone(rs.getString("telefone"));
        docente.setDepartamento(rs.getString("departamento"));
        docente.setAtivo(rs.getBoolean("ativo"));
        return docente;
    }

    public boolean inserir(Docente docente) {
        String sql = "INSERT INTO docente (nome, email, telefone, departamento, ativo) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = ConexaoDB.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, docente.getNome());
            stmt.setString(2, docente.getEmail());
            stmt.setString(3, docente.getTelefone());
            stmt.setString(4, docente.getDepartamento());
            stmt.setBoolean(5, docente.isAtivo());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir docente", e);
        }
    }

    public List<Docente> listar() {
        String sql = "SELECT * FROM docente ORDER BY nome";
        List<Docente> lista = new ArrayList<>();

        try (Connection conn = ConexaoDB.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                lista.add(map(rs));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar docentes", e);
        }

        return lista;
    }

    public List<Docente> listarAtivos() {
        String sql = "SELECT * FROM docente WHERE ativo = true ORDER BY nome";
        List<Docente> lista = new ArrayList<>();

        try (Connection conn = ConexaoDB.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                lista.add(map(rs));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar docentes ativos", e);
        }

        return lista;
    }

    public Docente buscarPorId(int id) {
        String sql = "SELECT * FROM docente WHERE id = ?";

        try (Connection conn = ConexaoDB.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return map(rs);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar docente", e);
        }

        return null;
    }

    public void atualizar(Docente docente) {
        String sql = "UPDATE docente SET nome=?, email=?, telefone=?, departamento=?, ativo=? WHERE id=?";

        try (Connection conn = ConexaoDB.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, docente.getNome());
            stmt.setString(2, docente.getEmail());
            stmt.setString(3, docente.getTelefone());
            stmt.setString(4, docente.getDepartamento());
            stmt.setBoolean(5, docente.isAtivo());
            stmt.setInt(6, docente.getId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar docente", e);
        }
    }

    public void excluir(int id) {
        String sql = "DELETE FROM docente WHERE id = ?";

        try (Connection conn = ConexaoDB.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir docente", e);
        }
    }
}
