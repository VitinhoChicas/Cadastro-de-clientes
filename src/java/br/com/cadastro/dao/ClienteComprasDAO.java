/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.cadastro.dao;

import br.com.cadastro.model.Cliente;
import br.com.cadastro.model.ClienteCompras;
import br.com.cadastro.utils.SingleConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Vitinho
 */

/*

rollback - desfaz todas as alterações da transação atual do banco


try - executa um codigo que pode dar erro 
catch - codigo que executa se o try der erro 


*/







public class ClienteComprasDAO {

    private Connection connection;
    
    public ClienteComprasDAO() {
        this.connection = SingleConnection.getConnection();
    }
 
    //Metodo Listar
    public List<ClienteCompras> listarPorCompras(){
        
        List<ClienteCompras> lista = new ArrayList<>();
        
     try{
         String sql = "select * from cliente_compras order by qtd_compras DESC";
         
         PreparedStatement ps = connection.prepareStatement(sql);
         ResultSet rs = ps.executeQuery();
         
         while (rs.next()){
             ClienteCompras cc = new ClienteCompras();
             cc.setId(rs.getInt("id"));
             cc.setIdCliente(rs.getInt("id_cliente"));
             cc.setNomeCliente(rs.getString("nome_cliente"));
             cc.setQtdCompras(rs.getInt("qtd_compras"));
             cc.setCreditos(rs.getDouble("creditos"));
             lista.add(cc);
         }
         
     }   catch(Exception e){
         e.printStackTrace();
     }
     
     return lista;
    }
    
    //Buscar Por id cliente
    public ClienteCompras buscarIdCliente(int idCliente){
        
        ClienteCompras cc = null;
        
        try{
            String sql = "Select * from cliente_compras where id_cliente = ?";
            
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, idCliente);
            ResultSet rs = ps.executeQuery();
            
            if(rs.next()){
                cc = new ClienteCompras();
                cc.setId(rs.getInt("id"));
                cc.setIdCliente(rs.getInt("id_cliente"));
                
                cc.setNomeCliente(rs.getString("nome_cliente"));
                cc.setQtdCompras(rs.getInt("qtd_compras"));
                cc.setCreditos(rs.getDouble("creditos"));
            }
            
        }catch(Exception e){
            e.printStackTrace();
        }
        return cc;
    } 
    
   
    public ClienteCompras editar(int id) {
        ClienteCompras c = new ClienteCompras();
        String sql = "SELECT * FROM cliente_compras WHERE id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                c.setId(rs.getInt("id"));
                c.setIdCliente(rs.getInt("id_cliente"));
                c.setNomeCliente(rs.getString("nome_cliente"));
                c.setQtdCompras(rs.getInt("qtd_compras"));
                c.setCreditos(rs.getDouble("creditos"));
                
                
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return c;
    }
    
    
        
    public Boolean excluir(int id) {
        String sql = "DELETE FROM cliente_compras WHERE id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
            connection.commit();
            return true;
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
            return false;
        }
    }

    
    //verifcar se ja existe registro
    public boolean existeRegistro(int idCliente){
        
        try{
            String sql = "Select count(*) from cliente_compras where id_cliente = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            
            ps.setInt(1, idCliente);
            ResultSet rs = ps.executeQuery();
            
            if(rs.next()){
                return rs.getInt(1) > 0;
            }
            
        }catch(Exception e){
            e.printStackTrace();
        }
        return false ;
    }
    
    
    //inserir
    public Boolean inserir(ClienteCompras cc){
        
        try{
            String sql = "Insert into cliente_compras" + "(id_cliente, nome_cliente, qtd_compras, creditos)" + "Values(?,?,?,?)";
            
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, cc.getIdCliente());
            ps.setString(2, cc.getNomeCliente());
            ps.setInt(3, cc.getQtdCompras());
            ps.setDouble(4, cc.getCreditos());
            
            ps.executeUpdate();
            connection.commit();
            
            return true;
           
        } catch (Exception e){
            try{
                connection.rollback();
            }catch (Exception ex){
                ex.printStackTrace();
            }
            e.printStackTrace();
            return false;
        }
    }
    
    
    //Atualizar registro
    public Boolean atualizar(ClienteCompras cc){
        try{
            String sql = "update cliente_compras" + " set qtd_compras = ?, creditos = ?" + " where id_cliente = ?";
            
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1,cc.getQtdCompras());
            ps.setDouble(2, cc.getCreditos());
            ps.setInt(3, cc.getIdCliente());
            
            ps.executeUpdate();
            connection.commit();
            
            return true;
        }catch(Exception e){
            try{
                connection.rollback();
            }catch(Exception ex){
                ex.printStackTrace();
            }
            e.printStackTrace();
            return false;
        }
    }
    
    
    //Salvar
    
    public Boolean salvar(ClienteCompras cc){
        if(existeRegistro(cc.getIdCliente())){
            return atualizar(cc);
        } else {
            return inserir(cc);
        }
    }
    
    //Total de compras
    
    public int getTotalCompras(){
        
        try{
            String sql = "select sum(qtd_compras) from cliente_compras";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            if(rs.next()){
                return rs.getInt(1);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        
        return 0;
    }
    
    
    
    //total creditos
    
    public double getTotalCreditos(){
        try{
            String sql = "select sum(creditos) from cliente_compras";
            
            
                   PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getDouble(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0.0;
    }
    
    
    
}
