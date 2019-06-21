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
import java.sql.SQLException;
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
            
            ps.setString(1, "nome");
            ps.setString(2, "descricao");
            
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
    
}
