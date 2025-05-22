package br.com.fiap.fintech.controller;

import br.com.fiap.fintech.dao.EnderecoDao;
import br.com.fiap.fintech.model.Endereco;
import br.com.fiap.fintech.model.Usuario;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet("/endereco")
public class EnderecoServlet extends HttpServlet {

    private EnderecoDao enderecoDao;

    @Override
    public void init() throws ServletException {
        enderecoDao = new EnderecoDao(); // ou injeção, como preferir
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("form".equals(action)) {
            abrirFormulario(request, response);
        } else {
            listarEnderecos(request, response);
        }
    }

    private void abrirFormulario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        Endereco endereco;

        if (id != null && !id.isEmpty()) {
            // editar: buscar endereço por id
            endereco = enderecoDao.getById(Integer.parseInt(id));
        } else {
            // novo endereço vazio
            endereco = new Endereco();
        }

        request.setAttribute("endereco", endereco);
        request.getRequestDispatcher("formEndereco.jsp").forward(request, response);
    }

    private void listarEnderecos(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Endereco> listaEnderecos = enderecoDao.getAll();
        request.setAttribute("listaEnderecos", listaEnderecos);
        request.getRequestDispatcher("endereco.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("save".equals(action)) {
            salvarEndereco(request, response);
        } else if ("delete".equals(action)) {
            deletarEndereco(request, response);
        } else {
            response.sendRedirect("endereco");
        }
    }

    private void salvarEndereco(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String idStr = request.getParameter("id");
        int id = (idStr == null || idStr.isEmpty()) ? 0 : Integer.parseInt(idStr);

        Endereco endereco = new Endereco();
        if (id != 0) {
            endereco.setIdEndereco(id);
        }

        // Obter o usuário logado da sessão
        HttpSession session = request.getSession();
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
        
        if (usuario != null) {
            // Definir o ID do usuário no objeto endereco
            endereco.setIdUsuario(usuario.getId());
        } else {
            // Se não há usuário logado, redirecionar para login
            response.sendRedirect("LoginServlet");
            return;
        }
    
        endereco.setCep(request.getParameter("cep"));
        endereco.setLogradouro(request.getParameter("logradouro"));
        endereco.setEstado(request.getParameter("estado"));
        endereco.setCidade(request.getParameter("cidade"));
        endereco.setBairro(request.getParameter("bairro"));
        endereco.setResidencia(request.getParameter("residencia"));
        endereco.setComplemento(request.getParameter("complemento"));

        if (id == 0) {
            enderecoDao.save(endereco);
        } else {
            enderecoDao.update(endereco);
        }

        response.sendRedirect("endereco");
    }

    private void deletarEndereco(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String idStr = request.getParameter("id");
        if (idStr != null && !idStr.isEmpty()) {
            int id = Integer.parseInt(idStr);
            enderecoDao.delete(id);
        }
        response.sendRedirect("endereco");
    }
}
