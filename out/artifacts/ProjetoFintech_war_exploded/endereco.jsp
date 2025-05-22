<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="pt-br">
<head>
  <meta charset="UTF-8">
  <title>Endereços</title>
  <link href="${pageContext.request.contextPath}/resources/bootstrap.min.css" rel="stylesheet">
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
        <th>ID</th>
        <th>ID Usuário</th>
        <th>CEP</th>
        <th>Logradouro</th>
        <th>Cidade</th>
        <th>Estado</th>
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
          <td>${endereco.cidade}</td>
          <td>${endereco.estado}</td>
          <td>
            <a href="formEndereco.jsp?id=${endereco.idEndereco}" class="btn btn-sm btn-warning">Editar</a>
            <a href="endereco?action=delete&id=${endereco.idEndereco}" class="btn btn-sm btn-danger" onclick="return confirm('Deseja excluir este endereço?');">Excluir</a>
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
</body>
</html>
