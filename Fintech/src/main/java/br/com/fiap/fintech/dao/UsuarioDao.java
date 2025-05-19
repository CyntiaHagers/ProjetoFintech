package br.com.fiap.fintech.dao;


import br.com.fiap.fintech.model.Usuario;
import br.com.fiap.fintech.controller.LoginServlet;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDao {
    private Connection conexao;

    public UsuarioDao() throws SQLException {
        conexao = ConnectionManager.getInstance().getConnection();
    }

    public void fecharConexao() throws SQLException {
        if (conexao != null && !conexao.isClosed()) {
            conexao.close();
        }
    }

    public void cadastrarUsuario(Usuario usuario) throws SQLException {
        String sql = "INSERT INTO tb_usuario (id_usuario, nr_cpf, nm_usuario, nm_email, ds_senha) VALUES (seq_id_usuario.nextval, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, usuario.getCpf());
            stmt.setString(2, usuario.getNome());
            stmt.setString(3, usuario.getEmail());
            stmt.setString(4, usuario.getSenha());
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

