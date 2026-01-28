/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.cadastro.filter;

import br.com.cadastro.utils.SingleConnection;
import java.io.IOException;
import java.util.logging.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

/**
 *
 * @author Vitinho
 */
@WebFilter(urlPatterns = {"/*"})
public class FilterConexao implements javax.servlet.Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Inicializa a conexao com o banco de dados
        SingleConnection.getConnection();
        System.out.println("Filter inicializado - Conexao com banco estabelecida");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
      
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        System.out.println("Filter destruido");
    }
}
