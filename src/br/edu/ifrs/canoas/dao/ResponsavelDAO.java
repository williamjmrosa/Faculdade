/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifrs.canoas.dao;

import br.edu.ifrs.canoas.controle.Logado;
import br.edu.ifrs.canoas.modelo.Responsavel;
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
 * @author William
 */
public class ResponsavelDAO extends AbstractDAO<Responsavel> {

    private static TelefoneDAO tDAO = new TelefoneDAO();

    @Override
    public Responsavel getOne(Long id) throws SQLException{
        Responsavel r = new Responsavel();
        Conexao c = new Conexao();
        Connection con = c.getConexao();
        String sql = "SELECT * FROM FacResponsavel WHERE idResponsavel = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                
                r.setIdResponsavel(rs.getLong("idResponsavel"));
                r.setNome(rs.getString("nome"));
                r.setRg(rs.getLong("rg"));
                r.setCpf(rs.getString("cpf"));
                r.setEmail(rs.getString("email"));
                r.setSenha(rs.getString("senha"));
                r.setAcesso(rs.getInt("acesso"));
                
                Telefone t = tDAO.getOne(rs.getLong("idTelefone"));
                r.setTelefone(t);
                
            }
        } catch (SQLException e) {
            
            throw new SQLException("Erro ao buscar Responsavel expecifico! \n"+e.getMessage());
        }finally{
            c.desconecta();
            con.close();
        }
        return r;
    }
    
    public Responsavel getOne(String cpf) throws SQLException {
        Responsavel r = new Responsavel();
        Conexao c = new Conexao();
        Connection con = c.getConexao();
        String sql = "SELECT * FROM FacResponsavel WHERE cpf = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, cpf);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                
                r.setIdResponsavel(rs.getLong("idResponsavel"));
                r.setNome(rs.getString("nome"));
                r.setRg(rs.getLong("rg"));
                r.setCpf(rs.getString("cpf"));
                r.setEmail(rs.getString("email"));
                r.setSenha(rs.getString("senha"));
                r.setAcesso(rs.getInt("acesso"));
                
                Telefone t = tDAO.getOne(rs.getLong("idTelefone"));
                r.setTelefone(t);
                
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar responsaveis");
            e.printStackTrace();
            return null;
        }finally{
            c.desconecta();
            con.close();
        }
        return r;
    }

    @Override
    public Long insert(Responsavel o) throws SQLException {
        Conexao c = new Conexao();
        Connection con = c.getConexao();
        String sql = "INSERT INTO FacResponsavel(idResponsavel,nome,rg,cpf,idTelefone,email,senha,acesso) "
                + "VALUES(facIdResponsavel.nextval,?,?,?,?,?,?,?)";
        try {
            Telefone t = o.getTelefone();
            t.setIdTelefone(tDAO.insert(t));
            o.setTelefone(t);

            if (o.getTelefone().getIdTelefone() != -1) {
                String generatedColumns[] = {"idResponsavel"};
                
                PreparedStatement ps = con.prepareStatement(sql,generatedColumns);
                ps.setString(1, o.getNome());
                ps.setLong(2, o.getRg());
                ps.setString(3, o.getCpf());
                ps.setLong(4, o.getTelefone().getIdTelefone());
                ps.setString(5, o.getEmail());
                ps.setString(6, o.getSenha());
                ps.setInt(7, o.getAcesso());
                ps.executeUpdate();
                
                ResultSet rs = ps.getGeneratedKeys();
                if(rs.next()){
                    o.setIdResponsavel(rs.getLong(1));
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao Cadastrar Responsavel");
            e.printStackTrace();
            return -1L;
        }finally{
            c.desconecta();
            con.close();
        }
        return o.getIdResponsavel();
    }

    @Override
    public boolean delete(Responsavel o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(Responsavel o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Responsavel> buscar(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Responsavel> filtrar(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Responsavel> filtrar(String texto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public static ArrayList<Responsavel> getAll() throws SQLException{
        ArrayList<Responsavel> responsaveis = new ArrayList<>();
        Conexao c = new Conexao();
        Connection con = c.getConexao();
        String sql = "SELECT * FROM FacResponsavel";
        TelefoneDAO tDAO = new TelefoneDAO();
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                Responsavel r = new Responsavel();
                r.setIdResponsavel(rs.getLong("idResponsavel"));
                r.setNome(rs.getString("nome"));
                r.setRg(rs.getLong("rg"));
                r.setCpf(rs.getString("cpf"));
                r.setEmail(rs.getString("email"));
                r.setSenha(rs.getString("senha"));
                r.setAcesso(rs.getInt("acesso"));
                
                Telefone t = tDAO.getOne(r.getTelefone().getIdTelefone());
                r.setTelefone(t);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar responsaveis");
            e.printStackTrace();
        }finally{
            c.desconecta();
            con.close();
        }
        return responsaveis;
    }

    public static boolean login(String cpf, String senha) throws SQLException{
        Conexao c = new Conexao();
        Connection con = c.getConexao();
        String sql = "SELECT * FROM FacResponsavel where cpf = ? and senha = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, cpf);
            ps.setString(2, senha);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                Responsavel r = new Responsavel();
                r.setIdResponsavel(rs.getLong("idResponsavel"));
                r.setNome(rs.getString("nome"));
                r.setRg(rs.getLong("rg"));
                r.setCpf(rs.getString("cpf"));
                r.setEmail(rs.getString("email"));
                r.setSenha(rs.getString("senha"));
                r.setAcesso(rs.getInt("acesso"));
                Telefone t = tDAO.getOne(rs.getLong("idTelefone"));
                r.setTelefone(t);
                Logado.setResponsavel(r);
                return true;
            }
        } catch (SQLException e) {
            throw new SQLException("Erro no login: "+e.getMessage());
            
        }
        return false;
        
    }
    
}
