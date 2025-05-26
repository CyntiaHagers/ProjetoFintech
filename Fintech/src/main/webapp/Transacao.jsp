<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="br.com.fiap.fintech.model.Transacao " %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <title>Transações</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background-color: #041C32;
            color: #ffffff;
        }
        .container-custom {
            background-color: #04293A;
            border-radius: 10px;
            padding: 20px;
            margin-top: 40px;
            box-shadow: 0 0 20px rgba(100, 255, 218, 0.2);
        }
        .btn-custom {
            background-color: #64FFDA;
            color: #000;
        }
        .form-label, th {
            color: #64FFDA;
        }
    </style>
</head>
<body>
<div class="container container-custom">
    <h2 class="mb-4">Cadastrar Transação</h2>
    <form method="post" action="transacao">
        <div class="row mb-3">
            <div class="col">
                <label class="form-label">Conta</label>
                <input type="number" name="idConta" class="form-control" required>
            </div>
            <div class="col">
                <label class="form-label">Valor</label>
                <input type="number" name="valor" class="form-control" required step="0.01">
            </div>
            <div class="col">
                <label class="form-label">Tipo</label>
                <select name="tipo" class="form-select" required>
                    <option value="E">Entrada</option>
                    <option value="S">Saída</option>
                </select>
            </div>
        </div>

        <div class="row mb-3">
            <div class="col">
                <label class="form-label">Nome</label>
                <input type="text" name="nome" class="form-control">
            </div>
            <div class="col">
                <label class="form-label">Descrição</label>
                <input type="text" name="descricao" class="form-control">
            </div>
        </div>

        <div class="row mb-3">
            <div class="col">
                <label class="form-label">Segunda Pessoa</label>
                <input type="text" name="segundaPessoa" class="form-control" required>
            </div>
            <div class="col">
                <label class="form-label">Conta da Segunda Pessoa</label>
                <input type="number" name="contaSegundaPessoa" class="form-control">
            </div>
            <div class="col">
                <label class="form-label">Data</label>
                <input type="date" name="data" class="form-control">
            </div>
        </div>

        <div class="mb-3">
            <label class="form-label">ID Recorrência (opcional)</label>
            <input type="number" name="idRecorrencia" class="form-control">
        </div>

        <div class="d-flex justify-content-end">
            <button type="submit" class="btn btn-custom">Salvar</button>
        </div>
    </form>

    <hr class="my-4">

    <h4>Transações Recentes</h4>
    <table class="table table-striped table-dark table-bordered">
        <thead>
        <tr>
            <th>Data</th>
            <th>Tipo</th>
            <th>Valor</th>
            <th>Descrição</th>
            <th>Segunda Pessoa</th>
        </tr>
        </thead>
        <tbody>
        <%
            List<Transacao> transacoes = (List<Transacao>) request.getAttribute("transacoes");
            if (transacoes != null) {
                for (Transacao t : transacoes) {
        %>
        <tr>
            <td><%= t.getDtTransacao() %></td>
            <td><%= "E".equals(t.getTpTransacao()) ? "Entrada" : "Saída" %></td>
            <td>R$ <%= t.getVlTransacao() %></td>
            <td><%= t.getDsTransacao() %></td>
            <td><%= t.getNmSegundaPessoa() %></td>
        </tr>
        <%
                }
            }
        %>
        </tbody>
    </table>
</div>
</body>
</html>
