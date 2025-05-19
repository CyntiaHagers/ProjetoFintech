package br.com.fiap.fintech.controller;

import br.com.fiap.fintech.dao.EnderecoDao;
import br.com.fiap.fintech.model.Endereco;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.List;

@WebServlet("/endereco")
public class EnderecoServlet extends HttpServlet {

    private EnderecoDao enderecoDao;

    @Override
    public void init() {
        enderecoDao = new EnderecoDao();
    }

    // Para exibir a lista de endereços do usuário logado
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("id_usuario") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        long idUsuario = (Long) session.getAttribute("id_usuario");

        // Aqui, crie um método no DAO para listar só os endereços do usuário (vamos criar depois)
        List<Endereco> listaEnderecos = enderecoDao.getByUsuario(idUsuario);

        request.setAttribute("listaEnderecos", listaEnderecos);
        RequestDispatcher dispatcher = request.getRequestDispatcher("endereco.jsp");
        dispatcher.forward(request, response);
    }

    // Para salvar e deletar
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("id_usuario") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        long idUsuario = (Long) session.getAttribute("id_usuario");
        String action = request.getParameter("action");

        if ("delete".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));
            enderecoDao.delete(id);
            response.sendRedirect("endereco");
        } else if ("save".equals(action)) {
            // pega dados do formulário
            int cep = Integer.parseInt(request.getParameter("cep"));
            String logradouro = request.getParameter("logradouro");
            String estado = request.getParameter("estado");
            String cidade = request.getParameter("cidade");
            String bairro = request.getParameter("bairro");
            String residencia = request.getParameter("residencia");
            String complemento = request.getParameter("complemento");

            Endereco endereco = new Endereco();
            endereco.setIdUsuario(idUsuario);
            endereco.setCep(cep);
            endereco.setLogradouro(logradouro);
            endereco.setEstado(estado);
            endereco.setCidade(cidade);
            endereco.setBairro(bairro);
            endereco.setResidencia(residencia);
            endereco.setComplemento(complemento);

            enderecoDao.save(endereco);

            response.sendRedirect("endereco");
        } else {
            // caso ação desconhecida, redireciona para lista
            response.sendRedirect("endereco");
        }
    }
}

