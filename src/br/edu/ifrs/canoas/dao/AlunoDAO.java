/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifrs.canoas.dao;

import br.edu.ifrs.canoas.modelo.Aluno;
import br.edu.ifrs.canoas.modelo.Endereco;
import br.edu.ifrs.canoas.modelo.Telefone;
import br.edu.ifrs.canoas.persistencia.Conexao;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;

/**
 *
 * @author William
 */
public class AlunoDAO extends AbstractDAO<Aluno> {

    private EnderecoDAO eDAO = new EnderecoDAO();
    private TelefoneDAO tDAO = new TelefoneDAO();

    @Override
    public Aluno getOne(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Long insert(Aluno o) {
        Conexao c = new Conexao();
        Connection con = c.getConexao();

        String sql = "INSERT INTO FacAluno(matricula,nome,rg,cpf,dataNascimento,idEndereco,idTelefone,idCurso,idResponsavel,email,senha,acesso)"
                + "VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";

        try {
            Endereco e = o.getEndereco();
            e.setIdEndereco(eDAO.insert(e));
            o.setEndereco(e);
            Telefone t = o.getTelefone();
            t.setIdTelefone(tDAO.insert(t));
            o.setTelefone(t);

            if (t.getIdTelefone() != -1 && e.getIdEndereco() != -1) {
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setLong(1, o.getMatricula());
                ps.setString(2, o.getNome());
                ps.setLong(3, o.getRg());
                ps.setString(4, o.getCpf());
                LocalDate ld = LocalDate.of(o.getDataNascimento().getAno(), o.getDataNascimento().getMes(), o.getDataNascimento().getDia());
                ps.setDate(5, Date.valueOf(ld.toString()));
                ps.setLong(6, o.getEndereco().getIdEndereco());
                ps.setLong(7, o.getTelefone().getIdTelefone());
                ps.setLong(8, o.getCurso().getIdCurso());
                ps.setLong(9, o.getResponsavel().getIdResponsavel());
                ps.setString(10, o.getEmail());
                ps.setString(11, o.getSenha());
                ps.setInt(12, o.getAcesso());

                ps.executeUpdate();
            }else{
                return -1L;
            }
        } catch (SQLException e) {
            System.out.println("Erro no cadastro de Aluno");
            e.printStackTrace();
            return -1L;
        }

        return 0L;
    }

    @Override
    public boolean delete(Aluno o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(Aluno o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Aluno> buscar(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Aluno> filtrar(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Aluno> filtrar(String texto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
