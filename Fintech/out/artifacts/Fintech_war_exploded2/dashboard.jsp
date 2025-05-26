<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="br.com.fiap.fintech.model.Usuario" %>
<%
  HttpSession sessao = request.getSession(false);
  Usuario usuarioLogado = (sessao != null) ? (Usuario) sessao.getAttribute("usuarioLogado") : null;

  if (usuarioLogado == null) {
    response.sendRedirect("index.jsp");
    return;
  }
%>
<!DOCTYPE html>
<html>
<head>
  <title>Dashboard - FinanceEasy</title>
  <link href="resources/css/bootstrap.min.css" rel="stylesheet">
  <style>
    html, body {
      height: 100%;
      margin: 0;
      background-color: #001f2b;
      color: white;
    }
    .container-fluid {
      height: 100vh;
      display: flex;
      flex-direction: row;
      padding: 0;
      overflow: hidden;
    }

    .sidebar {
      background-color: #003344;
      min-width: 220px;
      padding: 2rem 1rem;
      display: flex;
      flex-direction: column;
      height: 100vh;
      box-sizing: border-box;
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
      transition: background-color 0.3s, color 0.3s;
    }
    .sidebar a:hover {
      background-color: #25357a;
      color: #fff;
    }
    .sidebar a.mt-5 {
      margin-top: auto;
    }

    .main-content {
      flex-grow: 1;
      padding: 1.5rem 2rem;
      height: 100vh;
      box-sizing: border-box;
      overflow-y: hidden;
      display: flex;
      flex-direction: column;
    }
    .dashboard-header {
      margin-bottom: 1rem;
    }
    .cards-row {
      display: flex;
      gap: 0.5rem;
      margin-bottom: 1rem;
      flex-wrap: nowrap;
      justify-content: flex-start;
    }
    .card-custom {
      background-color: #003344;
      color: white;
      border: none;
      border-radius: 1rem;
      padding: 1.5rem;
      display: flex;
      flex-direction: column;
      justify-content: center;
      text-align: center;
    }
    .card-title {
      color: #0df2c4;
      margin-bottom: 0.7rem;
      font-weight: 600;
    }

    .dashboard-bottom {
      display: flex;
      gap: 2rem;
      height: 330px;
    }

    .transactions-table {
      background-color: #003344;
      border-radius: 1rem;
      padding: 1.5rem;
      box-sizing: border-box;
      flex: 1;
      overflow-y: auto;
      height: 350px; /* Preenche a altura do pai */
    }

    .transactions-table table {
      width: 100%;
      color: white;
    }
    .transactions-table th {
      color: #0df2c4;
      font-weight: 600;
    }
    .transactions-table td {
      padding: 0.5rem;
    }

    .last-transactions-container {
      background-color: #003344;
      border-radius: 1rem;
      padding: 1.5rem;
      box-sizing: border-box;
      flex: 1;
      /* Remova a altura fixa */
      /* height: 370px !important; */
      min-height: 350px; /* opcional */
      display: flex;
      flex-direction: column;
      overflow: hidden;
      color: white;
      height: 100%; /* Faz o container preencher a altura do pai */
    }



    .grafico-title {
      margin: 0 0 1rem 0;        /* margem abaixo do título para separar do gráfico */
      text-align: center;
      flex-shrink: 0;            /* o título não encolhe */
    }

    #graficoPizza {
      flex: none;             /* não ocupa espaço flexível */
      height: 250px !important; /* altura menor para deixar espaço para legenda */
      max-width: 100%;
      width: 100% !important;
      display: block;
    }

    .right-panel {
      background-color: #002933;
      width: 280px;
      padding: 2rem 2rem;
      border-radius: 1rem 0 0 1rem;
      text-align: center;
      display: flex;
      flex-direction: column;
      gap: 2rem;
      height: 100vh;
      box-sizing: border-box;
      overflow-y: auto;
    }
    .profile-img-container {
      position: relative;
      width: 110px;
      height: 110px;
      margin: 0 auto;
      border-radius: 50%;
      overflow: hidden;
      box-shadow: 0 0 10px #0df2c4aa;
    }
    .profile-img-container img {
      width: 110px;
      height: 110px;
      object-fit: cover;
      border-radius: 50%;
    }
    .profile-text {
      position: absolute;
      top: 50%;
      left: 50%;
      transform: translate(-50%, -50%);
      color: #0df2c4;
      font-weight: 600;
      font-size: 0.85rem;
      line-height: 1.2;
      white-space: nowrap;
      text-align: center;
      width: 90px;
    }
    .right-panel h5 {
      color: #0df2c4;
      font-weight: 600;
      font-size: 1.5rem;
      margin-bottom: 0;
    }
    .list-group {
      padding-left: 0;
      margin-bottom: 0;
      color: #b0d4df;
      font-weight: 500;
      font-size: 1.1rem;
      text-align: left;
    }
    .list-group-item {
      border: none;
      background: transparent;
      margin-bottom: 1rem;
      padding-left: 0;
    }
    .btn-metas {
      background-color: #1c2753;
      color: white;
      border: none;
      padding: 0.7rem 1.5rem;
      font-size: 1.1rem;
      border-radius: 0.5rem;
      cursor: pointer;
      text-decoration: none;
      display: inline-block;
      transition: background-color 0.3s;
      align-self: center;
      margin-top: auto;
    }
    .btn-metas:hover {
      background-color: #25357a;
      color: white;
      text-decoration: none;
    }
  </style>
