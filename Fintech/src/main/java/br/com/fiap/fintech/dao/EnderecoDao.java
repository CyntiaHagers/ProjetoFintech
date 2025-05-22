package br.com.fiap.fintech.dao;

import br.com.fiap.fintech.model.Endereco;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EnderecoDao {

    public void save(Endereco endereco) {
        String sql = "INSERT INTO tb_endereco " +
                "(id_endereco, tb_usuario_id_usuario, nr_cep, nm_logradouro, sg_estado, nm_cidade, nm_bairro, nr_residencia, ds_complemento) " +
                "VALUES (seq_tb_endereco.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = ConnectionManager.getInstance().getConnection();
             PreparedStatement stm = connection.prepareStatement(sql)) {

            stm.setLong(1, endereco.getIdUsuario());
            stm.setString(2, endereco.getCep());  // mudou para String
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
        }
    }

    public void update(Endereco endereco) {
        String sql = "UPDATE tb_endereco SET id_usuario = ?, cep = ?, logradouro = ?, estado = ?, cidade = ?, bairro = ?, nr_residencia = ?, complemento = ? WHERE id_endereco = ?";

        try (Connection connection = ConnectionManager.getInstance().getConnection();
             PreparedStatement stm = connection.prepareStatement(sql)) {

            stm.setLong(1, endereco.getIdUsuario());
            stm.setString(2, endereco.getCep());  // mudou para String
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
        }
    }

    public void delete(int id) {
        String sql = "DELETE FROM tb_endereco WHERE id_endereco = ?";

        try (Connection connection = ConnectionManager.getInstance().getConnection();
             PreparedStatement stm = connection.prepareStatement(sql)) {

            stm.setInt(1, id);

            int result = stm.executeUpdate();
            System.out.println("Endereço excluído! Linhas afetadas: " + result);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Endereco getById(int idEndereco) {
        String sql = "SELECT * FROM tb_endereco WHERE id_endereco = ?";
        Endereco endereco = null;

        try (Connection connection = ConnectionManager.getInstance().getConnection();
             PreparedStatement stm = connection.prepareStatement(sql)) {

            stm.setInt(1, idEndereco);
            try (ResultSet rs = stm.executeQuery()) {
                if (rs.next()) {
                    endereco = new Endereco(
                            rs.getLong("id_usuario"),
                            rs.getString("cep"), // mudou para String
                            rs.getString("logradouro"),
                            rs.getString("estado"),
                            rs.getString("cidade"),
                            rs.getString("bairro"),
                            rs.getString("nr_residencia"),
                            rs.getString("complemento"),
                            rs.getInt("id_endereco")
                    );
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return endereco;
    }

    public List<Endereco> getByUsuario(long idUsuario) {
        List<Endereco> lista = new ArrayList<>();
        String sql = "SELECT * FROM tb_endereco WHERE id_usuario = ?";

        try (Connection connection = ConnectionManager.getInstance().getConnection();
             PreparedStatement stm = connection.prepareStatement(sql)) {

            stm.setLong(1, idUsuario);
            try (ResultSet rs = stm.executeQuery()) {
                while (rs.next()) {
                    Endereco endereco = new Endereco(
                            rs.getLong("id_usuario"),
                            rs.getString("cep"),
                            rs.getString("logradouro"),
                            rs.getString("estado"),
                            rs.getString("cidade"),
                            rs.getString("bairro"),
                            rs.getString("nr_residencia"),
                            rs.getString("complemento"),
                            rs.getInt("id_endereco")
                    );
                    lista.add(endereco);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }

    public List<Endereco> getAll() {
        List<Endereco> lista = new ArrayList<>();
        String sql = "SELECT * FROM tb_endereco";

        try (Connection connection = ConnectionManager.getInstance().getConnection();
             PreparedStatement stm = connection.prepareStatement(sql);
             ResultSet rs = stm.executeQuery()) {

            while (rs.next()) {
                Endereco endereco = new Endereco(
                        rs.getLong("id_usuario"),
                        rs.getString("cep"),
                        rs.getString("logradouro"),
                        rs.getString("estado"),
                        rs.getString("cidade"),
                        rs.getString("bairro"),
                        rs.getString("nr_residencia"),
                        rs.getString("complemento"),
                        rs.getInt("id_endereco")
                );
                lista.add(endereco);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }
}

