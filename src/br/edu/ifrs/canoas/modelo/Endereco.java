/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifrs.canoas.modelo;

import br.edu.ifrs.canoas.persistencia.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author William José
 */
public class Endereco {
    private Long idEndereco;
    private String rua;
    private String cidade;
    private String bairro;
    private String estado;
    private int numero;
    private String cep;

    public Endereco() {
    }

    public Endereco(Long idEndereco, String rua, String cidade, String bairro, String estado, int numero, String cep) {
        this.idEndereco = idEndereco;
        this.rua = rua;
        this.cidade = cidade;
        this.bairro = bairro;
        this.estado = estado;
        this.numero = numero;
        this.cep = cep;
    }

    public Long getIdEndereco() {
        return idEndereco;
    }

    public void setIdEndereco(Long idEndereco) {
        this.idEndereco = idEndereco;
    }
    
    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public boolean insert(){
        Conexao c = new Conexao();
        Connection con = c.getConexao();
        String sql = "INSERT INTO FacEndereco(idEndereco,rua,cidade,bairro,estado,numero,cep)"
                +"VALUES(?,?,?,?,?,?,?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setLong(1, idEndereco);
            ps.setString(2, rua);
            ps.setString(3, cidade);
            ps.setString(4, bairro);
            ps.setString(5, estado);
            ps.setInt(6, numero);
            ps.setString(7, cep);
            ps.executeUpdate();
            /*ps.executeUpdate(sql,PreparedStatement.RETURN_GENERATED_KEYS);
            ResultSet rs = ps.getGeneratedKeys();
            if(rs != null && rs.next()){
                idEndereco = rs.getLong(1);
            }*/
            
        } catch (SQLException e) {
            System.out.println("Deu erro no endereço");
            e.printStackTrace();
            return false;
        }
        return true;
    }
    
    @Override
    public String toString() {
        return  "\nID: "+idEndereco+
                "\nCEP: "+cep+
                "\nEstado: "+estado+
                "\nCidade: "+cidade+
                "\nBairro: "+bairro+
                "\nRua: "+rua;
    }
    
    
}