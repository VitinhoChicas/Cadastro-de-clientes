<%-- 
    Document   : comprasCadastrar
    Created on : 5 de mar. de 2026, 23:00:31
    Author     : Vitinho
--%>

<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setLocale value="pt_BR" />

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Cadastrar Compras</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">

    <style>
        
        .form-container {
            background: white;
            padding: 30px;
            border-radius: 12px;
            box-shadow: 0 2px 10px rgba(0,0,0,0.1);
            max-width: 550px;
        }

        
        .form-group {
            margin-bottom: 20px;
        }
        .form-group label {
            display: block;
            margin-bottom: 8px;
            font-weight: 600;
            color: #333;
        }
        .form-group input,
        .form-group select {
            width: 100%;
            padding: 12px 15px;
            font-size: 16px;
            border: 2px solid #ddd;
            border-radius: 8px;
        }
        .form-group input:focus,
        .form-group select:focus {
            border-color: #6c56f4;
            outline: none;
        }

        
        .form-row {
            display: flex;
            gap: 20px;
        }
        .form-row .form-group {
            flex: 1;
        }

        
        .cliente-info {
            background: linear-gradient(135deg, #6c56f4, #8b7cf7);
            color: white;
            padding: 20px;
            border-radius: 8px;
            margin-bottom: 25px;
        }
        .cliente-info h3 {
            margin: 0;
            font-size: 22px;
        }
        .cliente-info p {
            margin: 5px 0 0 0;
            opacity: 0.9;
        }

        
        .btn-salvar {
            background-color: #28a745;
            color: white;
            padding: 12px 30px;
            border: none;
            border-radius: 8px;
            font-size: 16px;
            cursor: pointer;
            margin-right: 10px;
        }
        .btn-salvar:hover {
            background-color: #218838;
        }
        .btn-cancelar {
            background-color: #6c757d;
            color: white;
            padding: 12px 30px;
            border: none;
            border-radius: 8px;
            font-size: 16px;
            text-decoration: none;
        }
        .btn-cancelar:hover {
            background-color: #5a6268;
        }
    </style>
</head>
<body>
    <%@ include file="/header.jsp" %>
    <%@ include file="/menu.jsp" %>

    <main class="container">

        
        <h1>
            <c:choose>
                <c:when test="${not empty compras}">
                    Editar Compras
                </c:when>
                <c:otherwise>
                    Cadastrar Compras
                </c:otherwise>
            </c:choose>
        </h1>

        <div class="form-container">

           
            <c:if test="${not empty compras}">
                <div class="cliente-info">
                    <h3>${compras.nomeCliente}</h3>
                    <p>Código do Cliente: ${compras.idCliente}</p>
                </div>
            </c:if>

            <form action="ComprasSalvar" method="POST">

               
                <c:choose>
                    <c:when test="${not empty compras}">
                        <input type="hidden" name="idCliente" value="${compras.idCliente}">
                    </c:when>
                    <c:otherwise>
                        <div class="form-group">
                            <label for="idCliente">Selecione o Cliente:</label>
                            <select id="idCliente" name="idCliente" required>
                                <option value="">-- Selecione --</option>
                                <c:forEach var="cliente" items="${clientes}">
                                    <option value="${cliente.idCliente}">
                                        ${cliente.nome}
                                    </option>
                                </c:forEach>
                            </select>
                        </div>
                    </c:otherwise>
                </c:choose>

                 
                <div class="form-row">
                    <div class="form-group">
                        <label for="qtdCompras">
                            🛒 Quantidade de Compras:
                        </label>
                        <input type="number"
                               id="qtdCompras"
                               name="qtdCompras"
                               min="0"
                               value="${not empty compras ? compras.qtdCompras : 0}"
                               required>
                    </div>

                    <div class="form-group">
                        <label for="creditos">
                            💰 Créditos (R$):
                        </label>
                        <input type="number"
                               id="creditos"
                               name="creditos"
                               step="0.01"
                               min="0"
                               value="${not empty compras ? compras.creditos : 0.00}"
                               required>
                    </div>
                </div>

               
                <button type="submit" class="btn-salvar">Salvar</button>
                <a href="ComprasListar" class="btn-cancelar">Cancelar</a>

            </form>
        </div>
    </main>

    <%@ include file="/footer.jsp" %>
</body>
</html>
