package br.com.fiap.fintech.dao;

import br.com.fiap.fintech.model.Endereco;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EnderecoDao {

    private Connection connection;

    public void save(Endereco endereco) {
        PreparedStatement stm = null;
        String sql = "INSERT INTO tb_endereco " +
                "(id_endereco, id_usuario, cep, logradouro, estado, cidade, bairro, nr_residencia, complemento) " +
                "VALUES (seq_tb_endereco.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            connection = ConnectionManager.getInstance().getConnection();
            stm = connection.prepareStatement(sql);

            stm.setLong(1, endereco.getIdUsuario());
            stm.setInt(2, endereco.getCep());
            stm.setString(3, endereco.getLogradouro());
            stm.setString(4, endereco.getEstado());
            stm.setString(5, endereco.getCidade());
            stm.setString(6, endereco.getBairro());
            stm.setString(7, endereco.getResidencia());
            stm.setString(8, endereco.getComplemento());

            int result = stm.executeUpdate();
            System.out.println("Endereço salvo com sucesso! Linhas afetadas: " + result);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (stm != null) stm.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void update(Endereco endereco) {
        PreparedStatement stm = null;
        String sql = "UPDATE tb_endereco SET id_usuario = ?, cep = ?, logradouro = ?, estado = ?, cidade = ?, bairro = ?, nr_residencia = ?, complemento = ? WHERE id_endereco = ?";

        try {
            connection = ConnectionManager.getInstance().getConnection();
            stm = connection.prepareStatement(sql);

            stm.setLong(1, endereco.getIdUsuario());
            stm.setInt(2, endereco.getCep());
            stm.setString(3, endereco.getLogradouro());
            stm.setString(4, endereco.getEstado());
            stm.setString(5, endereco.getCidade());
            stm.setString(6, endereco.getBairro());
            stm.setString(7, endereco.getResidencia());
            stm.setString(8, endereco.getComplemento());
            stm.setInt(9, endereco.getIdEndereco());

            int result = stm.executeUpdate();
            System.out.println("Endereço atualizado! Linhas afetadas: " + result);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (stm != null) stm.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void delete(int id) {
        PreparedStatement stm = null;
        String sql = "DELETE FROM tb_endereco WHERE id_endereco = ?";

        try {
            connection = ConnectionManager.getInstance().getConnection();
            stm = connection.prepareStatement(sql);
            stm.setInt(1, id);

            int result = stm.executeUpdate();
            System.out.println("Endereço excluído! Linhas afetadas: " + result);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (stm != null) stm.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public Endereco getEndereçoById(int id) {
        PreparedStatement stm = null;
        ResultSet rs = null;
        Endereco endereco = null;

        String sql = "SELECT * FROM tb_endereco WHERE id_endereco = ?";

        try {
            connection = ConnectionManager.getInstance().getConnection();
            stm = connection.prepareStatement(sql);
            stm.setInt(1, id);

            rs = stm.executeQuery();
            if (rs.next()) {
                endereco = new Endereco(
                        rs.getLong("id_usuario"),
                        rs.getInt("cep"),
                        rs.getString("logradouro"),
                        rs.getString("estado"),
                        rs.getString("cidade"),
                        rs.getString("bairro"),
                        rs.getString("residencia"),
                        rs.getString("complemento"),
                        rs.getInt("id_endereco")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (stm != null) stm.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return endereco;
    }

    public List<Endereco> getAll() {
        List<Endereco> lista = new ArrayList<>();
        PreparedStatement stm = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM tb_endereco";

        try {
            connection = ConnectionManager.getInstance().getConnection();
            stm = connection.prepareStatement(sql);
            rs = stm.executeQuery();

            while (rs.next()) {
                Endereco endereco = new Endereco(
                        rs.getLong("id_usuario"),
                        rs.getInt("cep"),
                        rs.getString("logradouro"),
                        rs.getString("estado"),
                        rs.getString("cidade"),
                        rs.getString("bairro"),
                        rs.getString("residencia"),
                        rs.getString("complemento"),
                        rs.getInt("id_endereco")
                );
                lista.add(endereco);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (stm != null) stm.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return lista;
    }
}
