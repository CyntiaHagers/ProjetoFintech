<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html lang="pt-BR">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1" />
  <title>Gerenciador de Metas</title>
  <link rel="stylesheet" href="resources/css/bootstrap.min.css" />
  <style>
    /* Seu CSS original mantido exatamente */
    body {
      display: flex;
      justify-content: center;
      align-items: center;
      height: 100vh;
      margin: 0;
      background-color: #001f2b;
      color: #e0f7f7;
      font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
    }

    .container {
      background-color: rgba(3, 51, 68, 0.85);
      border-radius: 1rem;
      padding: 2rem;
      box-shadow: 0 0 15px #0df2c4cc;
      max-width: 900px;
      width: 90vw;
      overflow-y: auto;
      max-height: 90vh;
    }

    h1 {
      color: #0df2c4;
      font-weight: 700;
      text-align: center;
      margin-bottom: 2rem;
    }

    label {
      color: #b0e0e6;
      font-weight: 600;
    }

    input.form-control, textarea.form-control {
      background-color: #003344;
      border: 1px solid #0df2c4;
      color: #e0f7f7;
    }
    input.form-control::placeholder, textarea.form-control::placeholder {
      color: #a0c7c7;
    }
    input.form-control:focus, textarea.form-control:focus {
      background-color: #005569;
      border-color: #0df2c4;
      color: white;
      box-shadow: 0 0 5px #0df2c4;
    }

    .descricao-expandida {
      width: 100% !important;
      min-width: 600px;
      max-width: 900px;
      box-sizing: border-box;
      resize: vertical;
    }

    .btn-primary {
      background-color: #0df2c4;
      border-color: #0df2c4;
      color: #001f2b;
      font-weight: 700;
      transition: background-color 0.3s ease;
    }
    .btn-primary:hover {
      background-color: #0bb5a9;
      border-color: #0bb5a9;
      color: #001f2b;
    }

    .btn-secondary {
      background-color: #1c2753;
      border-color: #1c2753;
      color: #a0c7c7;
      font-weight: 600;
      transition: background-color 0.3s ease;
    }
    .btn-secondary:hover {
      background-color: #25357a;
      border-color: #25357a;
      color: white;
    }

    .btn-warning {
      background-color: #25547a;
      border-color: #25547a;
      color: #b0e0e6;
      font-weight: 600;
    }
    .btn-warning:hover {
      background-color: #2e668f;
      border-color: #2e668f;
      color: white;
    }

    .btn-danger {
      background-color: #b23a48;
      border-color: #b23a48;
      color: #fceaea;
      font-weight: 700;
    }
    .btn-danger:hover {
      background-color: #a02f3f;
      border-color: #a02f3f;
      color: white;
    }

    .list-group-item {
      background-color: #003344;
      color: #d0f2f0;
      border: none;
      margin-bottom: 0.5rem;
      border-radius: 0.8rem;
      padding: 1rem 1.5rem;
      box-shadow: 0 0 5px #0df2c4aa;
    }

    .list-group-item:hover {
      background-color: #00495a;
      color: white;
      box-shadow: 0 0 10px #0df2c4ee;
    }

    .fw-bold.text-primary {
      color: #0df2c4 !important;
    }

    .progress {
      background-color: #001f2b;
      border-radius: 0.8rem;
      height: 16px;
    }
    .progress-bar.bg-danger {
      background-color: #b23a48 !important;
    }

    .progress-bar.bg-warning {
      background-color: #25547a !important;
    }

    .progress-bar.bg-success {
      background-color: #1abc9c !important;
    }

    .progress-bar.bg-primary {
      background-color: #0df2c4 !important;
      transition: width 0.5s ease;
    }

    }

    small.text-muted {
      color: #a0c7c7 !important;
    }

    /* Seu novo botão fora do container */
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
      position: fixed;
      bottom: 20px;
      right: 20px;
      z-index: 1050;
      box-shadow: 0 0 10px #0df2c4aa;
    }
    .btn-metas:hover {
      background-color: #25357a;
      color: white;
    }

  </style>
</head>
<body>

