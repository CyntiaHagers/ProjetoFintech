package br.com.fiap.fintech.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import br.com.fiap.fintech.model.Usuario;
import br.com.fiap.fintech.model.Meta;
import br.com.fiap.fintech.dao.MetaDao;

import java.io.IOException;
import java.util.List;

@WebServlet("/dashboard")
public class DashboardServlet extends HttpServlet {

    private MetaDao metaDao = new MetaDao();

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
            // Buscar metas do usuário usando o ID do usuário logado
            Long idUsuario = usuarioLogado.getId();
            List<Meta> listaMetas = metaDao.getMetasByUsuario(idUsuario);

            // Colocar as metas no request para a JSP
            request.setAttribute("listaMetas", listaMetas);
            request.setAttribute("usuarioLogado", usuarioLogado);

            // Encaminhar para a página dashboard.jsp
            request.getRequestDispatcher("dashboard.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("erro", "Erro ao carregar metas: " + e.getMessage());
            request.getRequestDispatcher("/erro.jsp").forward(request, response);
        }
    }
}
