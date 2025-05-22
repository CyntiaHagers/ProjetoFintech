<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="br.com.fiap.fintech.model.Endereco" %>
<%
  Endereco endereco = (Endereco) request.getAttribute("endereco");
  String action = (endereco.getIdEndereco() == 0) ? "Cadastrar Endereço" : "Editar Endereço";
%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title><%= action %></title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
<div class="container mt-5">
  <div class="card shadow-lg">
    <div class="card-header bg-primary text-white">
      <h3 class="mb-0"><%= action %></h3>
    </div>
    <div class="card-body">
      <form action="endereco" method="post">
        <input type="hidden" name="action" value="save">
        <input type="hidden" name="id" value="<%= endereco.getIdEndereco() %>">

        <div class="row mb-3">
          <div class="col-md-6">
            <label class="form-label">CEP</label>
            <input type="text" name="cep" class="form-control" value="<%= endereco.getCep() != null ? endereco.getCep() : "" %>" required>
          </div>
          <div class="col-md-6">
            <label class="form-label">Logradouro</label>
            <input type="text" name="logradouro" class="form-control" value="<%= endereco.getLogradouro() != null ? endereco.getLogradouro() : "" %>" required>
          </div>
        </div>

        <div class="row mb-3">
          <div class="col-md-4">
            <label class="form-label">Estado</label>
            <input type="text" name="estado" class="form-control" value="<%= endereco.getEstado() != null ? endereco.getEstado() : "" %>" required>
          </div>
          <div class="col-md-4">
            <label class="form-label">Cidade</label>
            <input type="text" name="cidade" class="form-control" value="<%= endereco.getCidade() != null ? endereco.getCidade() : "" %>" required>
          </div>
          <div class="col-md-4">
            <label class="form-label">Bairro</label>
            <input type="text" name="bairro" class="form-control" value="<%= endereco.getBairro() != null ? endereco.getBairro() : "" %>" required>
          </div>
        </div>

        <div class="row mb-3">
          <div class="col-md-6">
            <label class="form-label">Número</label>
            <input type="text" name="residencia" class="form-control" value="<%= endereco.getResidencia() != null ? endereco.getResidencia() : "" %>" required>
          </div>
          <div class="col-md-6">
            <label class="form-label">Complemento</label>
            <input type="text" name="complemento" class="form-control" value="<%= endereco.getComplemento() != null ? endereco.getComplemento() : "" %>">
          </div>
        </div>

        <div class="d-flex justify-content-between">
          <a href="endereco" class="btn btn-secondary">Cancelar</a>
          <button type="submit" class="btn btn-success">Salvar</button>
        </div>
      </form>
    </div>
  </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>





