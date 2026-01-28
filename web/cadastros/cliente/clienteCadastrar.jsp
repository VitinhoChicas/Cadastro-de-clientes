<%-- 
    Document   : clienteCadastrar
    Created on : 23 de jan. de 2026, 23:28:20
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
      <title>Cadastro de Cliente</title>
      <link rel="stylesheet" href="css/style.css">
  </head>
  <body>
      <jsp:include page="../../header.jsp"/>
      <jsp:include page="../../menu.jsp"/>

      <main class="container">
          <div class="page-header">
              <h2>
                  <c:choose>
                      <c:when test="${cliente.idCliente > 0}">Editar Cliente</c:when>
                      <c:otherwise>Novo Cliente</c:otherwise>
                  </c:choose>
              </h2>
          </div>

          <div class="form-container">

              <c:if test="${not empty erro}">
                  <div style="background: #f8d7da; color: #721c24; border: 1px solid #f5c6cb; padding: 15px; border-radius: 5px;
  margin-bottom: 20px; text-align: center; font-weight: bold;">
      ${erro}
  </div>
              </c:if>

              <form action="ClienteCadastrar" method="post" class="form">
                  <input type="hidden" name="idCliente" value="${cliente.idCliente}">

                  <div class="form-group">
                      <label for="nome">Nome:</label>
                      <input type="text" id="nome" name="nome" value="${cliente.nome}" required maxlength="200"
  placeholder="Digite o nome do cliente">
                  </div>

                  <div class="form-group">
                      <label for="email">E-mail:</label>
                      <input type="email" id="email" name="email" value="${cliente.email}" required maxlength="200"
  placeholder="Digite o e-mail">
                  </div>

  
    
  
                  <div class="form-group">
                      <label for="dataNascimento">Data de Nascimento:</label>
                      <input type="date" id="dataNascimento" name="dataNascimento" required value="<fmt:formatDate
  value='${cliente.dataNascimento}' pattern='yyyy-MM-dd'/>">
                  </div>

                  <div class="form-group">
                      <label for="telefone">Telefone:</label>
                      <input type="text" id="telefone" name="telefone" maxlength="11"  required value="${cliente.telefone}" 
  placeholder="Digite o telefone">
                  </div>

                  <div class="form-actions">
                      <button type="submit" class="btn btn-primary">Salvar</button>
                      <a href="ClienteListar" class="btn btn-secondary">Cancelar</a>
                  </div>
              </form>
          </div>
      </main>

      <jsp:include page="../../footer.jsp"/>

      <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
      <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.mask/1.14.16/jquery.mask.min.js"></script>
      
  </body>
  </html>