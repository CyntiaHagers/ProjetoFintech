package br.com.fiap.fintech.controller;

import br.com.fiap.fintech.dao.TransacaoDao ;
import br.com.fiap.fintech.model.Usuario;
import jakarta.servlet.http.HttpServlet;
import br.com.fiap.fintech.model.Transacao;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@WebServlet("/transacao")
public class TransacaoServlet extends HttpServlet {

	private TransacaoDao dao;

	@Override
	public void init() throws ServletException {
		super.init();
		dao = new TransacaoDao();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Usuario usuario = (Usuario) req.getSession().getAttribute("usuarioLogado");
		if (usuario == null) {
			resp.sendRedirect("index.jsp");
			return;
		}
		Long idUsuario = usuario.getId();

		try {
			List<Transacao> transacoes = dao.listarPorUsuario(idUsuario);
			req.setAttribute("transacoes", transacoes);
			req.getRequestDispatcher("Transacao.jsp").forward(req, resp);
		} catch (Exception e) {
			throw new ServletException("Erro ao listar transações", e);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Usuario usuario = (Usuario) req.getSession().getAttribute("usuarioLogado");
		if (usuario == null) {
			resp.sendRedirect("index.jsp");
			return;
		}

		try {
			Transacao t = new Transacao();
            t.setIdUsuario(usuario.getId().intValue());
			t.setIdConta(Integer.parseInt(req.getParameter("idConta")));
			t.setVlTransacao(Double.parseDouble(req.getParameter("valor")));
			t.setNmTransacao(req.getParameter("nome"));
			t.setDsTransacao(req.getParameter("descricao"));
			t.setNmSegundaPessoa(req.getParameter("segundaPessoa"));
			t.setNrContaSegPessoa(Integer.parseInt(req.getParameter("contaSegundaPessoa")));

			String dataStr = req.getParameter("data");
			if (dataStr != null && !dataStr.isEmpty()) {
				Date data = new SimpleDateFormat("yyyy-MM-dd").parse(dataStr);
				t.setDtTransacao(data);
			}

			String idRecorrenciaStr = req.getParameter("idRecorrencia");
			if (idRecorrenciaStr != null && !idRecorrenciaStr.isEmpty()) {
				t.setIdRecorrencia(Integer.parseInt(idRecorrenciaStr));
			}

			t.setTpTransacao(req.getParameter("tipo"));

			dao.inserir(t);
			resp.sendRedirect("transacao");

		} catch (Exception e) {
			throw new ServletException("Erro ao registrar transação", e);
		}
	}
}