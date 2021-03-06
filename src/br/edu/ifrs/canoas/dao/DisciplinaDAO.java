/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifrs.canoas.dao;

import br.edu.ifrs.canoas.modelo.Disciplina;
import br.edu.ifrs.canoas.persistencia.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author William
 */
public class DisciplinaDAO extends AbstractDAO<Disciplina> {

    @Override
    public Disciplina getOne(Long id) throws SQLException{
        Conexao c = new Conexao();
        Connection con = c.getConexao();
        Disciplina d = new Disciplina();
        String sql = "SELECT * FROM FacDisciplina WHERE idDisciplina = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                d.setIdDisciplina(rs.getLong("idDisciplina"));
                d.setNome(rs.getString("nome"));
                d.setDescricao(rs.getString("descricao"));
            }
            return d;
        } catch (SQLException e) {
            throw new SQLException("Erro no buscar disciplina expecifica! \n"+e.getMessage());
        }finally{
            c.desconecta();
            con.close();
        }
        
    }

    @Override
    public Long insert(Disciplina o) throws SQLException{
        Conexao c = new Conexao();
        Connection con = c.getConexao();
        
        String sql = "INSERT INTO FacDisciplina(idDisciplina,nome,descricao)"
                + "VALUES(facIdDisciplina.nextval,?,?)";
        
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            
            ps.setString(1, o.getNome());
            ps.setString(2, o.getDescricao());
            
            ps.executeUpdate(); 
            return 1L;
        } catch (SQLException e) {
            throw new SQLException("Erro ao Cadastrar Curso! "+e.getMessage());
            //System.out.println("Erro ao Cadastrar Curso");
            //e.printStackTrace();
            //return -1L;
        }finally{
            c.desconecta();
            con.close();
        }
        
        
    }

    @Override
    public boolean delete(Disciplina o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(Disciplina o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Disciplina> buscar(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Disciplina> filtrar(Long id) throws SQLException{
        Conexao c = new Conexao();
        Connection con = c.getConexao();
        ArrayList<Disciplina> disciplinas = new ArrayList<>();
        
        String sql = "SELECT * FROM FACDISCIPLINA WHERE IDDISCIPLINA = ? ORDER BY IDDISCIPLINA";
        
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setLong(1, id);
            
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Disciplina d = new Disciplina();
                d.setIdDisciplina(rs.getLong("idDisciplina"));
                d.setNome(rs.getString("nome"));
                d.setDescricao(rs.getString("descricao"));
                d.mostrar(1);
                disciplinas.add(d);
                
            }
            return disciplinas;
        } catch (SQLException e) {
            throw new SQLException("Erro ao filtrar! "+e.getMessage());
            //System.out.println("Erro ao filtrar");
            //return null;
        }finally{
            c.desconecta();
            con.close();
        }
        
    }

    @Override
    public ArrayList<Disciplina> filtrar(String texto) throws SQLException{
        Conexao c = new Conexao();
        Connection con = c.getConexao();
        ArrayList<Disciplina> disciplinas = new ArrayList<>();
        String sql = "SELECT * FROM FACDISCIPLINA WHERE NOME LIKE ? ORDER BY IDDISCIPLINA";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, texto+"%");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Disciplina d = new Disciplina();
                d.setIdDisciplina(rs.getLong("idDisciplina"));
                d.setNome(rs.getString("nome"));
                d.setDescricao(rs.getString("descricao"));
                d.mostrar(1);
                disciplinas.add(d);
            }
            return disciplinas;
        } catch (SQLException e) {
            throw new SQLException("Erro ao filtrar! "+e.getMessage());
            //System.out.println("Erro ao filtrar");
            //e.printStackTrace();
            //return null;
        }finally{
            c.desconecta();
            con.close();
        }
        
    }
    
    public static ArrayList<Disciplina> getALL() throws SQLException{
        Conexao c = new Conexao();
        Connection con = c.getConexao();
        ArrayList<Disciplina> disciplinas = new ArrayList<>();
        
        String sql = "SELECT * FROM FACDISCIPLINA ORDER BY IDDISCIPLINA";
        
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                Disciplina d = new Disciplina();
                d.setIdDisciplina(rs.getLong("idDisciplina"));
                d.setNome(rs.getString("nome"));
                d.setDescricao(rs.getString("descricao"));
                d.mostrar(1);
                disciplinas.add(d);
                
            }
            return disciplinas;
            
        } catch (SQLException e) {
            throw new SQLException("Erro ao Listar disciplinas! "+e.getMessage());
            //System.out.println("Erro ao Listar disciplinas");
            //e.printStackTrace();
            //return null;
        }finally{
            c.desconecta();
            con.close();
        }
        
    }
    
}
