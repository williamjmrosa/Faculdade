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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author William José
 */
public class ProfessorDAO extends AbstractDAO<Professor> {
    private static TelefoneDAO tDAO = new TelefoneDAO();
    private static EnderecoDAO eDAO = new EnderecoDAO();
    
    @Override
    public Professor getOne(Long id) {
        Conexao c = new Conexao();
        Connection con = c.getConexao();
        Professor p = new Professor();
        String sql = "SELECT * FROM FacProfessor WHERE matricula = ?";
        
        try {
            
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                
                p.setMatricula(rs.getLong("matricula"));
                p.setNome(rs.getString("nome"));
                p.setRg(rs.getLong("rg"));
                p.setCpf(rs.getString("cpf"));
                p.setEndereco(eDAO.getOne(rs.getLong("idEndereco")));
                p.setTelefone(tDAO.getOne(rs.getLong("idTelefone")));
                p.setFormacao(rs.getString("formacao"));
                p.setEmail(rs.getString("email"));
                p.setSenha(rs.getString("senha"));
                p.setAcesso(rs.getInt("acesso"));
                p.mostrar(1);
                
            }
        } catch (SQLException e) {
            System.out.println("Erro ao filtrar professor");
            e.printStackTrace();
            return null;
        }
        return p;
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
    public ArrayList<Professor> filtrar(Long id) {
        Conexao c = new Conexao();
        Connection con = c.getConexao();
        ArrayList<Professor> professores = new ArrayList<>();
        String sql = "SELECT * FROM FacProfessor WHERE matricula = ?";
        
        try {
            
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Professor p = new Professor();
                p.setMatricula(rs.getLong("matricula"));
                p.setNome(rs.getString("nome"));
                p.setRg(rs.getLong("rg"));
                p.setCpf(rs.getString("cpf"));
                p.setEndereco(eDAO.getOne(rs.getLong("idEndereco")));
                p.setTelefone(tDAO.getOne(rs.getLong("idTelefone")));
                p.setFormacao(rs.getString("formacao"));
                p.setEmail(rs.getString("email"));
                p.setSenha(rs.getString("senha"));
                p.setAcesso(rs.getInt("acesso"));
                p.mostrar(1);
                professores.add(p);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao filtrar professor");
            e.printStackTrace();
            return null;
        }
        return professores;
    }

    @Override
    public ArrayList<Professor> filtrar(String texto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public static ArrayList<Professor> getAll(){
        Conexao c = new Conexao();
        Connection con = c.getConexao();
        ArrayList<Professor> professores = new ArrayList<>();
        String sql = "SELECT * FROM FacProfessor";
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                Professor p = new Professor();
                p.setMatricula(rs.getLong("matricula"));
                p.setNome(rs.getString("nome"));
                p.setRg(rs.getLong("rg"));
                p.setCpf(rs.getString("cpf"));
                Endereco e = eDAO.getOne(rs.getLong("idEndereco"));
                p.setEndereco(e);
                Telefone t = tDAO.getOne(rs.getLong("idTelefone"));
                p.setTelefone(t);
                //p.setEndereco(eDAO.getOne(rs.getLong("idEndereco")));
               // p.setTelefone(tDAO.getOne(rs.getLong("idTelefone")));
                p.setFormacao(rs.getString("formacao"));
                p.setEmail(rs.getString("email"));
                p.setSenha(rs.getString("senha"));
                p.setAcesso(rs.getInt("acesso"));
                p.mostrar(1);
                professores.add(p);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao mostrar todos os professores");
            e.printStackTrace();
            return null;
        }
        return professores;
    }
    
}
