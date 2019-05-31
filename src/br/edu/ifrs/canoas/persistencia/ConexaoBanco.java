/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifrs.canoas.persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author william
 */
public class ConexaoBanco {
    
    private static final String URL = "jdbc:mysql://localhost:3306/escola";
    private static final String USUARIO = "root";
    private static final String SENHA = "12345";
    
    public static Connection getConexao() throws SQLException{
        
        Connection c = null;
        
        try {
            c = DriverManager.getConnection(URL, USUARIO, SENHA);
        } catch (SQLException se) {
            
            throw new SQLException("Erro ao conectar no Banco de Dados! " + se.getMessage());
            
        }
        
        return c;
        
    }
    
}
