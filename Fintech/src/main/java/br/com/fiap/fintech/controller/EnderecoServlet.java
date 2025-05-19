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
        enderecoDao = new EnderecoDao(); // instancie como necessário
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        if ("delete".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));
            enderecoDao.delete(id);
            response.sendRedirect("endereco");
        } else {
            List<Endereco> listaEnderecos = enderecoDao.getAll();
            request.setAttribute("listaEnderecos", listaEnderecos);
            RequestDispatcher dispatcher = request.getRequestDispatcher("endereco.jsp");
            dispatcher.forward(request, response);
        }
    }
}
