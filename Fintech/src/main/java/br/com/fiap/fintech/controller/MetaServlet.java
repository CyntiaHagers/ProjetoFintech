package br.com.fiap.fintech.controller;

import br.com.fiap.fintech.dao.MetaDao;
import br.com.fiap.fintech.model.Meta;
import br.com.fiap.fintech.model.Usuario;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

@WebServlet("/meta")
public class MetaServlet extends HttpServlet {

    private final MetaDao metaDao = new MetaDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("usuarioLogado") == null) {
            response.sendRedirect("index.jsp");
            return;
        }

        String action = request.getParameter("action");

        try {
            if ("delete".equals(action)) {
                Long id = Long.parseLong(request.getParameter("id"));
                metaDao.delete(id);
                response.sendRedirect("meta");
                return;
            } else if ("edit".equals(action)) {
                Long id = Long.parseLong(request.getParameter("id"));
                Meta metaParaEditar = metaDao.getById(id);
                request.setAttribute("metaParaEditar", metaParaEditar);

                Usuario usuarioLogado = (Usuario) session.getAttribute("usuarioLogado");
                List<Meta> metas = metaDao.getMetasByUsuario(usuarioLogado.getId());
                request.setAttribute("listaMetas", metas);

                request.getRequestDispatcher("/metas.jsp").forward(request, response);
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("erro", "Erro ao processar ação: " + e.getMessage());
            request.getRequestDispatcher("/erro.jsp").forward(request, response);
            return;
        }

        // Caso nenhuma ação específica, apenas listar metas
        try {
            Usuario usuarioLogado = (Usuario) session.getAttribute("usuarioLogado");
            List<Meta> metas = metaDao.getMetasByUsuario(usuarioLogado.getId());
            request.setAttribute("listaMetas", metas);
            request.getRequestDispatcher("/metas.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("erro", "Erro ao carregar metas: " + e.getMessage());
            request.getRequestDispatcher("/erro.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("usuarioLogado") == null) {
            response.sendRedirect("index.jsp");
            return;
        }

        try {
            Usuario usuarioLogado = (Usuario) session.getAttribute("usuarioLogado");
            Long usuarioId = usuarioLogado.getId();

            String idStr = request.getParameter("id");
            String nome = request.getParameter("nome");
            double valor = Double.parseDouble(request.getParameter("valor"));
            double vlAtual = Double.parseDouble(request.getParameter("vlAtual"));
            String descricao = request.getParameter("descricao");
            String dataMetaStr = request.getParameter("dataMeta");

            Date dataMeta = Date.valueOf(dataMetaStr);

            if (idStr != null && !idStr.isEmpty()) {
                // Atualizar meta existente
                Long id = Long.parseLong(idStr);
                Meta metaEditada = new Meta();
                metaEditada.setId(id);
                metaEditada.setUsuarioId(usuarioId);
                metaEditada.setNome(nome);
                metaEditada.setValor(valor);
                metaEditada.setVlAtual(vlAtual);
                metaEditada.setDescricao(descricao);
                metaEditada.setDataMeta(dataMeta);

                metaDao.update(metaEditada);

            } else {
                // Salvar nova meta
                Meta novaMeta = new Meta();
                novaMeta.setUsuarioId(usuarioId);
                novaMeta.setNome(nome);
                novaMeta.setValor(valor);
                novaMeta.setVlAtual(vlAtual);
                novaMeta.setDescricao(descricao);
                novaMeta.setDataMeta(dataMeta);

                metaDao.save(novaMeta);
            }

            response.sendRedirect("meta");

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("erro", "Erro ao salvar meta: " + e.getMessage());
            request.getRequestDispatcher("/erro.jsp").forward(request, response);
        }
    }
}


