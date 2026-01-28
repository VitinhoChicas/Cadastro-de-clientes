<%-- 
    Document   : cliente
    Created on : 23 de jan. de 2026, 23:27:53
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
    <title>Lista de Clientes</title>
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="https://cdn.datatables.net/1.13.4/css/jquery.dataTables.min.css">
</head>
<body>
    <jsp:include page="../../header.jsp"/>
    <jsp:include page="../../menu.jsp"/>

    <main class="container">
        <div class="page-header">
            <h2>Lista de Clientes</h2>
            <a href="ClienteNovo" class="btn btn-primary">Novo Cliente</a>
        </div>

        <div class="table-container">
            <table id="tabelaClientes" class="display">
                <thead>
                    <tr>
                        <th>Código</th>
                        <th>Nome</th>
                        <th>E-mail</th>
                        <th>Data Nascimento</th>
                        <th>Telefone</th>
                        <th>Ações</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="cliente" items="${clientes}">
                        <tr>
                            <td>${cliente.idCliente}</td>
                            <td>${cliente.nome}</td>
                            <td>${cliente.email}</td>
                            <td><fmt:formatDate value="${cliente.dataNascimento}" pattern="dd/MM/yyyy"/></td>
                            <td>${cliente.telefone}</td>
                            <td class="actions">
                                <a href="ClienteCarregar?idCliente=${cliente.idCliente}" class="btn btn-edit">Editar</a>
                                <a href="ClienteExcluir?idCliente=${cliente.idCliente}" class="btn btn-delete" onclick="return confirm('Deseja realmente excluir este cliente?')">Excluir</a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </main>

    <jsp:include page="../../footer.jsp"/>

    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://cdn.datatables.net/1.13.4/js/jquery.dataTables.min.js"></script>
    <script src="js/app.js"></script>
    <script>
        $(document).ready(function() {
            $('#tabelaClientes').DataTable({
                "language": {
                    "url": "//cdn.datatables.net/plug-ins/1.13.4/i18n/pt-BR.json"
                },
                "pageLength": 10,
                "ordering": true,
                "searching": true
            });
        });
    </script>
</body>
</html>