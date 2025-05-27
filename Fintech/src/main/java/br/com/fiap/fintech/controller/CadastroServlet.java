package br.com.fiap.fintech.controller;

import br.com.fiap.fintech.dao.UsuarioDao;
import br.com.fiap.fintech.model.Usuario;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/cadastro")
public class CadastroServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String cpf = request.getParameter("cpf");
        String nome = request.getParameter("nome");
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");
        String confirmarSenha = request.getParameter("confirmarSenha");

        // Validação simples da confirmação de senha
        if (!senha.equals(confirmarSenha)) {
            request.setAttribute("erro", "As senhas não coincidem.");
            // Redirecionar para o JSP mantendo os dados já preenchidos via parâmetros
            request.getRequestDispatcher("cadastro.jsp").forward(request, response);
            return;
        }

        Usuario usuario = new Usuario(cpf, nome, email, senha);

        try {
            UsuarioDao usuarioDao = new UsuarioDao();
            usuarioDao.cadastrarUsuario(usuario);
            usuarioDao.fecharConexao();

            // Mensagem de sucesso para mostrar na página index.jsp
            request.setAttribute("sucesso", "Cadastro realizado com sucesso! Faça login.");
            request.getRequestDispatcher("index.jsp").forward(request, response);

        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("erro", "Erro ao cadastrar usuário: " + e.getMessage());
            request.getRequestDispatcher("cadastro.jsp").forward(request, response);
        }
    }
}
