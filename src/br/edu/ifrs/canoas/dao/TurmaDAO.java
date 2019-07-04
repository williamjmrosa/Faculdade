/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifrs.canoas.dao;

import br.edu.ifrs.canoas.modelo.Turma;
import br.edu.ifrs.canoas.persistencia.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author William
 */
public class TurmaDAO extends AbstractDAO<Turma>{

    @Override
    public Turma getOne(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Long insert(Turma o) {
        Conexao c = new Conexao();
        Connection con = c.getConexao();
        String sql = "INSERT INTO FacTurma(idTurma,nome,matriculaProfessor,idDisciplina)"
                + "VALUES(facIdTurma.nextval,?,?,?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, o.getNome());
            ps.setLong(2, o.getProfessor().getMatricula());
            ps.setLong(3, o.getDisciplina().getIdDisciplina());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar Turma");
            e.printStackTrace();
            return -1L;
        }
        return 0L;
        
    }

    @Override
    public boolean delete(Turma o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(Turma o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Turma> buscar(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Turma> filtrar(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Turma> filtrar(String texto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
