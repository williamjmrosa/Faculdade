/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifrs.canoas.dao;

import br.edu.ifrs.canoas.modelo.Curso;
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
public class CursoDAO extends AbstractDAO<Curso>{

    @Override
    public Curso getOne() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Long insert(Curso o) {
        
        Conexao c = new Conexao();
        Connection con = c.getConexao();
        
        String sql = "INSERT INTO FacCurso(idCurso,nome,descricao)"
                + "VALUES(facIdCurso.nextval,?,?)";
        
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, o.getNome());
            ps.setString(2, o.getDescricao());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Deu erro Curso");
            e.printStackTrace();
            return -1L;
        }
        
        return 1L;
    }

    @Override
    public boolean delete(Curso o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(Curso o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Curso> buscar(Long id) {
       throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Curso login(String usuario, String senha) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Curso logado(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Curso> filtrar(Long id) {
        ArrayList<Curso> cursos = new ArrayList<>();
        
        Conexao conexao = new Conexao();
        Connection con = conexao.getConexao();
        
        String sql = "SELECT * FROM FACCURSO WHERE IDCURSO = ?";
        
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                Curso c = new Curso();
                c.setIdCurso(rs.getLong("idCurso"));
                c.setNome(rs.getString("nome"));
                c.setDescricao(rs.getString("descricao"));
                cursos.add(c);
            }
            
        } catch (SQLException e) {
            
            System.out.println("Erro ao filtrar Curso");
            e.printStackTrace();
            return null;
        }
        return cursos;
    }

    @Override
    public ArrayList<Curso> filtrar(String texto) {
        ArrayList<Curso> cursos = new ArrayList<>();
        
        Conexao conexao = new Conexao();
        Connection con = conexao.getConexao();
        
        String sql = "SELECT * FROM FACCURSO WHERE NOME like ?";
        
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, "%"+texto+"%");
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                Curso c = new Curso();
                c.setIdCurso(rs.getLong("idCurso"));
                c.setNome(rs.getString("nome"));
                c.setDescricao(rs.getString("descricao"));
                cursos.add(c);
            }
            
        } catch (SQLException e) {
            
            System.out.println("Erro ao filtrar Curso");
            e.printStackTrace();
            return null;
        }
        return cursos;
    }
    
    public static ArrayList<Curso> getALL(){
        ArrayList<Curso> cursos = new ArrayList<>();
        
        Conexao conexao = new Conexao();
        Connection con = conexao.getConexao();
        
        String sql = "SELECT * FROM FACCURSO";
        
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            
            while(rs.next()){
                Curso c = new Curso();
                c.setIdCurso(rs.getLong("idCurso"));
                c.setNome(rs.getString("nome"));
                c.setDescricao(rs.getString("descricao"));
                cursos.add(c);
            }
            
        } catch (SQLException e) {
            
            System.out.println("Erro ao filtrar Curso");
            e.printStackTrace();
            return null;
        }
        return cursos;
    }
    
    
}
