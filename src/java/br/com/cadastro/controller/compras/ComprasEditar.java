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
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Vitinho
 */

@WebServlet(name = "ComprasEditar", urlPatterns = {"/ComprasEditar"})

public class ComprasEditar extends HttpServlet {
    
    private ClienteComprasDAO c;
    
    @Override
    public void init() throws ServletException {
        c = new ClienteComprasDAO();
    }
    
     @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String idStr = request.getParameter("id");

        if (idStr != null && !idStr.isEmpty()) {
            int id = Integer.parseInt(idStr);
            ClienteCompras cc = c.editar(id);
            request.setAttribute("compras", cc);
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("/cadastros/compras/comprasCadastrar.jsp");
        dispatcher.forward(request, response);
    }
    
    
    
    
    
    
    
    
}
