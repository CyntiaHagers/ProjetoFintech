<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="br.com.fiap.fintech.model.Endereco" %>
<%
  List<Endereco> enderecos = (List<Endereco>) request.getAttribute("listaEnderecos");
%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Lista de Endereços</title>
  <style>
    body {
      font-family: Arial, sans-serif;
      background-color: #001f2b;
      color: #e0f7f1;
      padding: 20px;
    }

    .container {
      max-width: 1000px;
      margin: 0 auto;
      background-color: #003344;
      border-radius: 10px;
      padding: 25px;
      box-shadow: 0 0 10px #0df2c4aa;
    }

    h2 {
      color: #0df2c4;
      margin-bottom: 20px;
    }

    table {
      width: 100%;
      border-collapse: collapse;
      margin-bottom: 20px;
    }

    th, td {
      padding: 10px;
      text-align: left;
      border-bottom: 1px solid #0df2c455;
    }

    th {
      color: #0df2c4;
    }

    .btn {
      padding: 6px 12px;
      border: none;
      border-radius: 4px;
      font-size: 14px;
      cursor: pointer;
      text-decoration: none;
      margin-right: 5px;
    }

    .btn-edit {
      background-color: #0df2c4;
      color: #001f2b;
    }

    .btn-delete {
      background-color: #e74c3c;
      color: white;
    }

    .btn-add {
      background-color: #0df2c4;
      color: white;
      font-weight: bold;
      padding: 10px 16px;
      display: inline-block;
      margin-bottom: 20px;
      border-radius: 5px;
      text-decoration: none;
    }

    .btn-add:hover {
      background-color: #1f6391;
    }
  </style>
</head>
<body>
<div class="container">
  <h2>Endereços Cadastrados</h2>

  <a href="formEndereco.jsp" class="btn-add">+ Adicionar Endereço</a>

  <table>
    <thead>
    <tr>
      <th>CEP</th>
      <th>Logradouro</th>
      <th>Cidade</th>
      <th>Estado</th>
      <th>Ações</th>
    </tr>
    </thead>
    <tbody>
    <%
      if (enderecos != null && !enderecos.isEmpty()) {
        for (Endereco e : enderecos) {
    %>
    <tr>
      <td><%= e.getCep() %></td>
      <td><%= e.getLogradouro() %></td>
      <td><%= e.getCidade() %></td>
      <td><%= e.getEstado() %></td>
      <td>
        <a href="endereco?action=edit&id=<%= e.getIdEndereco() %>" class="btn btn-edit">Editar</a>
        <a href="endereco?action=delete&id=<%= e.getIdEndereco() %>" class="btn btn-delete" onclick="return confirm('Deseja realmente excluir este endereço?');">Excluir</a>
      </td>
    </tr>
    <%
      }
    } else {
    %>
    <tr>
      <td colspan="5">Nenhum endereço cadastrado.</td>
    </tr>
    <%
      }
    %>
    </tbody>
  </table>
</div>
</body>
</html>





