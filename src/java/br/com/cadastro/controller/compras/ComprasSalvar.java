/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.cadastro.controller.compras;

import br.com.cadastro.dao.ClienteComprasDAO;
import br.com.cadastro.dao.ClienteDAO;
import br.com.cadastro.model.Cliente;
import br.com.cadastro.model.ClienteCompras;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Vitinho
 */
@WebServlet(name = "ComprasSalvar", urlPatterns = {"/ComprasSalvar"})
public class ComprasSalvar extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //Pega dados do formulario
        int idCliente = Integer.parseInt(request.getParameter("idCliente"));
        int qtdCompras = Integer.parseInt(request.getParameter("qtdCompras"));
        double creditos = Double.parseDouble(request.getParameter("creditos"));

        // Busca nome do cliente
        ClienteDAO clienteDAO = new ClienteDAO();
        Cliente cliente = clienteDAO.carregar(idCliente);

        //monta o obj
        ClienteCompras compras = new ClienteCompras();
        compras.setIdCliente(idCliente);
        compras.setNomeCliente(cliente.getNome());
        compras.setQtdCompras(qtdCompras);
        compras.setCreditos(creditos);

        //salvar
        ClienteComprasDAO comprasDAO = new ClienteComprasDAO();
        comprasDAO.salvar(compras);

        //Redireciona para a lista
        response.sendRedirect("ComprasListar");
    }
}
