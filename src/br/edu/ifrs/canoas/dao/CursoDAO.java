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
    public Curso getOne(Long id) throws SQLException {
        Conexao conexao = new Conexao();
        Connection con = conexao.getConexao();
        Curso cu = new Curso();
        String sql = "SELECT * FROM FACCURSO WHERE IDCURSO = ?";
        
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                cu.setIdCurso(rs.getLong("idCurso"));
                cu.setNome(rs.getString("nome"));
                cu.setDescricao(rs.getString("descricao"));
                cu.mostrar(1);
            }
            return cu;
        } catch (SQLException e) {
            throw new SQLException("Erro ao buscar Curso expeficico"+e.getMessage());
            //System.out.println("Erro ao buscar Curso expecifico");
            //e.printStackTrace();
            //return null;
        }finally{
            conexao.desconecta();
            con.close();
        }
        
    }

    @Override
    public Long insert(Curso o) throws SQLException {
        
        Conexao c = new Conexao();
        Connection con = c.getConexao();
        
        String sql = "INSERT INTO FacCurso(idCurso,nome,descricao)"
                + "VALUES(facIdCurso.nextval,?,?)";
        
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, o.getNome());
            ps.setString(2, o.getDescricao());
            ps.executeUpdate();
            return 0L;
        } catch (SQLException e) {
            throw new SQLException("Erro ao cadastrar Curso! "+e.getMessage());
            //System.out.println("Deu erro Curso");
            //e.printStackTrace();
            //return -1L;
        }finally{
            c.desconecta();
            con.close();
        }
        
        
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
    public ArrayList<Curso> filtrar(Long id) throws SQLException{
        ArrayList<Curso> cursos = new ArrayList<>();
        
        Conexao conexao = new Conexao();
        Connection con = conexao.getConexao();
        
        String sql = "SELECT * FROM FACCURSO WHERE IDCURSO = ? ORDER BY IDCURSO";
        
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                Curso c = new Curso();
                c.setIdCurso(rs.getLong("idCurso"));
                c.setNome(rs.getString("nome"));
                c.setDescricao(rs.getString("descricao"));
                c.mostrar(1);
                cursos.add(c);
            }
            return cursos;
        } catch (SQLException e) {
            throw new SQLException("Erro ao filtrar Curso! "+e.getMessage());
            //System.out.println("Erro ao filtrar Curso");
            //e.printStackTrace();
            //return null;
        }finally{
            conexao.desconecta();
            con.close();
        }
        
    }

    @Override
    public ArrayList<Curso> filtrar(String texto) throws SQLException{
        ArrayList<Curso> cursos = new ArrayList<>();
        
        Conexao conexao = new Conexao();
        Connection con = conexao.getConexao();
        
        String sql = "SELECT * FROM FACCURSO WHERE NOME like ? ORDER BY IDCURSO";
        
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, texto+"%");
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                Curso c = new Curso();
                c.setIdCurso(rs.getLong("idCurso"));
                c.setNome(rs.getString("nome"));
                c.setDescricao(rs.getString("descricao"));
                c.mostrar(1);
                cursos.add(c);
            }
            return cursos;
        } catch (SQLException e) {
            throw new SQLException("Erro ao filtrar Curso! "+e.getMessage());
            //System.out.println("Erro ao filtrar Curso");
            //e.printStackTrace();
            //return null;
        }finally{
            conexao.desconecta();
            con.close();
        }
        
    }
    
    public static ArrayList<Curso> getALL() throws SQLException{
        ArrayList<Curso> cursos = new ArrayList<>();
        
        Conexao conexao = new Conexao();
        Connection con = conexao.getConexao();
        
        String sql = "SELECT * FROM FACCURSO ORDER BY IDCURSO";
        
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            
            while(rs.next()){
                Curso c = new Curso();
                c.setIdCurso(rs.getLong("idCurso"));
                c.setNome(rs.getString("nome"));
                c.setDescricao(rs.getString("descricao"));
                c.mostrar(1);
                cursos.add(c);
            }
            return cursos;
        } catch (SQLException e) {
            throw new SQLException("Erro ao filtrar Curso! "+e.getMessage());
            //System.out.println("Erro ao filtrar Curso");
            //e.printStackTrace();
            //return null;
        }finally{
            conexao.desconecta();
            con.close();
        }
        
    }
    
    
}
