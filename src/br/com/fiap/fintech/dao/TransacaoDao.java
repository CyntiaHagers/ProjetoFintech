package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.Transacao;
import util.ConnectionManager;

public class TransacaoDao {

	// LISTA TRANSACOES POR USUARIO (Exemplo para extrato)
	public List<Transacao> listarPorUsuario(int idUsuario) throws Exception {
		List<Transacao> lista = new ArrayList<>();
		String sql = "SELECT * FROM TB_TRANSACAO WHERE TB_USUARIO_ID_USUARIO = ? ORDER BY DT_TRANSACAO DESC";

		try (Connection conn = ConnectionManager.getConnection();
		     PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setInt(1, idUsuario);

			try (ResultSet rs = stmt.executeQuery()) {
				ResultSetMetaData rsmd = rs.getMetaData();
				for (int i = 1; i <= rsmd.getColumnCount(); i++) {
					System.out.println("Coluna: " + rsmd.getColumnName(i));
				}

				while (rs.next()) {
					Transacao t = new Transacao();
					t.setIdTransacao(rs.getInt("ID_TRANSACAO"));
					t.setIdUsuario(rs.getInt("TB_USUARIO_ID_USUARIO"));
					t.setIdConta(rs.getInt("TB_CONTA_ID_CONTA"));
					t.setIeTransacao(rs.getInt("IE_TRANSACAO"));
					t.setVlTransacao(rs.getDouble("VL_TRANSACAO"));
					t.setNmTransacao(rs.getString("NM_TRANSACAO"));
					t.setDsTransacao(rs.getString("DS_TRANSACAO"));
					t.setNrFrequencia(rs.getInt("NR_FREQUENCIA"));
					t.setNrParcelas(rs.getInt("NR_PARCELAS"));
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

	// CADASTRAR NOVA TRANSACAO
	public void inserir(Transacao t) throws Exception {
		String sql = "INSERT INTO TB_TRANSACAO " +
				"(ID_TRANSACAO, TB_USUARIO_ID_USUARIO, TB_CONTA_ID_CONTA, IE_TRANSACAO, VL_TRANSACAO, NM_TRANSACAO, DS_TRANSACAO, NR_FREQUENCIA, NR_PARCELAS, NM_SEGUNDA_PESSOA, NR_CONTA_SEG_PESSOA, DT_TRANSACAO, ID_RECORRENCIA, TP_TRANSACAO) " +
				"VALUES (SEQ_ID_TRANSACAO.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		try (Connection conn = ConnectionManager.getConnection();
		     PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setInt(1, t.getIdUsuario());
			stmt.setInt(2, t.getIdConta());
			stmt.setInt(3, t.getIeTransacao());
			stmt.setDouble(4, t.getVlTransacao());
			stmt.setString(5, t.getNmTransacao());
			stmt.setString(6, t.getDsTransacao());
			stmt.setInt(7, t.getNrFrequencia());
			stmt.setInt(8, t.getNrParcelas());
			stmt.setString(9, t.getNmSegundaPessoa());
			stmt.setInt(10, t.getNrContaSegPessoa());
			if (t.getDtTransacao() != null)
				stmt.setDate(11, new java.sql.Date(t.getDtTransacao().getTime()));
			else
				stmt.setNull(11, java.sql.Types.DATE);
			if (t.getIdRecorrencia() > 0)
				stmt.setInt(12, t.getIdRecorrencia());
			else
				stmt.setNull(12, java.sql.Types.INTEGER);
			stmt.setString(13, t.getTpTransacao());

			stmt.executeUpdate();
		}
	}

	// Adicione aqui mais métodos (update, delete, filtros avançados) conforme for necessário
}
