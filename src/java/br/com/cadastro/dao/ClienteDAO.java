/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.cadastro.dao;

import br.com.cadastro.model.Cliente;
import br.com.cadastro.utils.SingleConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/*
Minhas anotações 

Catch : impede que o sistema quebre ,  desfaz a transação, mostra o erro e avisa o que deu problema


*/




/**
 *
 * @author Vitinho
 */
public class ClienteDAO implements GenericDAO<Cliente> {

    private Connection connection;

    public ClienteDAO() {
        connection = SingleConnection.getConnection();
    }

    @Override
    public Boolean cadastrar(Cliente cliente) {
        if (cliente.getIdCliente() == 0) {
            return inserir(cliente);
        } else {
            return alterar(cliente);
        }
    }

    @Override
    public Boolean inserir(Cliente cliente) {
        String sql = "INSERT INTO cliente (nome, email, datanascimento, telefone) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, cliente.getNome());
            ps.setString(2, cliente.getEmail());
            
           if (cliente.getDataNascimento() != null) {
    ps.setDate(3, new java.sql.Date(cliente.getDataNascimento().getTime()));
} else {
    ps.setNull(3, java.sql.Types.DATE);
}
            ps.setString(4, cliente.getTelefone());
            ps.execute();
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

    @Override
    public Boolean alterar(Cliente cliente) {
        String sql = "UPDATE cliente SET nome = ?, email = ?, datanascimento = ?, telefone = ? WHERE idcliente = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, cliente.getNome());
            ps.setString(2, cliente.getEmail());
            
            if (cliente.getDataNascimento() != null) {
    ps.setDate(3, new java.sql.Date(cliente.getDataNascimento().getTime()));
} else {
    ps.setNull(3, java.sql.Types.DATE);
}
            
            ps.setString(4, cliente.getTelefone());
            ps.setInt(5, cliente.getIdCliente());
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

    @Override
    public Boolean excluir(int id) {
        String sql = "DELETE FROM cliente WHERE idcliente = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ps.execute();
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

    @Override
    public Cliente carregar(int id) {
        Cliente cliente = new Cliente();
        String sql = "SELECT * FROM cliente WHERE idcliente = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                cliente.setIdCliente(rs.getInt("idcliente"));
                cliente.setNome(rs.getString("nome"));
                cliente.setEmail(rs.getString("email"));
                cliente.setDataNascimento(rs.getDate("datanascimento"));
                cliente.setTelefone(rs.getString("telefone"));
                
                
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cliente;
    }

    @Override
    public List<Cliente> listar() {
        List<Cliente> clientes = new ArrayList<>();
        String sql = "SELECT * FROM cliente ORDER BY nome";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setIdCliente(rs.getInt("idcliente"));
                cliente.setNome(rs.getString("nome"));
                cliente.setEmail(rs.getString("email"));
                cliente.setDataNascimento(rs.getDate("datanascimento"));
                cliente.setTelefone(rs.getString("telefone"));
                clientes.add(cliente);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clientes;
    }

    public List<Cliente> listarAniversariantesPorMes(int mes) {
        List<Cliente> clientes = new ArrayList<>();
        String sql = "SELECT * FROM cliente WHERE EXTRACT(MONTH FROM datanascimento) = ? ORDER BY EXTRACT(DAY FROM datanascimento), nome";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, mes);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setIdCliente(rs.getInt("idcliente"));
                cliente.setNome(rs.getString("nome"));
                cliente.setEmail(rs.getString("email"));
                cliente.setDataNascimento(rs.getDate("datanascimento"));
                cliente.setTelefone(rs.getString("telefone"));
                clientes.add(cliente);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clientes;
    }
    
    
    
   public Boolean emailExiste(String email, int idCliente) {
      String sql = "SELECT COUNT(*) FROM cliente WHERE email = ? AND idcliente != ?";
      try {
          PreparedStatement ps = connection.prepareStatement(sql);
          ps.setString(1, email);
          ps.setInt(2, idCliente);
          ResultSet rs = ps.executeQuery();
          if (rs.next()) {
              return rs.getInt(1) > 0;
          }
      } catch (SQLException e) {
          e.printStackTrace();
      }
      return false;
  }
}
        
  