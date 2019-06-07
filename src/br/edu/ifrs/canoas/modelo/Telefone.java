/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifrs.canoas.modelo;

import br.edu.ifrs.canoas.persistencia.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author William José
 */
public class Telefone {
    private Long idTelefone;
    private String tipo;
    private String numero;

    public Telefone() {
    }

    public Telefone(Long idTelefone, String tipo, String numero) {
        this.idTelefone = idTelefone;
        this.tipo = tipo;
        this.numero = numero;
    }

    public Long getIdTelefone() {
        return idTelefone;
    }

    public void setIdTelefone(Long idTelefone) {
        this.idTelefone = idTelefone;
    }
    
    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public boolean insert(){
        Conexao c = new Conexao();
        Connection con = c.getConexao();
        String sql = "INSERT INTO FacTelefone(idTelefone,tipo,numero) VALUES(facIdTelefone.nextval,?,?)";
        try {
            String generatedColumns[] = {"idTelefone"};
            
            PreparedStatement ps = con.prepareStatement(sql,generatedColumns);
            //ps.setLong(1, idTelefone);
            ps.setString(1, tipo);
            ps.setString(2, numero);
            ps.executeUpdate();
            //ps.executeUpdate(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            ResultSet rs = ps.getGeneratedKeys();
            if(rs.next()){
                idTelefone = rs.getLong(1);
            }
        } catch (SQLException e) {
            System.out.println("Deu problema no telefone!");
            e.printStackTrace();
            return false;
        }
        return true;
    }
    
    @Override
    public String toString() {
        return  "\nID Telefone: "+idTelefone+
                "\nTipo: "+tipo+
                "\nNumero: "+numero;
    }
    
}
