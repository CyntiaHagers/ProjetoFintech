<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="br.com.fiap.fintech.model.Endereco" %>
<%
  Endereco endereco = (Endereco) request.getAttribute("endereco");
  if (endereco == null) {
    endereco = new Endereco(); // evita NullPointerException caso o atributo não esteja setado
  }
  String action = (endereco.getIdEndereco() == 0) ? "Cadastrar Endereço" : "Editar Endereço";
%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title><%= action %></title>
  <style>
    body {
      font-family: Arial, sans-serif;
      background-color: #001f2b;
      color: #e0f7f1;
      padding: 20px;
    }

    .container {
      max-width: 900px;
      margin: 0 auto;
      background-color: #003344;
      border-radius: 10px;
      padding: 25px;
      box-shadow: 0 0 10px #0df2c4aa;
    }

    h3 {
      color: #0df2c4;
      margin-bottom: 20px;
    }

    label {
      display: block;
      margin-bottom: 5px;
      font-weight: bold;
      color: #e0f7f1;
    }

    input[type="text"],
    input[type="number"] {
      width: 100%;
      padding: 10px;
      margin-bottom: 15px;
      border: 1px solid #0df2c4aa;
      border-radius: 5px;
      background-color: #004959;
      color: #e0f7f1;
      box-sizing: border-box;
    }

    .btn-primary {
      background-color: #0df2c4;
      color: #001f2b;
      padding: 10px 20px;
      border: none;
      border-radius: 5px;
      font-size: 14px;
      cursor: pointer;
      text-decoration: none;
      transition: background-color 0.3s ease;
      font-weight: bold;
    }

    .btn-primary:hover {
      background-color: #0abca8;
      color: white;
    }

    .btn-secondary {
      background-color: #e74c3c;
      color: white;
      padding: 10px 20px;
      border: none;
      border-radius: 5px;
      font-size: 14px;
      cursor: pointer;
      text-decoration: none;
      font-weight: bold;
      transition: background-color 0.3s ease;
    }

    .btn-secondary:hover {
      background-color: #c0392b;
    }

    .actions {
      display: flex;
      justify-content: space-between;
      margin-top: 20px;
    }

    .row {
      display: flex;
      flex-wrap: wrap;
      gap: 20px;
    }

    .col-half {
      flex: 1 1 48%;
    }
  </style>
</head>
<body>
<div class="container">
  <h3><%= action %></h3>
  <form action="endereco" method="post">
    <input type="hidden" name="action" value="save">
    <input type="hidden" name="id" value="<%= endereco.getIdEndereco() %>">
    <input type="hidden" name="idUsuario" value="<%= endereco.getIdUsuario() %>">

    <div class="row">
      <div class="col-half">
        <label for="cep">CEP</label>
        <input type="text" id="cep" name="cep" value="<%= endereco.getCep() != null ? endereco.getCep() : "" %>" required>
      </div>
      <div class="col-half">
        <label for="logradouro">Logradouro</label>
        <input type="text" id="logradouro" name="logradouro" value="<%= endereco.getLogradouro() != null ? endereco.getLogradouro() : "" %>" required>
      </div>
    </div>

    <div class="row">
      <div class="col-half">
        <label for="estado">Estado</label>
        <input type="text" id="estado" name="estado" maxlength="2" value="<%= endereco.getEstado() != null ? endereco.getEstado() : "" %>" required>
      </div>
      <div class="col-half">
        <label for="cidade">Cidade</label>
        <input type="text" id="cidade" name="cidade" value="<%= endereco.getCidade() != null ? endereco.getCidade() : "" %>" required>
      </div>
    </div>

    <div class="row">
      <div class="col-half">
        <label for="bairro">Bairro</label>
        <input type="text" id="bairro" name="bairro" value="<%= endereco.getBairro() != null ? endereco.getBairro() : "" %>" required>
      </div>
      <div class="col-half">
        <label for="residencia">Número</label>
        <input type="number" id="residencia" name="residencia" value="<%= endereco.getResidencia() != 0 ? endereco.getResidencia() : "" %>" required>
      </div>
    </div>

    <label for="complemento">Complemento</label>
    <input type="text" id="complemento" name="complemento" value="<%= endereco.getComplemento() != null ? endereco.getComplemento() : "" %>">

    <div class="actions">
      <a href="endereco" class="btn-secondary">Cancelar</a>
      <button type="submit" class="btn-primary">Salvar</button>
    </div>

  </form>
</div>
</body>
</html>



