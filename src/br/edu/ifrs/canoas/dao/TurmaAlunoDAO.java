/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifrs.canoas.dao;

import br.edu.ifrs.canoas.modelo.Aluno;
import br.edu.ifrs.canoas.modelo.Turma;
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
public class TurmaAlunoDAO{

    private static TurmaDAO tDAO = new TurmaDAO();
    
    public Turma getOne(Long id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Long insert(Turma t,Aluno a) throws SQLException {
        Conexao c = new Conexao();
        Connection con = c.getConexao();
        String sql = "INSERT INTO FacTurmaAluno(matricula,idTurma) VALUES(?,?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setLong(1, a.getMatricula());
            ps.setLong(2,t.getIdTurma());
            ps.executeUpdate();
            return a.getMatricula();
            
        } catch (SQLException e) {
            throw new SQLException("Erro ao Matricular "+a.getNome()+" na Turma "+t.getNome()+"!"+e.getMessage());
        }
    }

    public boolean delete(Turma o) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean update(Turma o) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public ArrayList<Turma> buscar(Long id) throws SQLException {
        Conexao c = new Conexao();
        Connection con = c.getConexao();
        ArrayList<Turma> turmas = new ArrayList<>();
        String sql = "SELECT t.IDTURMA id FROM FACTURMA t INNER JOIN FACCURSODISCIPLINA cd ON cd.IDDISCIPLINA = t.IDDISCIPLINA \n" +
        "JOIN FACDISCIPLINA d on d.IDDISCIPLINA = cd.IDDISCIPLINA WHERE cd.IDCURSO = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Turma t = tDAO.getOne(rs.getLong("id"));
                turmas.add(t);
                
            }
        } catch (SQLException e) {
            throw new SQLException("Erro ao buscar turmas de um curso! "+e.getMessage());
        }
        return turmas;
    }

    public ArrayList<Turma> filtrar(Long id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public ArrayList<Turma> filtrar(String texto) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
