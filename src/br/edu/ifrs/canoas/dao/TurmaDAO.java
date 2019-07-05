/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifrs.canoas.dao;

import br.edu.ifrs.canoas.modelo.Disciplina;
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
public class TurmaDAO extends AbstractDAO<Turma>{
    
    private static ProfessorDAO pDAO = new ProfessorDAO();
    private static DisciplinaDAO dDAO = new DisciplinaDAO();
    
    @Override
    public Turma getOne(Long id) throws SQLException{
        Conexao c = new Conexao();
        Connection con = c.getConexao();
        Turma t = new Turma();
        String sql = "SELECT IDTURMA,t.NOME turma, MATRICULAPROFESSOR,t.IDDISCIPLINA id, d.NOME disciplina, DESCRICAO"
                + " FROM FacTurma t inner join FACDISCIPLINA d on d.IDDISCIPLINA = t.IDDISCIPLINA WHERE IDTURMA = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                t.setIdTurma(rs.getInt("idTurma"));
                t.setNome(rs.getString("turma"));
                t.setProfessor(pDAO.getOne(rs.getLong("matriculaProfessor")));
                Disciplina d = new Disciplina();
                d.setIdDisciplina(rs.getLong("id"));
                d.setNome(rs.getString("disciplina"));
                d.setDescricao(rs.getString("descricao"));
                d.mostrar(1);
                t.setDisciplina(d);
            }
        } catch (SQLException e) {
            throw new SQLException("Erro ao buscar turma expecifica! \n"+e.getMessage());
        }
        return t;
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
