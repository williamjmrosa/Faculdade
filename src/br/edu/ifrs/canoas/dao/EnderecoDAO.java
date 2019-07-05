/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifrs.canoas.dao;

import br.edu.ifrs.canoas.modelo.Endereco;
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
public class EnderecoDAO extends AbstractDAO<Endereco> {

    @Override
    public Endereco getOne(Long id) throws SQLException {
        Conexao c = new Conexao();
        Connection con = c.getConexao();
        
        String sql = "SELECT * FROM FacEndereco where idEndereco = ?";
        try {
            Endereco e = new Endereco();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                
                e.setIdEndereco(rs.getLong("idEndereco"));
                e.setCep(rs.getString("cep"));
                e.setBairro(rs.getString("bairro"));
                e.setCidade(rs.getString("cidade"));
                e.setEstado(rs.getString("estado"));
                e.setNumero(rs.getInt("numero"));
                e.setRua(rs.getString("rua"));
            }
            return e;
        } catch (SQLException e) {
            throw new SQLException("Erro ao buscar endereço expecifico! "+e.getMessage());
            //System.out.println("Erro ao buscar endereço expecifico");
            //e.printStackTrace();
            //return null;
        }finally{
            c.desconecta();
            con.close();
        }
         
    }

    @Override
    public Long insert(Endereco o) throws SQLException {
        Conexao c = new Conexao();
        Connection con = c.getConexao();
        String sql = "INSERT INTO FacEndereco(idEndereco,rua,cidade,bairro,estado,numero,cep)"
                +"VALUES(facIdEndereco.nextval,?,?,?,?,?,?)";
        try {
            String generatedColumns[] = {"idEndereco"};
            
            PreparedStatement ps = con.prepareStatement(sql,generatedColumns);
            //ps.setLong(1, idEndereco);
            ps.setString(1, o.getRua());
            ps.setString(2, o.getCidade());
            ps.setString(3, o.getBairro());
            ps.setString(4, o.getEstado());
            ps.setInt(5, o.getNumero());
            ps.setString(6, o.getCep());
            ps.executeUpdate();
            //ps.executeUpdate(sql,PreparedStatement.RETURN_GENERATED_KEYS);
            ResultSet rs = ps.getGeneratedKeys();
            if(rs.next()){
                o.setIdEndereco(rs.getLong(1));
            }
            return o.getIdEndereco();
        } catch (SQLException e) {
            throw new SQLException("Deu erro no endereço! "+e.getMessage());
            //System.out.println("Deu erro no endereço");
            //e.printStackTrace();
            //return -1L;
        }finally{
            c.desconecta();
            con.close();
        }
        
    }

    @Override
    public boolean delete(Endereco o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(Endereco o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Endereco> buscar(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Endereco> filtrar(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Endereco> filtrar(String texto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
