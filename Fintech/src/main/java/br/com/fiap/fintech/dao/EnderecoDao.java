package br.com.fiap.fintech.dao;

import br.com.fiap.fintech.model.Endereco;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EnderecoDao {

    private Connection conexao;

    public EnderecoDao(Connection conexao) {
        this.conexao = conexao;
    }

    public void save(Endereco endereco) throws SQLException {
        String sql = endereco.getIdEndereco() == 0 ?
                "INSERT INTO TB_ENDERECO (ID_ENDERECO, TB_USUARIO_ID_USUARIO, NR_CEP, NM_LOGRADOURO, SG_ESTADO, NM_CIDADE, NM_BAIRRO, NR_RESIDENCIA, DS_COMPLEMENTO) " +
                        "VALUES (SEQ_ENDERECO.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?)" :
                "UPDATE TB_ENDERECO SET TB_USUARIO_ID_USUARIO=?, NR_CEP=?, NM_LOGRADOURO=?, SG_ESTADO=?, NM_CIDADE=?, NM_BAIRRO=?, NR_RESIDENCIA=?, DS_COMPLEMENTO=? WHERE ID_ENDERECO=?";

        try (PreparedStatement stmt = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setLong(1, endereco.getIdUsuario());

            // Limpa o CEP, deixando só números
            String cepNumerico = endereco.getCep() != null ? endereco.getCep().replaceAll("\\D", "") : "";
            long cepLong = 0;
            try {
                if (!cepNumerico.isEmpty()) {
                    cepLong = Long.parseLong(cepNumerico);
                }
            } catch (NumberFormatException e) {
                throw new SQLException("CEP inválido: " + cepNumerico);
            }
            stmt.setLong(2, cepLong);

            stmt.setString(3, endereco.getLogradouro());
            stmt.setString(4, endereco.getEstado());
            stmt.setString(5, endereco.getCidade());
            stmt.setString(6, endereco.getBairro());
            stmt.setInt(7, endereco.getResidencia());
            stmt.setString(8, endereco.getComplemento());

            if (endereco.getIdEndereco() != 0) {
                stmt.setInt(9, endereco.getIdEndereco());
                stmt.executeUpdate();
            } else {
                stmt.executeUpdate();
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        endereco.setIdEndereco(rs.getInt(1));
                    }
                }
            }
        }
    }

    public Endereco getById(int idEndereco) throws SQLException {
        String sql = "SELECT * FROM TB_ENDERECO WHERE ID_ENDERECO=?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, idEndereco);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Endereco e = new Endereco();
                    e.setIdEndereco(rs.getInt("ID_ENDERECO"));
                    e.setIdUsuario(rs.getLong("TB_USUARIO_ID_USUARIO"));
                    e.setCep(String.valueOf(rs.getLong("NR_CEP")));
                    e.setLogradouro(rs.getString("NM_LOGRADOURO"));
                    e.setEstado(rs.getString("SG_ESTADO"));
                    e.setCidade(rs.getString("NM_CIDADE"));
                    e.setBairro(rs.getString("NM_BAIRRO"));
                    e.setResidencia(rs.getInt("NR_RESIDENCIA"));
                    e.setComplemento(rs.getString("DS_COMPLEMENTO"));
                    return e;
                }
            }
        }
        return null;
    }

    public List<Endereco> getAll() throws SQLException {
        List<Endereco> lista = new ArrayList<>();
        String sql = "SELECT * FROM TB_ENDERECO";
        try (Statement stmt = conexao.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Endereco e = new Endereco();
                e.setIdEndereco(rs.getInt("ID_ENDERECO"));
                e.setIdUsuario(rs.getLong("TB_USUARIO_ID_USUARIO"));
                e.setCep(String.valueOf(rs.getLong("NR_CEP")));
                e.setLogradouro(rs.getString("NM_LOGRADOURO"));
                e.setEstado(rs.getString("SG_ESTADO"));
                e.setCidade(rs.getString("NM_CIDADE"));
                e.setBairro(rs.getString("NM_BAIRRO"));
                e.setResidencia(rs.getInt("NR_RESIDENCIA"));
                e.setComplemento(rs.getString("DS_COMPLEMENTO"));
                lista.add(e);
            }
        }
        return lista;
    }
}

