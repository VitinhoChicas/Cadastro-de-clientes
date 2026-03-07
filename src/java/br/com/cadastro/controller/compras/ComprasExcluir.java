/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.cadastro.controller.compras;

import br.com.cadastro.dao.ClienteComprasDAO;
import java.io.IOException;
import java.rmi.ServerException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Vitinho
 */

@WebServlet(name = "ComprasExcluir", urlPatterns = {"/ComprasExcluir"})
public class ComprasExcluir extends HttpServlet{
    
    private ClienteComprasDAO comprasDAO;
    
    @Override
    public void init() throws ServletException{
        comprasDAO  = new ClienteComprasDAO();
    }
    
    @Override
    protected void doGet(HttpServletRequest request,  HttpServletResponse response)
            throws ServletException, IOException{
        
        String idStr = request.getParameter("id");
        
        if(idStr != null && !idStr.isEmpty()){
            int id = Integer.parseInt(idStr);
            comprasDAO.excluir(id);
        }
        
        response.sendRedirect("ComprasListar");
          }
    
      @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
