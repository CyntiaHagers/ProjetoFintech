package br.com.fiap.fintech.dao;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import br.com.fiap.fintech.model.Recorrencia;

/**
 * Classe DAO para operações relacionadas a recorrências de transações
 */
public class RecorrenciaDao {

    /**
     * Insere uma nova recorrência no banco de dados
     */
    public int inserir(Recorrencia recorrencia) throws Exception {
        String sql = "INSERT INTO TB_RECORRENCIA "
                + "(ID_RECORRENCIA, TP_RECORRENCIA, NR_TOTAL_PARCELAS, NR_INTERVALO_MESES, DT_INICIO, DT_FIM) "
                + "VALUES (SEQ_ID_RECORRENCIA.NEXTVAL, ?, ?, ?, ?, ?)";
        
        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, new String[]{"ID_RECORRENCIA"})) {
            
            stmt.setString(1, String.valueOf(recorrencia.getTipoRecorrencia()));
            
            // Tratar o total de parcelas (pode ser null)
            if (recorrencia.getTotalParcelas() != null) {
                stmt.setInt(2, recorrencia.getTotalParcelas());
            } else {
                stmt.setNull(2, java.sql.Types.INTEGER);
            }
            
            stmt.setInt(3, recorrencia.getIntervaloMeses());
            
            // Converter LocalDate para java.sql.Date
            if (recorrencia.getDataInicio() != null) {
                stmt.setDate(4, java.sql.Date.valueOf(recorrencia.getDataInicio()));
            } else {
                stmt.setNull(4, java.sql.Types.DATE);
            }
            
            // Tratar a data fim (pode ser null)
            if (recorrencia.getDataFim() != null) {
                stmt.setDate(5, java.sql.Date.valueOf(recorrencia.getDataFim()));
            } else {
                stmt.setNull(5, java.sql.Types.DATE);
            }
            
            stmt.executeUpdate();
            
            // Obter o ID gerado
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
            
            return 0;
        }
    }
    
    /**
     * Busca uma recorrência pelo ID
     */
    public Recorrencia buscarPorId(int idRecorrencia) throws Exception {
        String sql = "SELECT * FROM TB_RECORRENCIA WHERE ID_RECORRENCIA = ?";
        
        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, idRecorrencia);
            
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return extrairRecorrenciaDoResultSet(rs);
                }
                return null;
            }
        }
    }
    
    /**
     * Lista todas as recorrências ativas na data especificada
     */
    public List<Recorrencia> listarAtivasEm(LocalDate data) throws Exception {
        String sql = "SELECT * FROM TB_RECORRENCIA WHERE DT_INICIO <= ? AND (DT_FIM IS NULL OR DT_FIM >= ?)";
        
        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            java.sql.Date sqlDate = java.sql.Date.valueOf(data);
            stmt.setDate(1, sqlDate);
            stmt.setDate(2, sqlDate);
            
            try (ResultSet rs = stmt.executeQuery()) {
                List<Recorrencia> lista = new ArrayList<>();
                while (rs.next()) {
                    lista.add(extrairRecorrenciaDoResultSet(rs));
                }
                return lista;
            }
        }
    }
    
    /**
     * Atualiza uma recorrência existente
     */
    public boolean atualizar(Recorrencia recorrencia) throws Exception {
        String sql = "UPDATE TB_RECORRENCIA SET TP_RECORRENCIA = ?, NR_TOTAL_PARCELAS = ?, "
                + "NR_INTERVALO_MESES = ?, DT_INICIO = ?, DT_FIM = ? WHERE ID_RECORRENCIA = ?";
        
        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, String.valueOf(recorrencia.getTipoRecorrencia()));
            
            // Tratar o total de parcelas (pode ser null)
            if (recorrencia.getTotalParcelas() != null) {
                stmt.setInt(2, recorrencia.getTotalParcelas());
            } else {
                stmt.setNull(2, java.sql.Types.INTEGER);
            }
            
            stmt.setInt(3, recorrencia.getIntervaloMeses());
            
            // Converter LocalDate para java.sql.Date
            if (recorrencia.getDataInicio() != null) {
                stmt.setDate(4, java.sql.Date.valueOf(recorrencia.getDataInicio()));
            } else {
                stmt.setNull(4, java.sql.Types.DATE);
            }
            
            // Tratar a data fim (pode ser null)
            if (recorrencia.getDataFim() != null) {
                stmt.setDate(5, java.sql.Date.valueOf(recorrencia.getDataFim()));
            } else {
                stmt.setNull(5, java.sql.Types.DATE);
            }
            
            stmt.setInt(6, recorrencia.getIdRecorrencia());
            
            return stmt.executeUpdate() > 0;
        }
    }
    
    /**
     * Exclui uma recorrência pelo ID
     */
    public boolean excluir(int idRecorrencia) throws Exception {
        String sql = "DELETE FROM TB_RECORRENCIA WHERE ID_RECORRENCIA = ?";
        
        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, idRecorrencia);
            
            return stmt.executeUpdate() > 0;
        }
    }
    
    /**
     * Método auxiliar para extrair uma recorrência do ResultSet
     */
    private Recorrencia extrairRecorrenciaDoResultSet(ResultSet rs) throws SQLException {
        Recorrencia r = new Recorrencia();
        
        r.setIdRecorrencia(rs.getInt("ID_RECORRENCIA"));
        
        String tipoRecorrencia = rs.getString("TP_RECORRENCIA");
        if (tipoRecorrencia != null && !tipoRecorrencia.isEmpty()) {
            r.setTipoRecorrencia(tipoRecorrencia.charAt(0));
        }
        
        int totalParcelas = rs.getInt("NR_TOTAL_PARCELAS");
        if (!rs.wasNull()) {
            r.setTotalParcelas(totalParcelas);
        }
        
        r.setIntervaloMeses(rs.getInt("NR_INTERVALO_MESES"));
        
        Date dataInicio = rs.getDate("DT_INICIO");
        if (dataInicio != null) {
            r.setDataInicio(dataInicio.toLocalDate());
        }
        
        Date dataFim = rs.getDate("DT_FIM");
        if (dataFim != null) {
            r.setDataFim(dataFim.toLocalDate());
        }
        
        return r;
    }
    
    /**
     * Método para listar todas as transações associadas a uma recorrência
     */
    public List<Integer> listarTransacoesPorRecorrencia(int idRecorrencia) throws Exception {
        String sql = "SELECT ID_TRANSACAO FROM TB_TRANSACAO WHERE ID_RECORRENCIA = ?";
        
        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, idRecorrencia);
            
            try (ResultSet rs = stmt.executeQuery()) {
                List<Integer> idsTransacoes = new ArrayList<>();
                while (rs.next()) {
                    idsTransacoes.add(rs.getInt("ID_TRANSACAO"));
                }
                return idsTransacoes;
            }
        }
    }
}