/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.cadastro.controller.relatorio;

import br.com.cadastro.dao.ClienteDAO;
import br.com.cadastro.model.Cliente;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
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



@WebServlet(name = "RelatorioAniversariantes", urlPatterns = {"/RelatorioAniversariantes"})
public class RelatorioAniversariantes extends HttpServlet {

    private ClienteDAO clienteDAO;

    private static final String[] MESES = {
        "Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho",
        "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"
    };

    @Override
    public void init() throws ServletException {
        clienteDAO = new ClienteDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

       
        
        String mesParam = request.getParameter("mes");
        int mesSelecionado = 0;

        if (mesParam != null && !mesParam.isEmpty()) {
            try {
                mesSelecionado = Integer.parseInt(mesParam);
            } catch (NumberFormatException e) {
                mesSelecionado = 0;
            }
        }


        Map<String, List<Cliente>> aniversariantesPorMes = new LinkedHashMap<>();

        if (mesSelecionado == 0) {
          
            for (int mes = 1; mes <= 12; mes++) {
                List<Cliente> aniversariantes = clienteDAO.listarAniversariantesPorMes(mes);
                if (!aniversariantes.isEmpty()) {
                    aniversariantesPorMes.put(MESES[mes - 1], aniversariantes);
                }
            }
        } else {
        
            List<Cliente> aniversariantes = clienteDAO.listarAniversariantesPorMes(mesSelecionado);
            if (!aniversariantes.isEmpty()) {
                aniversariantesPorMes.put(MESES[mesSelecionado - 1], aniversariantes);
            }
        }

        request.setAttribute("aniversariantesPorMes", aniversariantesPorMes);
        request.setAttribute("meses", MESES);
        request.setAttribute("mesSelecionado", mesSelecionado);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/relatorios/aniversariantes.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
