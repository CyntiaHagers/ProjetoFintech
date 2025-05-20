<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="br.com.fiap.fintech.model.Usuario" %>
<%@ page import="java.util.List" %>
<%
  HttpSession sessao = request.getSession(false);
  Usuario usuarioLogado = (sessao != null) ? (Usuario) sessao.getAttribute("usuarioLogado") : null;

  if (usuarioLogado == null) {
    response.sendRedirect("login.jsp");
    return;
  }
%>
<!DOCTYPE html>
<html>
<head>
  <title>Dashboard - FinanceEasy</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
  <style>
    body {
      background-color: #001f2b;
      color: white;
    }

    .sidebar {
      background-color: #003344;
      min-height: 100vh;
      padding: 2rem 1rem;
    }

    .sidebar h2 {
      color: #0df2c4;
      font-weight: bold;
      margin-bottom: 2rem;
    }

    .sidebar a {
      display: block;
      color: #b0d4df;
      padding: 0.8rem 1rem;
      margin-bottom: 0.5rem;
      border-radius: 8px;
      text-decoration: none;
      background-color: #1c2753;
    }

    .sidebar a:hover {
      background-color: #25357a;
      color: #fff;
    }

    .main-content {
      padding: 2rem;
    }

    .dashboard-header {
      margin-bottom: 2rem;
    }

    .card-custom {
      background-color: #003344;
      color: white;
      border: none;
      border-radius: 1rem;
    }

    .card-title {
      color: #0df2c4;
    }

    .right-panel {
      background-color: #002933;
      padding: 2rem;
      border-radius: 1rem;
    }

    .profile-img {
      width: 100%;
      border-radius: 50%;
      margin-bottom: 1rem;
    }

    .btn-metas {
      background-color: #1c2753;
      color: white;
      border: none;
    }

    .btn-metas:hover {
      background-color: #25357a;
    }

  </style>
</head>
<body>
<div class="container-fluid">
  <div class="row">
    <!-- Sidebar -->
    <div class="col-md-2 sidebar">
      <h2>FinanceEasy</h2>
      <a href="dashboard.jsp">Página Principal</a>
      <a href="endereco.jsp">Endereço</a>
      <a href="alertas.jsp">Alertas</a>
      <a href="contas.jsp">Contas</a>
      <a href="configuracoes.jsp">Configurações</a>
      <a href="LogoutServlet" class="mt-5">Sair</a>
    </div>

    <!-- Conteúdo Central -->
    <div class="col-md-7 main-content">
      <div class="dashboard-header">
        <h2>Bem-vindo, <%= usuarioLogado.getNome() %>!</h2>
        <p><strong>CPF:</strong> <%= usuarioLogado.getCpf() %></p>
      </div>

      <div class="row mb-4">
        <div class="col-md-4">
          <div class="card card-custom p-3">
            <h5 class="card-title">Saldo Total</h5>
            <p class="card-text">R$ 8.500,00</p>
          </div>
        </div>
        <div class="col-md-4">
          <div class="card card-custom p-3">
            <h5 class="card-title">Fatura em Crédito</h5>
            <p class="card-text">R$ 1.200,00</p>
          </div>
        </div>
        <div class="col-md-4">
          <div class="card card-custom p-3">
            <h5 class="card-title">Análise Mensal</h5>
            <p class="card-text">Renda: R$ 5.000 | Despesas: R$ 3.200</p>
          </div>
        </div>
      </div>

      <div class="card card-custom p-3">
        <h5 class="card-title">Últimas Transações</h5>
        <table class="table text-white mt-3">
          <thead>
          <tr>
            <th>Nome</th>
            <th>Categoria</th>
            <th>Valor</th>
            <th>Tipo</th>
          </tr>
          </thead>
          <tbody>
          <tr>
            <td>Salário</td>
            <td>Renda</td>
            <td>R$ 4.000,00</td>
            <td>Receita</td>
          </tr>
          <tr>
            <td>Mercado</td>
            <td>Alimentação</td>
            <td>R$ 350,00</td>
            <td>Despesa</td>
          </tr>
          <tr>
            <td>Academia</td>
            <td>Saúde</td>
            <td>R$ 120,00</td>
            <td>Despesa</td>
          </tr>
          </tbody>
        </table>
      </div>
    </div>

    <!-- Lateral Direita -->
    <div class="col-md-3">
      <div class="right-panel text-center">
        <img src="https://via.placeholder.com/150" class="profile-img" alt="Foto de Perfil">
        <h5>Minhas Metas</h5>
        <ul class="list-group list-group-flush text-white mb-3">
          <li class="list-group-item bg-transparent text-white">Viajar - R$ 3.000</li>
          <li class="list-group-item bg-transparent text-white">Reserva Emergência - R$ 5.000</li>
        </ul>
        <a href="metas.jsp" class="btn btn-metas">Ver Metas</a>
      </div>
    </div>
  </div>
</div>
</body>
</html>


