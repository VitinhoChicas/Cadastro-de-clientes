<%-- 
    Document   : compras
    Created on : 5 de mar. de 2026, 22:52:25
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
    <title>Compras dos Clientes</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <link rel="stylesheet" href="https://cdn.datatables.net/1.13.4/css/jquery.dataTables.min.css">

    <style>
        .cards-container {
            display: flex;
            gap: 20px;
            margin-bottom: 25px;
        }
        .stat-card {
            flex: 1;
            padding: 20px 25px;
            border-radius: 12px;
            color: white;
            box-shadow: 0 4px 15px rgba(0,0,0,0.1);
        }
        .stat-card.compras {
            background: linear-gradient(135deg, #6c56f4, #8b7cf7);
        }
        .stat-card.creditos {
            background: linear-gradient(135deg, #28a745, #20c997);
        }
        .stat-card h3 {
            margin: 0 0 5px 0;
            font-size: 14px;
            opacity: 0.9;
        }
        .stat-card .valor {
            font-size: 32px;
            font-weight: bold;
        }

        
        .ranking {
            display: inline-block;
            width: 28px;
            height: 28px;
            border-radius: 50%;
            text-align: center;
            line-height: 28px;
            font-weight: bold;
            font-size: 14px;
        }
        .ranking-1 { background: linear-gradient(135deg, #ffd700, #ffec8b); color: #333; }
        .ranking-2 { background: linear-gradient(135deg, #c0c0c0, #e8e8e8); color: #333; }
        .ranking-3 { background: linear-gradient(135deg, #cd7f32, #daa06d); color: #fff; }
        .ranking-outros { background: #e0e0e0; color: #666; }

      
        .qtd-compras {
            color: #6c56f4;
            font-weight: bold;
            font-size: 18px;
        }
        .valor-credito {
            color: #28a745;
            font-weight: bold;
        }

        
        .btn-cadastrar {
            background-color: #6c56f4;
            color: white;
            padding: 10px 20px;
            border-radius: 8px;
            text-decoration: none;
            display: inline-block;
            margin-bottom: 20px;
        }
        .btn-cadastrar:hover {
            background-color: #5a45d6;
        }
        .btn-editar {
            background-color: #17a2b8;
            color: white;
            padding: 6px 14px;
            border-radius: 5px;
            text-decoration: none;
            font-size: 13px;
        }
        .btn-editar:hover {
            background-color: #138496;
        }
        
        .container{
           color: white;
        }
        
        .btnDelete{
            background-color: #666;
            color: white;
            padding: 6px 14px;
            border-radius: 5px;
            text-decoration: none;
            font-size: 13px;
        }
        .tdBT{
            display: flex;
        }
        
    </style>
</head>
<body>
    <%@ include file="/header.jsp" %>
    <%@ include file="/menu.jsp" %>

    <main class="container" style="main">
        <h1>Compras dos Clientes</h1>

        
        <div class="cards-container">
            <div class="stat-card compras">
                <h3>Total de Compras</h3>
                <div class="valor">${totalCompras}</div>
            </div>
            <div class="stat-card creditos">
                <h3>Total em Créditos</h3>
                <div class="valor">
                    <fmt:formatNumber value="${totalCreditos}" type="currency"/>
                </div>
            </div>
        </div>

        
        <a href="ComprasCadastrar" class="btn-cadastrar">+ Cadastrar Compras</a>

        
        <c:choose>
            <c:when test="${not empty listaCompras}">
                <table id="tabela-compras" class="display" style="width:100%">
                    <thead>
                        <tr>
                            <th width="60">Rank</th>
                            <th>Cliente</th>
                            <th>Qtd. Compras</th>
                            <th>Créditos</th>
                            <th width="100">Ações</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="item" items="${listaCompras}" varStatus="status">
                            <tr>
                                
                                <td>
                                    <c:choose>
                                        <c:when test="${status.index == 0}">
                                            <span class="ranking ranking-1">1</span>
                                        </c:when>
                                        <c:when test="${status.index == 1}">
                                            <span class="ranking ranking-2">2</span>
                                        </c:when>
                                        <c:when test="${status.index == 2}">
                                            <span class="ranking ranking-3">3</span>
                                        </c:when>
                                        <c:otherwise>
                                            <span class="ranking ranking-outros">
                                                ${status.index + 1}
                                            </span>
                                        </c:otherwise>
                                    </c:choose>
                                </td>

           
                                <td>${item.nomeCliente}</td>

                               
                                <td>
                                    <span class="qtd-compras">${item.qtdCompras}</span>
                                </td>

                                
                                <td>
                                    <span class="valor-credito">
                                        <fmt:formatNumber value="${item.creditos}" type="currency"/>
                                    </span>
                                </td>

                                
                                <td class="tdBT">
                                    <a href="ComprasEditar?id=${item.id}" class="btn-editar">
                                        Editar
                                    </a>
                                        <a href="ComprasExcluir?id=${item.id}" class="btnDelete" onclick="return confirm('Deseja realmente excluir ?')">Excluir</a>
                             
                                </td>
                                
                                 
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </c:when>
            <c:otherwise>
                <p>Nenhum registro de compras cadastrado.</p>
            </c:otherwise>
        </c:choose>
    </main>

    <%@ include file="/footer.jsp" %>

  
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://cdn.datatables.net/1.13.4/js/jquery.dataTables.min.js"></script>
    <script>
        $(document).ready(function() {
            $('#tabela-compras').DataTable({
                language: {
                    url: '//cdn.datatables.net/plug-ins/1.13.4/i18n/pt-BR.json'
                },
                order: [[2, 'desc']],  // Ordena por compras 
                columnDefs: [
                    { orderable: false, targets: [0, 4] }  // Desabilita ordenação no rank e ações
                ]
            });
        });
    </script>
</body>
</html>
