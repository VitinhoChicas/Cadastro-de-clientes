/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.cadastro.controller.compras;

import br.com.cadastro.dao.ClienteComprasDAO;
import br.com.cadastro.model.ClienteCompras;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Vitinho
 */

@WebServlet(name = "ComprasListar", urlPatterns = {"/ComprasListar"})
public class ComprasListar extends HttpServlet{
    
    
     @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        
        ClienteComprasDAO dao = new ClienteComprasDAO();
        
        //Lista ordenada por quantidade de comrpas
        List<ClienteCompras> lista = dao.listarPorCompras();
        
        //Estatistica
        int totalCompras = dao.getTotalCompras();
        double totalCreditos = dao.getTotalCreditos();
        
        //Enviar para a view
        request.setAttribute("listaCompras", lista);
        request.setAttribute("totalCompras", totalCompras);
        request.setAttribute("totalCreditos", totalCreditos);
        
        request.getRequestDispatcher("/cadastros/compras/compras.jsp")
                .forward(request, response);
    }
    
    
    
}
