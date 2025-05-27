package br.com.fiap.fintech.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import br.com.fiap.fintech.model.Usuario;
import br.com.fiap.fintech.model.Meta;
import br.com.fiap.fintech.model.Transacao;
import br.com.fiap.fintech.dao.MetaDao;
import br.com.fiap.fintech.dao.TransacaoDao;

import java.io.IOException;
import java.util.List;

@WebServlet("/dashboard")
public class DashboardServlet extends HttpServlet {

    private MetaDao metaDao = new MetaDao();
    private TransacaoDao transacaoDao = new TransacaoDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Verificar sessão e usuário logado
        HttpSession session = request.getSession(false);
        if (session == null) {
            response.sendRedirect("index.jsp");
            return;
        }

        Usuario usuarioLogado = (Usuario) session.getAttribute("usuarioLogado");
        if (usuarioLogado == null) {
            response.sendRedirect("index.jsp");
            return;
        }

        try {
            Long idUsuario = usuarioLogado.getId();

            // Buscar metas do usuário
            List<Meta> listaMetas = metaDao.getMetasByUsuario(idUsuario);

            // Buscar as últimas 5 transações do usuário
            List<Transacao> ultimasTransacoes = transacaoDao.listarUltimasPorUsuario(idUsuario, 5);

            // Colocar dados no request
            request.setAttribute("listaMetas", listaMetas);
            request.setAttribute("ultimasTransacoes", ultimasTransacoes);
            request.setAttribute("usuarioLogado", usuarioLogado);

            // Encaminhar para a página dashboard.jsp
            request.getRequestDispatcher("dashboard.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("erro", "Erro ao carregar dados do dashboard: " + e.getMessage());
            request.getRequestDispatcher("/erro.jsp").forward(request, response);
        }
    }
}
