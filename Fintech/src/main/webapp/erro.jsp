<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <title>Erro</title>
    <link rel="stylesheet" href="resources/css/bootstrap.min.css" />
    <style>
        body {
            background-color: #001f2b;
            color: #e0f7f7;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
        }

        .error-container {
            background-color: rgba(3, 51, 68, 0.85);
            padding: 2rem 3rem;
            border-radius: 1rem;
            box-shadow: 0 0 15px #b23a48cc;
            text-align: center;
            max-width: 600px;
        }

        h1 {
            font-size: 2rem;
            color: #ff6b6b;
            margin-bottom: 1rem;
        }

        p {
            color: #f1f1f1;
            font-size: 1.2rem;
        }

        .btn-primary {
            margin-top: 2rem;
            background-color: #0df2c4;
            border-color: #0df2c4;
            color: #001f2b;
            font-weight: 600;
        }

        .btn-primary:hover {
            background-color: #0bb5a9;
            border-color: #0bb5a9;
        }
    </style>
</head>
<body>
<div class="error-container">
    <h1>Ops! Algo deu errado.</h1>
    <p>${mensagemErro != null ? mensagemErro : "Ocorreu um erro inesperado. Tente novamente mais tarde."}</p>
    <a href="meta" class="btn btn-primary">Voltar para o Gerenciador de Metas</a>
</div>
</body>
</html>
