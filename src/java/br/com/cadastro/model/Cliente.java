/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.cadastro.model;
import java.io.Serializable;
import java.util.Date;

/*
Minhas anotações

Serializable : converter o objeto de uma forma que pode ser salvo ou enviado
*/

/**
 *
 * @author Vitinho
 */
public class Cliente implements Serializable{
    
   private static final long serialVersionUID = 1L;
    
    private int idCliente;
    private String nome;
    private String email;
    private Date dataNascimento;
    private String telefone;

    public Cliente() {
    }

    public Cliente(int idCliente, String nome, String email, Date dataNascimento, String telefone) {
        this.idCliente = idCliente;
        this.nome = nome;
        this.email = email;
        this.dataNascimento = dataNascimento;
        this.telefone = telefone;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    @Override
    public String toString() {
        return "Cliente{" + "idCliente=" + idCliente + ", nome=" + nome + ", email=" + email + ", dataNascimento=" + dataNascimento + ", telefone=" + telefone + '}';
    }
    
    
    
    
    
    
}
