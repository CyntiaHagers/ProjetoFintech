<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="pt-br">
<head>
  <meta charset="UTF-8" />
  <title>Lista de Endereços</title>
  <link href="${pageContext.request.contextPath}/resources/bootstrap.min.css" rel="stylesheet" />

  <!-- Fonte se usada na página de metas -->
  <link href="https://fonts.googleapis.com/css2?family=Roboto&display=swap" rel="stylesheet">

  <style>
    body {
      font-family: 'Roboto', sans-serif;
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
      box-shadow: 0 4px 12px rgba(0, 0, 0, 0.2);
    }

    .btn-metas:hover {
      background-color: #25357a;
      color: white;
    }

    .btn-voltar {
      position: fixed;
      bottom: 20px;
      right: 20px;
      z-index: 1050;
    }
  </style>
</head>

<body>

<div class="container mt-5">
  <div class="d-flex justify-content-between align-items-center mb-3">
    <h2>Endereços Cadastrados</h2>
    <a href="formEndereco.jsp" class="btn btn-success">+ Adicionar Endereço</a>
  </div>

  <c:if test="${not empty listaEnderecos}">
    <table class="table table-bordered table-striped">
      <thead class="table-dark">
      <tr>
        <th>ID Endereço</th>
        <th>ID Usuário</th>
        <th>CEP</th>
        <th>Logradouro</th>
        <th>Estado</th>
        <th>Cidade</th>
        <th>Bairro</th>
        <th>Residência</th>
        <th>Complemento</th>
        <th>Ações</th>
      </tr>
      </thead>
      <tbody>
      <c:forEach var="endereco" items="${listaEnderecos}">
        <tr>
          <td>${endereco.idEndereco}</td>
          <td>${endereco.idUsuario}</td>
          <td>${endereco.cep}</td>
          <td>${endereco.logradouro}</td>
          <td>${endereco.estado}</td>
          <td>${endereco.cidade}</td>
          <td>${endereco.bairro}</td>
          <td>${endereco.residencia}</td>
          <td>${endereco.complemento}</td>
          <td>
            <a href="formEndereco.jsp?id=${endereco.idEndereco}" class="btn btn-sm btn-warning">Editar</a>
            <a href="endereco?action=delete&id=${endereco.idEndereco}"
               class="btn btn-sm btn-danger"
               onclick="return confirm('Deseja excluir este endereço?');">Excluir</a>
          </td>
        </tr>
      </c:forEach>
      </tbody>
    </table>
  </c:if>

  <c:if test="${empty listaEnderecos}">
    <div class="alert alert-info">Nenhum endereço cadastrado.</div>
  </c:if>
</div>

<!-- Botão fixo "Voltar para página principal" -->
<div class="btn-voltar">
  <a href="${pageContext.request.contextPath}/dashboard.jsp" class="btn-metas">
    ← Voltar para página principal
  </a>
</div>

<script src="${pageContext.request.contextPath}/resources/bootstrap.bundle.min.js"></script>
</body>
</html>
