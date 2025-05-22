<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <title>FinanceEasy - Login</title>

    <!-- Bootstrap CSS local -->
    <link href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">

    <style>
        body {
            background-color: #0c0c24;
            color: white;
            font-family: 'Segoe UI', sans-serif;
            margin: 0;
        }

        .main-container {
            display: flex;
            height: 100vh;
        }

        .left-section {
            flex: 1;
            display: flex;
            justify-content: center;
            align-items: center;
            padding: 2rem 4rem;
        }

        .login-box {
            display: flex;
            flex-direction: column;
            align-items: flex-start; /* conteúdo alinhado à esquerda */
            max-width: 400px;
            width: 100%;
        }

        .logo {
            font-size: 2rem;
            font-weight: bold;
            color: #0df2c4;
            margin-bottom: 0.3rem;
        }

        .create-account {
            font-size: 1rem;
            margin-bottom: 2rem;
        }

        .form-control {
            background-color: #003344;
            color: white;
            border: none;
            font-size: 1.1rem;
            padding: 1rem;
        }

        .form-control::placeholder {
            color: #b0d4df;
        }

        .btn-primary {
            background-color: #1c2753;
            border: none;
            font-size: 1.1rem;
            padding: 0.8rem;
        }

        .btn-primary:hover {
            background-color: #25357a;
        }

        a.text-info {
            color: #0df2c4 !important;
            text-decoration: underline;
        }

        .right-section {
            flex: 1;
            display: flex;
            justify-content: center;
            align-items: center;
            padding: 2rem;
        }

        .right-section img {
            max-width: 100%;
            max-height: 80vh;
        }

        @media (max-width: 768px) {
            .main-container {
                flex-direction: column;
                text-align: center;
            }

            .left-section, .right-section {
                max-width: 100%;
                padding: 2rem;
            }

            .right-section img {
                margin-top: 2rem;
            }

            .login-box {
                align-items: center;
                text-align: center;
            }
        }
    </style>
</head>
<body>

<div class="main-container">
    <!-- Seção Esquerda -->
    <div class="left-section">
        <div class="login-box">
            <div class="logo">FinanceEasy</div>
            <div class="create-account">
                Não tem uma conta? <a href="cadastro.jsp" class="text-info">Criar Conta</a>
            </div>

            <c:if test="${not empty erro}">
                <div class="alert alert-danger">${erro}</div>
            </c:if>


            <form action="LoginServlet" method="post" class="w-100">
                <div class="mb-3">
                    <input type="email" class="form-control" placeholder="Email" name="email" required>
                </div>
                <div class="mb-3">
                    <input type="password" class="form-control" placeholder="Senha" name="senha" required>
                </div>
                <button type="submit" class="btn btn-primary w-100">Login</button>
            </form>
        </div>
    </div>

    <!-- Seção Direita -->
    <div class="right-section">
        <img src="./resources/login.png"Cofrinho">
    </div>
</div>

<!-- Bootstrap JS local -->
<script src="resources/js/bootstrap.bundle.min.js"></script>

</body>
</html>