<div class="container my-4 p-4">
  <h1>Gerenciador de Metas</h1>

  <!-- Formulário para adicionar ou editar meta -->
  <form action="meta" method="post" class="row g-3 align-items-end">
    <input type="hidden" name="id" value="${metaParaEditar != null ? metaParaEditar.id : ''}" />
    <div class="col-md-6">
      <label for="titulo" class="form-label">Título da Meta</label>
      <input
              type="text"
              class="form-control"
              id="titulo"
              name="nome"
              value="${metaParaEditar != null ? metaParaEditar.nome : ''}"
              required
              placeholder="Título da Meta"
      />
    </div>
    <div class="col-md-3">
      <label for="valorMeta" class="form-label">Valor da Meta (R$)</label>
      <input
              type="number"
              class="form-control"
              id="valorMeta"
              name="valor"
              value="${metaParaEditar != null ? metaParaEditar.valor : ''}"
              required
              min="1"
              placeholder="Valor da Meta (R$)"
      />
    </div>
    <div class="col-md-3">
      <label for="valorAtual" class="form-label">Valor Atual (R$)</label>
      <input
              type="number"
              class="form-control"
              id="valorAtual"
              name="vlAtual"
              value="${metaParaEditar != null ? metaParaEditar.vlAtual : ''}"
              required
              min="0"
              placeholder="Valor Atual (R$)"
      />
    </div>

    <div class="col-12">
      <label for="descricao" class="form-label">Descrição</label>
      <textarea
              class="form-control descricao-expandida"
              id="descricao"
              name="descricao"
              rows="3"
              placeholder="Descrição da meta"
      >${metaParaEditar != null ? metaParaEditar.descricao : ''}</textarea>
    </div>

    <div class="col-md-4">
      <label for="dataPrazo" class="form-label">Prazo</label>
      <input
              type="date"
              class="form-control"
              id="dataPrazo"
              name="dataMeta"
              value="${metaParaEditar != null ? metaParaEditar.dataMeta : ''}"
              required
      />
    </div>
    <div class="col-md-8 d-flex gap-2">
      <button type="submit" class="btn btn-primary flex-grow-1">
        <c:choose>
          <c:when test="${metaParaEditar != null && metaParaEditar.id != null}">
            Atualizar Meta
          </c:when>
          <c:otherwise>
            Adicionar Meta
          </c:otherwise>
        </c:choose>
      </button>
      <a href="meta" class="btn btn-secondary flex-grow-1 <c:if test='${metaParaEditar == null}'>d-none</c:if>">Cancelar</a>
    </div>
  </form>

  <!-- Lista de metas -->
  <ul class="list-group mt-4">
    <c:choose>
      <c:when test="${empty listaMetas}">
        <li class="list-group-item text-center">Nenhuma meta cadastrada.</li>
      </c:when>
      <c:otherwise>
        <c:forEach var="meta" items="${listaMetas}" varStatus="loop">

          <!-- Cálculo seguro do progresso -->
          <c:choose>
            <c:when test="${meta.valor > 0}">
              <c:set var="progresso" value="${(meta.vlAtual * 100.0) / meta.valor}" />
            </c:when>
            <c:otherwise>
              <c:set var="progresso" value="0" />
            </c:otherwise>
          </c:choose>

          <li class="list-group-item">
            <div class="d-flex justify-content-between align-items-center flex-wrap gap-2">
              <div class="fw-bold text-primary flex-grow-1">${meta.nome}</div>
              <small class="text-muted">
                Prazo:
                <fmt:formatDate value="${meta.dataMeta}" pattern="dd/MM/yyyy" />
              </small>
              <div>
                <a href="meta?action=edit&id=${meta.id}" class="btn btn-warning btn-sm me-1">Editar</a>
                <a href="meta?action=delete&id=${meta.id}" class="btn btn-danger btn-sm" onclick="return confirm('Tem certeza que deseja excluir esta meta?')">Excluir</a>
              </div>
            </div>

            <div class="mt-2">
              <div>
                R$
                <fmt:formatNumber value="${meta.vlAtual}" type="currency" currencySymbol="" />
                /
                R$
                <fmt:formatNumber value="${meta.valor}" type="currency" currencySymbol="" />
              </div>
              <div>
                <small class="text-muted">${meta.descricao}</small>
              </div>

              <!-- Debug (remover se quiser) -->
              <small>${progresso}%</small>

              <!-- Barra de progresso -->
              <c:choose>
                <c:when test="${progresso >= 100}">
                  <c:set var="classeBarra" value="bg-success" />
                </c:when>
                <c:when test="${progresso >= 50}">
                  <c:set var="classeBarra" value="bg-warning" />
                </c:when>
                <c:otherwise>
                  <c:set var="classeBarra" value="bg-danger" />
                </c:otherwise>
              </c:choose>

              <div class="progress mt-2">
                <div
                        class="progress-bar ${classeBarra}"
                        role="progressbar"
                        style="width: ${progresso > 100 ? 100 : progresso}%;"
                        aria-valuenow="${progresso}"
                        aria-valuemin="0"
                        aria-valuemax="100"
                >
                  <fmt:formatNumber value="${progresso}" type="number" maxFractionDigits="0" />%
                </div>
              </div>
            </div>
          </li>
        </c:forEach>
      </c:otherwise>
    </c:choose>
  </ul>

</div>

<!-- Botão fixo fora do container -->
<a href="dashboard" class="btn-metas">← Voltar para página principal</a>


<script src="resources/js/bootstrap.bundle.min.js"></script>
</body>
</html>
