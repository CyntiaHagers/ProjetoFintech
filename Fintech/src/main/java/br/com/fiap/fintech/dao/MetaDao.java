package br.com.fiap.fintech.dao;

import br.com.fiap.fintech.model.Meta;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MetaDao {

    // Salvar nova meta
    public void save(Meta meta) throws SQLException {
        String sql = "INSERT INTO TB_META (TB_USUARIO_ID_USUARIO, NM_META, VL_META, VL_ATUAL, DS_META, DT_META) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = ConnectionManager.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, meta.getUsuarioId());
            ps.setString(2, meta.getNome());
            ps.setDouble(3, meta.getValor());
            ps.setDouble(4, meta.getVlAtual());
            ps.setString(5, meta.getDescricao());
            ps.setDate(6, meta.getDataMeta());

            ps.executeUpdate();
        }
    }

    // Listar metas do usuário logado
    public List<Meta> getMetasByUsuario(Long usuarioId) throws SQLException {
        List<Meta> metas = new ArrayList<>();
        String sql = "SELECT ID_META, TB_USUARIO_ID_USUARIO, NM_META, VL_META, VL_ATUAL, DS_META, DT_META FROM TB_META WHERE TB_USUARIO_ID_USUARIO = ? ORDER BY DT_META";

        try (Connection conn = ConnectionManager.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, usuarioId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Meta meta = new Meta();
                    meta.setId(rs.getLong("ID_META"));
                    meta.setUsuarioId(rs.getLong("TB_USUARIO_ID_USUARIO"));
                    meta.setNome(rs.getString("NM_META"));
                    meta.setValor(rs.getDouble("VL_META"));
                    meta.setVlAtual(rs.getDouble("VL_ATUAL"));
                    meta.setDescricao(rs.getString("DS_META"));
                    meta.setDataMeta(rs.getDate("DT_META"));
                    metas.add(meta);
                }
            }
        }
        return metas;
    }

    // Buscar meta por ID
    public Meta getById(Long id) throws SQLException {
        String sql = "SELECT ID_META, TB_USUARIO_ID_USUARIO, NM_META, VL_META, VL_ATUAL, DS_META, DT_META FROM TB_META WHERE ID_META = ?";
        Meta meta = null;

        try (Connection conn = ConnectionManager.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    meta = new Meta();
                    meta.setId(rs.getLong("ID_META"));
                    meta.setUsuarioId(rs.getLong("TB_USUARIO_ID_USUARIO"));
                    meta.setNome(rs.getString("NM_META"));
                    meta.setValor(rs.getDouble("VL_META"));
                    meta.setVlAtual(rs.getDouble("VL_ATUAL"));
                    meta.setDescricao(rs.getString("DS_META"));
                    meta.setDataMeta(rs.getDate("DT_META"));
                }
            }
        }
        return meta;
    }

    // Atualizar meta
    public void update(Meta meta) throws SQLException {
        String sql = "UPDATE TB_META SET NM_META = ?, VL_META = ?, VL_ATUAL = ?, DS_META = ?, DT_META = ? WHERE ID_META = ?";

        try (Connection conn = ConnectionManager.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, meta.getNome());
            ps.setDouble(2, meta.getValor());
            ps.setDouble(3, meta.getVlAtual());
            ps.setString(4, meta.getDescricao());
            ps.setDate(5, meta.getDataMeta());
            ps.setLong(6, meta.getId());

            ps.executeUpdate();
        }
    }

    // Deletar meta
    public void delete(Long id) throws SQLException {
        String sql = "DELETE FROM TB_META WHERE ID_META = ?";

        try (Connection conn = ConnectionManager.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, id);
            ps.executeUpdate();
        }
    }
}


