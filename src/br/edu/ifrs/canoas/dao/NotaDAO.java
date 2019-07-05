/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifrs.canoas.dao;

import br.edu.ifrs.canoas.modelo.Aluno;
import br.edu.ifrs.canoas.modelo.Nota;
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
public class NotaDAO{

    private AlunoDAO aDAO = new AlunoDAO();
    private TurmaDAO tDAO = new TurmaDAO();
    
    public Nota getOne(Long id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Long insert(Nota o) throws SQLException {
        Conexao c = new Conexao();
        Connection con = c.getConexao();
        String sql = "INSERT INTO FacNota(idNota,nota1,nota2,nota3,matriculaAluno,idTurma)"
                + "VALUES(facIdNota.nextval,?,?,?,?,?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setDouble(1, o.getNota1());
            ps.setDouble(2, o.getNota2());
            ps.setDouble(3, o.getNota3());
            ps.setLong(4, o.getAluno().getMatricula());
            ps.setLong(5, o.getTurma().getIdTurma());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Erro ao dadastrar nota! \n"+e.getMessage());
        }
        return 0L;
    }

    public boolean delete(Nota o) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean update(Nota o) throws SQLException {
        Conexao c = new Conexao();
        Connection con = c.getConexao();
        String sql = "UPDATE FacNota SET nota1 = ?, nota2 = ?, nota3 = ? where idNota = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setLong(1, o.getIdNota());
            ps.setDouble(2, o.getNota1());
            ps.setDouble(3, o.getNota2());
            ps.setDouble(4, o.getNota3());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new SQLException("Erro ao atulizar nota! \n"+e.getMessage());
        }finally{
            c.desconecta();
            con.close();
        }
        
    }

    public ArrayList<Nota> buscar(Long id) throws SQLException {
        Conexao c = new Conexao();
        Connection con = c.getConexao();
        ArrayList<Nota> notas = new ArrayList<>();
        
        String sql = "SELECT * FROM FacNota n INNER JOIN FacAluno a on a.matricula = n.matriculaAluno WHERE idTurma = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Nota n = new Nota();
                n.setIdNota(rs.getInt("idNota"));
                n.setNota1(rs.getDouble("nota1"));
                n.setNota2(rs.getDouble("nota2"));
                n.setNota3(rs.getDouble("nota3"));
                n.setAluno(aDAO.getOne(rs.getLong("matriculaAluno")));
                n.setTurma(tDAO.getOne(rs.getLong("idTurma")));
                notas.add(n);
            }
        } catch (SQLException e) {
            throw new SQLException("Erro buscar! \n"+e.getMessage());
        }finally{
            c.desconecta();
            con.close();
        }
        return notas;
    }

    public ArrayList<Nota> filtrar(Long id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public ArrayList<Nota> filtrar(String texto) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
