/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifrs.canoas.dao;

import br.edu.ifrs.canoas.modelo.Endereco;
import br.edu.ifrs.canoas.modelo.Professor;
import br.edu.ifrs.canoas.modelo.Telefone;
import br.edu.ifrs.canoas.persistencia.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author William José
 */
public class ProfessorDAO extends AbstractDAO<Professor> {
    private TelefoneDAO tDAO = new TelefoneDAO();
    private EnderecoDAO eDAO = new EnderecoDAO();
    
    @Override
    public Professor getOne() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Long insert(Professor o) {
        Conexao c = new Conexao();
        Connection con = c.getConexao();
        
        String sql = "INSERT INTO FacProfessor(matricula,nome,rg,cpf,idEndereco,idTelefone,formacao,email,senha,acesso)"
                +"VALUES(?,?,?,?,?,?,?,?,?,?)";
        
        try {
            Endereco e = o.getEndereco();
            e.setIdEndereco(eDAO.insert(o.getEndereco()));
            o.setEndereco(e);
            Telefone t = o.getTelefone();
            t.setIdTelefone(tDAO.insert(o.getTelefone()));
            o.setTelefone(t);
            
            if(o.getEndereco().getIdEndereco() != -1  && o.getTelefone().getIdTelefone() != -1 ){
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setLong(1, o.getMatricula());
                ps.setString(2, o.getNome());
                ps.setLong(3, o.getRg());
                ps.setString(4, o.getCpf());
                ps.setLong(5, o.getEndereco().getIdEndereco());
                ps.setLong(6, o.getTelefone().getIdTelefone());
                ps.setString(7, o.getFormacao());
                ps.setString(8, o.getEmail());
                ps.setString(9, o.getSenha());
                ps.setInt(10, o.getAcesso());
                
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println("Deu problema Professor");
            e.printStackTrace();
            return -1L;
        }
        return o.getMatricula();
    }

    @Override
    public boolean delete(Professor o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(Professor o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Professor> buscar(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Professor login(String usuario, String senha) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Professor logado(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
