package br.com.fiap.fintech.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.fintech.model.Transacao;


public class TransacaoDao {

	public List<Transacao> listarPorUsuario(Long idUsuario) throws Exception {
		List<Transacao> lista = new ArrayList<>();
		String sql = "SELECT * FROM TB_TRANSACAO WHERE TB_USUARIO_ID_USUARIO = ? ORDER BY DT_TRANSACAO DESC";

		try (Connection conn = ConnectionManager.getConnection();
		     PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setLong(1, idUsuario);

			try (ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) {
					Transacao t = new Transacao();
					t.setIdTransacao(rs.getInt("ID_TRANSACAO"));
					t.setIdUsuario(rs.getInt("TB_USUARIO_ID_USUARIO"));
					t.setIdConta(rs.getInt("TB_CONTA_ID_CONTA"));
					t.setVlTransacao(rs.getDouble("VL_TRANSACAO"));
					t.setNmTransacao(rs.getString("NM_TRANSACAO"));
					t.setDsTransacao(rs.getString("DS_TRANSACAO"));
					t.setNmSegundaPessoa(rs.getString("NM_SEGUNDA_PESSOA"));
					t.setNrContaSegPessoa(rs.getInt("NR_CONTA_SEG_PESSOA"));
					t.setDtTransacao(rs.getDate("DT_TRANSACAO"));
					t.setIdRecorrencia(rs.getInt("ID_RECORRENCIA"));
					t.setTpTransacao(rs.getString("TP_TRANSACAO"));
					lista.add(t);
				}
			}
		}
		return lista;
	}

	public List<Transacao> listarUltimasPorUsuario(Long idUsuario, int limite) throws Exception {
		List<Transacao> lista = new ArrayList<>();
		String sql = "SELECT * FROM TB_TRANSACAO WHERE TB_USUARIO_ID_USUARIO = ? ORDER BY DT_TRANSACAO DESC FETCH FIRST ? ROWS ONLY";

		try (Connection conn = ConnectionManager.getConnection();
			 PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setLong(1, idUsuario);
			stmt.setInt(2, limite);

			try (ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) {
					Transacao t = new Transacao();
					t.setIdTransacao(rs.getInt("ID_TRANSACAO"));
					t.setIdUsuario(rs.getInt("TB_USUARIO_ID_USUARIO"));
					t.setIdConta(rs.getInt("TB_CONTA_ID_CONTA"));
					t.setVlTransacao(rs.getDouble("VL_TRANSACAO"));
					t.setNmTransacao(rs.getString("NM_TRANSACAO"));
					t.setDsTransacao(rs.getString("DS_TRANSACAO"));
					t.setNmSegundaPessoa(rs.getString("NM_SEGUNDA_PESSOA"));
					t.setNrContaSegPessoa(rs.getInt("NR_CONTA_SEG_PESSOA"));
					t.setDtTransacao(rs.getDate("DT_TRANSACAO"));
					t.setIdRecorrencia(rs.getInt("ID_RECORRENCIA"));
					t.setTpTransacao(rs.getString("TP_TRANSACAO"));
					lista.add(t);
				}
			}
		}
		return lista;
	}


	public void inserir(Transacao t) throws Exception {
		String sql = "INSERT INTO TB_TRANSACAO " +
				"(ID_TRANSACAO, TB_USUARIO_ID_USUARIO, TB_CONTA_ID_CONTA, VL_TRANSACAO, NM_TRANSACAO, DS_TRANSACAO, NM_SEGUNDA_PESSOA, NR_CONTA_SEG_PESSOA, DT_TRANSACAO, ID_RECORRENCIA, TP_TRANSACAO) " +
				"VALUES (SEQ_ID_TRANSACAO.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		try (Connection conn = ConnectionManager.getConnection();
		     PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setInt(1, t.getIdUsuario());
			stmt.setInt(2, t.getIdConta());
			stmt.setDouble(3, t.getVlTransacao());
			stmt.setString(4, t.getNmTransacao());
			stmt.setString(5, t.getDsTransacao());
			stmt.setString(6, t.getNmSegundaPessoa());
			stmt.setInt(7, t.getNrContaSegPessoa());

			if (t.getDtTransacao() != null)
				stmt.setDate(8, new java.sql.Date(t.getDtTransacao().getTime()));
			else
				stmt.setNull(8, java.sql.Types.DATE);

			if (t.getIdRecorrencia() > 0)
				stmt.setInt(9, t.getIdRecorrencia());
			else
				stmt.setNull(9, java.sql.Types.INTEGER);

			stmt.setString(10, t.getTpTransacao());

			stmt.executeUpdate();
		}
	}
}
