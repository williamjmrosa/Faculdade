/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifrs.canoas.dao;

import br.edu.ifrs.canoas.controle.Logado;
import br.edu.ifrs.canoas.modelo.Endereco;
import br.edu.ifrs.canoas.modelo.Funcionario;
import br.edu.ifrs.canoas.modelo.Telefone;
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
public class FuncionarioDAO extends AbstractDAO<Funcionario> {

    private static EnderecoDAO eDAO = new EnderecoDAO();
    private static TelefoneDAO tDAO = new TelefoneDAO();

    @Override
    public Funcionario getOne(Long id) throws SQLException{
        Conexao c = new Conexao();
        Connection con = c.getConexao();
        Funcionario f = new Funcionario();
        String sql = "SELECT * FROM FacFuncionario WHERE matricula = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                f.setMatricula(rs.getLong("matricula"));
                f.setNome(rs.getString("nome"));
                f.setRg(rs.getLong("rg"));
                f.setCpf(rs.getString("cpf"));
                Endereco e = eDAO.getOne(rs.getLong("idEndereco"));
                f.setEndereco(e);
                Telefone t = tDAO.getOne(rs.getLong("idTelefone"));
                f.setTelefone(t);
                f.setEmail(rs.getString("email"));
                f.setSenha(rs.getString("senha"));
                f.setAcesso(rs.getInt("acesso"));
            }
        } catch (SQLException e) {
            throw new SQLException("Erro no busca de funcionario expecifico! \n"+e.getMessage());
        }
        return f;
    }

    @Override
    public Long insert(Funcionario o) throws SQLException {
        Conexao c = new Conexao();
        Connection con = c.getConexao();
        String sql = "INSERT INTO FacFuncionario(matricula,nome,rg,cpf,idEndereco,idTelefone,email,senha,acesso)"
                + "VALUES(?,?,?,?,?,?,?,?,?)";

        try {
            Endereco e = o.getEndereco();
            e.setIdEndereco(eDAO.insert(e));
            Telefone t = o.getTelefone();
            t.setIdTelefone(tDAO.insert(t));
            o.setEndereco(e);
            o.setTelefone(t);
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setLong(1, o.getMatricula());
            ps.setString(2, o.getNome());
            ps.setLong(3, o.getRg());
            ps.setString(4, o.getCpf());
            ps.setLong(5, o.getEndereco().getIdEndereco());
            ps.setLong(6, o.getTelefone().getIdTelefone());
            ps.setString(7, o.getEmail());
            ps.setString(8, o.getSenha());
            ps.setInt(9, o.getAcesso());
            ps.executeUpdate();

        } catch (SQLException e) {
            o.setMatricula(-1L);
            throw new SQLException("Erro ao cadastrar Funcionario! " + e.getMessage());

        }
        return o.getMatricula();

    }

    @Override
    public boolean delete(Funcionario o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(Funcionario o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Funcionario> buscar(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Funcionario> filtrar(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Funcionario> filtrar(String texto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public static boolean login(Long matricula, String senha) throws SQLException {
        Conexao c = new Conexao();
        Connection con = c.getConexao();
        String sql = "SELECT * FROM FacFuncionario where matricula = ? and senha = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setLong(1, matricula);
            ps.setString(2, senha);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Funcionario f = new Funcionario();
                f.setMatricula(rs.getLong("matricula"));
                f.setNome(rs.getString("nome"));
                f.setRg(rs.getLong("rg"));
                f.setCpf(rs.getString("cpf"));
                Endereco e = eDAO.getOne(rs.getLong("idEndereco"));
                f.setEndereco(e);
                Telefone t = tDAO.getOne(rs.getLong("idTelefone"));
                f.setTelefone(t);
                f.setEmail(rs.getString("email"));
                f.setSenha(rs.getString("senha"));
                f.setAcesso(rs.getInt("acesso"));
                f.mostrar(0);
                Logado.setPessoa(f);
                return true;
            }
        } catch (SQLException e) {
            throw new SQLException("Erro no login: " + e.getMessage());

        }
        return false;

    }

}