</head>
<body>
<div class="container-fluid">
  <!-- Barra Esquerda -->
  <div class="sidebar">
    <h2>FinanceEasy</h2>
    <a href="dashboard.jsp">Página Principal</a>
    <a href="endereco.jsp">Endereços </a>
    <a href="contas.jsp">Contas</a>
    <a href="transacao">Transações</a>
    <a href="LogoutServlet" class="mt-5">Sair</a>
  </div>

  <!-- Parte Central -->
  <div class="main-content">
    <div class="dashboard-header">
      <h2>Bem-vindo, <%= usuarioLogado.getNome() %>!</h2>
      <p><strong>CPF:</strong> <%= usuarioLogado.getCpf() %></p>
    </div>

    <div class="cards-row">
      <div class="card card-custom">
        <h5 class="card-title">Saldo Total</h5>
        <p style="font-size: 1.7rem; font-weight: 700; margin: 0;">R$ 8.500,00</p>
      </div>
      <div class="card card-custom">
        <h5 class="card-title">Fatura em Crédito</h5>
        <p style="font-size: 1.7rem; font-weight: 700; margin: 0;">R$ 1.200,00</p>
      </div>
      <div class="card card-custom" style="text-align: center;">
        <h5 class="card-title">Análise Mensal</h5>
        <p style="font-size: 1rem; font-weight: 600; margin: 0 0 0.6rem;">
          Renda: R$ 5.000 | Despesas: R$ 3.200
        </p>
        <canvas id="graficoMensal" style="max-height: 150px;"></canvas>
      </div>
    </div>

    <div class="dashboard-bottom">
      <!-- Últimas Transações -->
      <div class="transactions-table">
        <h5 class="card-title mb-4">Últimas Transações</h5>
        <table class="table table-sm text-white">
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
            <td>R$ 5.000,00</td>
            <td>Entrada</td>
          </tr>
          <tr>
            <td>Supermercado</td>
            <td>Alimentação</td>
            <td>R$ 600,00</td>
            <td>Saída</td>
          </tr>
          <tr>
            <td>Academia</td>
            <td>Saúde</td>
            <td>R$ 100,00</td>
            <td>Saída</td>
          </tr>
          </tbody>
        </table>
      </div>

      <!-- Gráfico de Pizza -->
      <div class="last-transactions-container">
        <h5 class="card-title mb-4">Gastos por Categoria</h5>
        <canvas id="graficoPizza"></canvas>
      </div>
    </div>
  </div>

  <!-- Barra Direita -->
  <div class="right-panel">
    <div class="profile-img-container">
      <img src="https://via.placeholder.com/150" alt="Foto de Perfil">
      <div class="profile-text">
        <div><%= usuarioLogado.getNome() %></div>
        <div>CPF: <%= usuarioLogado.getCpf() %></div>
      </div>
    </div>

    <h5>Minhas Metas</h5>
    <ul class="list-group list-group-flush mb-4">
      <li class="list-group-item">Viajar - R$ 3.000</li>
      <li class="list-group-item">Reserva Emergência - R$ 5.000</li>
    </ul>
    <a href="meta" class="btn-metas">Ver Metas</a>

  </div>
</div>

<script src="resources/js/bootstrap.bundle.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
<script>
  const ctxMensal = document.getElementById('graficoMensal').getContext('2d');
  new Chart(ctxMensal, {
    type: 'bar',
    data: {
      labels: ['Renda', 'Despesas'],
      datasets: [{
        label: 'Valores Mensais (R$)',
        data: [5000, 3200],
        backgroundColor: ['#0df2c4cc', '#ff6f61cc'],
        borderColor: ['#0df2c4', '#ff6f61'],
        borderWidth: 1
      }]
    },
    options: {
      responsive: true,
      maintainAspectRatio: false,
      scales: { y: { beginAtZero: true } },
      plugins: { legend: { display: false } }
    }
  });
</script>


  const ctxPizza = document.getElementById('graficoPizza').getContext('2d');
  new Chart(ctxPizza, {
    type: 'pie',
    data: {
      labels: ['Alimentação', 'Transporte', 'Lazer', 'Saúde', 'Outros'],
      datasets: [{
        data: [1200, 500, 400, 300, 300],
        backgroundColor: [
          '#0df2c4cc',
          '#bb86fcc9',
          '#ff6f61cc',
          '#62d2a2cc',
          '#ffe066cc'
        ],
        borderColor: '#001f2b',
        borderWidth: 2
      }]
    },
    options: {
      responsive: true,
      maintainAspectRatio: false,
      plugins: {
        legend: {
          position: 'bottom',
          labels: {
            color: '#b0d4df',
            font: { size: 14, weight: '600' }
          }
        }
      }
    }
  });
</script>
</body>
</html>


