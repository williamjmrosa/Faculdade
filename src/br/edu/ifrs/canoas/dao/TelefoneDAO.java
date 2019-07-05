/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifrs.canoas.dao;

import br.edu.ifrs.canoas.modelo.Telefone;
import br.edu.ifrs.canoas.persistencia.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author William
 */
public class TelefoneDAO extends AbstractDAO<Telefone>{

    @Override
    public Telefone getOne(Long id) throws SQLException {
        Telefone t = new Telefone();
        Conexao c = new Conexao();
        Connection con = c.getConexao();
        String sql = "SELECT * FROM FacTelefone WHERE idTelefone = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                t.setIdTelefone(rs.getLong("idTelefone"));
                t.setNumero(rs.getString("numero"));
                t.setTipo(rs.getString("tipo"));
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar teleone expecifico");
            e.printStackTrace();
            return null;
        }finally{
            c.desconecta();
            con.close();
        }
        return t;
    }

    @Override
    public Long insert(Telefone o) throws SQLException {
        Conexao c = new Conexao();
        Connection con = c.getConexao();
        String sql = "INSERT INTO FacTelefone(idTelefone,tipo,numero) VALUES(facIdTelefone.nextval,?,?)";
        try {
            String generatedColumns[] = {"idTelefone"};
            
            PreparedStatement ps = con.prepareStatement(sql,generatedColumns);
            //ps.setLong(1, idTelefone);
            ps.setString(1, o.getTipo());
            ps.setString(2, o.getNumero());
            ps.executeUpdate();
            //ps.executeUpdate(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            ResultSet rs = ps.getGeneratedKeys();
            if(rs.next()){
                o.setIdTelefone(rs.getLong(1));
            }
        } catch (SQLException e) {
            System.out.println("Deu problema no telefone!");
            e.printStackTrace();
            return -1L;
        }finally{
            c.desconecta();
            con.close();
        }
        return o.getIdTelefone();
    }

    @Override
    public boolean delete(Telefone o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(Telefone o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Telefone> buscar(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Telefone> filtrar(String texto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Telefone> filtrar(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
