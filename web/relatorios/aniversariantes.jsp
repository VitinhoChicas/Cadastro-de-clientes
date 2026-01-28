<%-- 
    Document   : aniversariantes
    Created on : 23 de jan. de 2026, 23:28:59
    Author     : Vitinho
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Relatório de Aniversariantes</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
    <jsp:include page="../header.jsp"/>
    <jsp:include page="../menu.jsp"/>

    <main class="container">
        <div class="page-header">
            <h2>Relatório de Aniversariantes por Mês</h2>
        </div>

        <!-- Filtro por Mês -->
        <div class="filter-container">
            <form action="RelatorioAniversariantes" method="get" class="filter-form">
                <label for="mes">Filtrar por mês:</label>
                <select name="mes" id="mes" onchange="this.form.submit()">
                    <option value="0" ${mesSelecionado == 0 ? 'selected' : ''}>Todos os meses</option>
                    <option value="1" ${mesSelecionado == 1 ? 'selected' : ''}>Janeiro</option>
                    <option value="2" ${mesSelecionado == 2 ? 'selected' : ''}>Fevereiro</option>
                    <option value="3" ${mesSelecionado == 3 ? 'selected' : ''}>Março</option>
                    <option value="4" ${mesSelecionado == 4 ? 'selected' : ''}>Abril</option>
                    <option value="5" ${mesSelecionado == 5 ? 'selected' : ''}>Maio</option>
                    <option value="6" ${mesSelecionado == 6 ? 'selected' : ''}>Junho</option>
                    <option value="7" ${mesSelecionado == 7 ? 'selected' : ''}>Julho</option>
                    <option value="8" ${mesSelecionado == 8 ? 'selected' : ''}>Agosto</option>
                    <option value="9" ${mesSelecionado == 9 ? 'selected' : ''}>Setembro</option>
                    <option value="10" ${mesSelecionado == 10 ? 'selected' : ''}>Outubro</option>
                    <option value="11" ${mesSelecionado == 11 ? 'selected' : ''}>Novembro</option>
                    <option value="12" ${mesSelecionado == 12 ? 'selected' : ''}>Dezembro</option>
                </select>
                <noscript><button type="submit" class="btn btn-primary">Filtrar</button></noscript>
            </form>
        </div>

        <div class="report-container">
            <c:choose>
                <c:when test="${empty aniversariantesPorMes}">
                    <div class="no-data">
                        <p>Nenhum cliente cadastrado no mês filtrado.</p>
                    </div>
                </c:when>
                <c:otherwise>
                    <c:forEach var="entrada" items="${aniversariantesPorMes}">
                        <div class="month-section">
                            <h3 class="month-title">${entrada.key}</h3>
                            <table class="report-table">
                                <thead>
                                    <tr>
                                        <th>Cliente</th>
                                        <th>Data Nasc.</th>
                                        <th>Telefone</th>
                                        <th>E-mail</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="cliente" items="${entrada.value}">
                                        <tr>
                                            <td>${cliente.nome}</td>
                                            <td><fmt:formatDate value="${cliente.dataNascimento}" pattern="dd/MM/yyyy"/></td>
                                            <td>${cliente.telefone}</td>
                                            <td>${cliente.email}</td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </c:forEach>
                </c:otherwise>
            </c:choose>
        </div>

        <div class="print-actions">
            <button onclick="window.print()" class="btn btn-primary">Imprimir Relatório</button>
            <a href="index.jsp" class="btn btn-secondary">Voltar</a>
        </div>
    </main>

    <jsp:include page="../footer.jsp"/>
</body>
</html>
