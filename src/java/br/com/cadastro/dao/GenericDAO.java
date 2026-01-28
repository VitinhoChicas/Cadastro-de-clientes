/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.com.cadastro.dao;

import java.util.List;

/**
 *
 * @author Vitinho
 */
public interface GenericDAO<T> {
   
    Boolean cadastrar (T objeto);
    Boolean inserir (T objeto);
    Boolean alterar (T objeto);
    Boolean excluir (int id);
    T carregar(int id);
    List<T> listar();
    
}
