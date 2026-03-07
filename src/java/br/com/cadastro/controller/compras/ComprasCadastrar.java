/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.cadastro.controller.compras;

/**
 *
 * @author Vitinho
 */
import br.com.cadastro.dao.ClienteDAO;
import br.com.cadastro.dao.ClienteComprasDAO;
import br.com.cadastro.model.Cliente;
import br.com.cadastro.model.ClienteCompras;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ComprasCadastrar", urlPatterns = {"/ComprasCadastrar"})
public class ComprasCadastrar extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Busca todos os clientes para o dropdown
        ClienteDAO clienteDAO = new ClienteDAO();
        List<Cliente> clientes = clienteDAO.listar();

        // Verifica se está editando um cliente específico
        String idParam = request.getParameter("id");

        if (idParam != null && !idParam.isEmpty()) {

            int idCliente = Integer.parseInt(idParam);

            // Busca dados existentes
            ClienteComprasDAO comprasDAO = new ClienteComprasDAO();
            ClienteCompras compras = comprasDAO.buscarIdCliente(idCliente);

            if (compras != null) {
                // Cliente já tem registro de compras
                request.setAttribute("compras", compras);

            } else {
                // Cliente não tem registro, cria um novo vazio
                Cliente cliente = clienteDAO.carregar(idCliente);

                ClienteCompras novoRegistro = new ClienteCompras();
                novoRegistro.setIdCliente(idCliente);
                novoRegistro.setNomeCliente(cliente.getNome());
                novoRegistro.setQtdCompras(0);
                novoRegistro.setCreditos(0.0);

                request.setAttribute("compras", novoRegistro);
            }
        }

        request.setAttribute("clientes", clientes);

        request.getRequestDispatcher("/cadastros/compras/comprasCadastrar.jsp")
               .forward(request, response);
    }
}
