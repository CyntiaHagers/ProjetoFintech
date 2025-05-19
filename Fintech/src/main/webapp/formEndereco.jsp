<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
  <meta charset="UTF-8" />
  <title>Cadastro de Endereço</title>
  <link href="${pageContext.request.contextPath}/resources/bootstrap.min.css" rel="stylesheet" />
</head>
<body>
<div class="container mt-5">
  <h2>Cadastro de Endereço</h2>
  <form action="endereco" method="post">
    <div class="mb-3">
      <label for="idUsuario" class="form-label">ID Usuário</label>
      <input type="number" class="form-control" id="idUsuario" name="idUsuario" required />
    </div>
    <div class="mb-3">
      <label for="cep" class="form-label">CEP</label>
      <input type="number" class="form-control" id="cep" name="cep" required />
    </div>
    <div class="mb-3">
      <label for="logradouro" class="form-label">Logradouro</label>
      <input type="text" class="form-control" id="logradouro" name="logradouro" required />
    </div>
    <div class="mb-3">
      <label for="estado" class="form-label">Estado</label>
      <input type="text" class="form-control" id="estado" name="estado" required maxlength="2" />
    </div>
    <div class="mb-3">
      <label for="cidade" class="form-label">Cidade</label>
      <input type="text" class="form-control" id="cidade" name="cidade" required />
    </div>
    <div class="mb-3">
      <label for="bairro" class="form-label">Bairro</label>
      <input type="text" class="form-control" id="bairro" name="bairro" required />
    </div>
    <div class="mb-3">
      <label for="residencia" class="form-label">Residência</label>
      <input type="text" class="form-control" id="residencia" name="residencia" required />
    </div>
    <div class="mb-3">
      <label for="complemento" class="form-label">Complemento</label>
      <input type="text" class="form-control" id="complemento" name="complemento" />
    </div>
    <button type="submit" class="btn btn-primary">Salvar</button>
    <a href="listar-enderecos.jsp" class="btn btn-secondary">Voltar</a>
  </form>
</div>
<script src="${pageContext.request.contextPath}/resources/bootstrap.bundle.min.js"></script>
</body>
</html>
