/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.cadastro.model;

import java.io.Serializable;

/**
 *
 * @author Vitinho
 */
public class ClienteCompras  implements Serializable{
    
    private static final long serialVersionUID = 1l;
    
    private int id;
    private int idCliente;
    private String nomeCliente;
    private int qtdCompras;
    private double creditos;

    public ClienteCompras() {
    }

    public ClienteCompras(int id, int idCliente, String nomeCliente, int qtdCompras, double creditos) {
        this.id = id;
        this.idCliente = idCliente;
        this.nomeCliente = nomeCliente;
        this.qtdCompras = qtdCompras;
        this.creditos = creditos;
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public int getQtdCompras() {
        return qtdCompras;
    }

    public void setQtdCompras(int qtdCompras) {
        this.qtdCompras = qtdCompras;
    }

    public double getCreditos() {
        return creditos;
    }

    public void setCreditos(double creditos) {
        this.creditos = creditos;
    }
    
    
    
    
    
}
