  /*                                                                                                                       * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
   * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template                               */
  package br.com.cadastro.controller.cliente;

  import br.com.cadastro.dao.ClienteDAO;
  import br.com.cadastro.model.Cliente;
  import java.io.IOException;
  import java.text.ParseException;
  import java.text.SimpleDateFormat;
  import java.util.Date;
  import javax.servlet.ServletException;
  import javax.servlet.annotation.WebServlet;
  import javax.servlet.http.HttpServlet;
  import javax.servlet.http.HttpServletRequest;
  import javax.servlet.http.HttpServletResponse;

  /**
   *
   * @author Vitinho
   */
  @WebServlet(name = "ClienteCadastrar", urlPatterns = {"/ClienteCadastrar"})
  public class ClienteCadastrar extends HttpServlet {

      private ClienteDAO clienteDAO;

      @Override
      public void init() throws ServletException {
          clienteDAO = new ClienteDAO();
      }

      @Override
      protected void doGet(HttpServletRequest request, HttpServletResponse response)
              throws ServletException, IOException {
          doPost(request, response);
      }

      @Override
      protected void doPost(HttpServletRequest request, HttpServletResponse response)
              throws ServletException, IOException {

          request.setCharacterEncoding("UTF-8");

          String idStr = request.getParameter("idCliente");
          String nome = request.getParameter("nome");
          String email = request.getParameter("email");
          String dataNascimentoStr = request.getParameter("dataNascimento");
          String telefone = request.getParameter("telefone");

          int id = 0;
          if (idStr != null && !idStr.isEmpty()) {
              id = Integer.parseInt(idStr);
          }

          Date dataNascimento = null;
          if (dataNascimentoStr != null && !dataNascimentoStr.isEmpty()) {
              try {
                  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                  dataNascimento = sdf.parse(dataNascimentoStr);
              } catch (ParseException e) {
                  e.printStackTrace();
              }
          }

          Cliente cliente = new Cliente();
          cliente.setIdCliente(id);
          cliente.setNome(nome);
          cliente.setEmail(email);
          cliente.setDataNascimento(dataNascimento);
          cliente.setTelefone(telefone);

          // Verifica se o email ja existe no banco
          if (clienteDAO.emailExiste(email, id)) {
              request.setAttribute("erro", "Este e-mail ja esta cadastrado!");
              request.setAttribute("cliente", cliente);
              request.getRequestDispatcher("/cadastros/cliente/clienteCadastrar.jsp").forward(request, response);
              return;
          }

          clienteDAO.cadastrar(cliente);
          response.sendRedirect("ClienteListar");
      }
  }