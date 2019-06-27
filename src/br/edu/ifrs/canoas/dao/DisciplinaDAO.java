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
    public Disciplina getOne() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Long insert(Disciplina o) {
        Conexao c = new Conexao();
        Connection con = c.getConexao();
        
        String sql = "INSERT INTO FacDisciplina(idDisciplina,nome,descricao)"
                + "VALUES(facIdDisciplina.nextval,?,?)";
        
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            
            ps.setString(1, o.getNome());
            ps.setString(2, o.getDescricao());
            
            ps.executeUpdate(); 
            
        } catch (SQLException e) {
            System.out.println("Erro ao Cadastrar Curso");
            e.printStackTrace();
            return -1L;
        }
        
        return 1L;
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
    public Disciplina login(String usuario, String senha) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Disciplina logado(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Disciplina> filtrar(Long id) {
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
                disciplinas.add(d);
                
            }
            
        } catch (SQLException e) {
            System.out.println("Erro ao filtrar");
            return null;
        }
        return disciplinas;
    }

    @Override
    public ArrayList<Disciplina> filtrar(String texto) {
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
                disciplinas.add(d);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao filtrar");
            e.printStackTrace();
            return null;
        }
        return disciplinas;
    }
    
    public static ArrayList<Disciplina> getALL(){
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
                disciplinas.add(d);
            }
            
        } catch (SQLException e) {
            System.out.println("Erro ao Listar disciplinas");
            e.printStackTrace();
            return null;
        }
        return disciplinas;
    }
    
}
