package br.com.fiap.fintech.controller;

import br.com.fiap.fintech.dao.ConnectionManager;
import br.com.fiap.fintech.dao.EnderecoDao;
import br.com.fiap.fintech.model.Endereco;
import br.com.fiap.fintech.model.Usuario;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.Connection;

@WebServlet("/endereco")
public class EnderecoServlet extends HttpServlet {

    private EnderecoDao enderecoDao;
    private Connection conexao;

    @Override
    public void init() {
        conexao = ConnectionManager.getInstance().getConnection();
        enderecoDao = new EnderecoDao(conexao);
    }

    @Override
    public void destroy() {
        try {
            if (conexao != null && !conexao.isClosed()) {
                conexao.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idStr = request.getParameter("id");
        if (idStr != null && !idStr.isEmpty()) {
            try {
                int id = Integer.parseInt(idStr);
                Endereco endereco = enderecoDao.getById(id);
                request.setAttribute("endereco", endereco);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            request.setAttribute("endereco", new Endereco());
        }
        request.getRequestDispatcher("/formEndereco.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String idStr = request.getParameter("id");
            int idEndereco = (idStr != null && !idStr.isEmpty()) ? Integer.parseInt(idStr) : 0;

            HttpSession session = request.getSession();
            Usuario usuarioLogado = (Usuario) session.getAttribute("usuarioLogado");
            if (usuarioLogado == null) {
                throw new IllegalArgumentException("Usuário não autenticado.");
            }
            long idUsuario = usuarioLogado.getId();

            // Limpa o CEP para conter somente números
            String cep = request.getParameter("cep");
            if (cep != null) {
                cep = cep.replaceAll("\\D", "");
            } else {
                cep = "";
            }

            String logradouro = request.getParameter("logradouro");
            String estado = request.getParameter("estado");
            String cidade = request.getParameter("cidade");
            String bairro = request.getParameter("bairro");
            String residenciaStr = request.getParameter("residencia");
            int residencia = (residenciaStr != null && !residenciaStr.isEmpty()) ? Integer.parseInt(residenciaStr) : 0;
            String complemento = request.getParameter("complemento");

            Endereco endereco = new Endereco(idEndereco, idUsuario, cep, logradouro, estado,
                    cidade, bairro, residencia, complemento);

            enderecoDao.save(endereco);

            response.sendRedirect("enderecos");
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("erro", "Erro ao salvar endereço: " + e.getMessage());
            request.getRequestDispatcher("/formEndereco.jsp").forward(request, response);
        }
    }
}


