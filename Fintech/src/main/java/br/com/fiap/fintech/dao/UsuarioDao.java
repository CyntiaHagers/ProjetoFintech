package br.com.fiap.fintech.dao;


import br.com.fiap.fintech.model.Usuario;
import br.com.fiap.fintech.controller.LoginServlet;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDao {
    private Connection conexao;

    public UsuarioDao() throws SQLException {
        conexao = ConnectionManager.getConnection();  // método static chamado diretamente
    }


    public void fecharConexao() throws SQLException {
        if (conexao != null && !conexao.isClosed()) {
            conexao.close();
        }
    }

    public void cadastrarUsuario(Usuario usuario) throws SQLException {
        if (usuarioExiste(usuario.getCpf())) {
            throw new SQLException("Usuário com este CPF já está cadastrado.");
        }

        // Buscar o próximo ID da sequência
        long idUsuario = 0;
        String sqlSeq = "SELECT seq_usuario.NEXTVAL FROM dual";
        try (PreparedStatement stmtSeq = conexao.prepareStatement(sqlSeq)) {
            ResultSet rs = stmtSeq.executeQuery();
            if (rs.next()) {
                idUsuario = rs.getLong(1);
            }
        }

        String sql = "INSERT INTO TB_USUARIO (ID_USUARIO, NR_CPF, NM_USUARIO, NM_EMAIL, DS_SENHA) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setLong(1, idUsuario);
            stmt.setString(2, usuario.getCpf());
            stmt.setString(3, usuario.getNome());
            stmt.setString(4, usuario.getEmail());
            stmt.setString(5, usuario.getSenha());
            stmt.executeUpdate();

        }
    }







    public Usuario validarLogin(String email, String senha) throws SQLException {
        String sql = "SELECT * FROM tb_usuario WHERE nm_email = ? AND ds_senha = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, email);
            stmt.setString(2, senha);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Usuario(
                        rs.getLong("id_usuario"),
                        rs.getString("nr_cpf"),
                        rs.getString("nm_usuario"),
                        rs.getString("nm_email"),
                        rs.getString("ds_senha")
                );
            }
        }
        return null;
    }

    public boolean usuarioExiste(String cpf) throws SQLException {
        String sql = "SELECT COUNT(*) FROM TB_USUARIO WHERE NR_CPF = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, cpf);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        }
        return false;
    }


    public List<Usuario> getAll() throws SQLException {
        String sql = "SELECT * FROM tb_usuario";
        List<Usuario> usuarios = new ArrayList<>();
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                usuarios.add(new Usuario(
                        rs.getLong("id_usuario"),
                        rs.getString("nr_cpf"),
                        rs.getString("nm_usuario"),
                        rs.getString("nm_email"),
                        rs.getString("ds_senha")
                ));
            }
        }
        return usuarios;
    }
}

