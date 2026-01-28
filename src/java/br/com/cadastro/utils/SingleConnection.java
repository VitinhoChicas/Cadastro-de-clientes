/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.cadastro.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Vitinho
 */
public class SingleConnection {
    
    
  private static Connection connection = null;
  
  static{
      conectar();
  }
    
    private static void conectar(){
        try{
            if(connection == null || connection.isClosed()){
                Class.forName("org.postgresql.Driver");
                  connection = DriverManager.getConnection(
      "jdbc:postgresql://localhost:5432/Clientes?autoReconnect=true",
      "postgres",
      "1234"
  );
                
                connection.setAutoCommit(false);
                
                System.out.println("Conexão como  OK!");
            }
        }catch (ClassNotFoundException e){
            System.out.println("Driver não encontrado:" + e.getMessage());
        }catch(SQLException e){
            System.out.println("Erro na conexão :" + e.getMessage());
            }
        }
    public static Connection getConnection(){
        return connection;
    }
    
}
