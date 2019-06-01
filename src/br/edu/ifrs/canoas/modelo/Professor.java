/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifrs.canoas.modelo;

import br.edu.ifrs.canoas.persistencia.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author William José
 */
public class Professor extends Pessoa{
    private String formacao;
    

    public Professor() {
    }

    public Professor(String formacao, Long matricula, String nome, Long rg, String cpf, Endereco endereco, Telefone telefone, String email, String senha, int acesso) {
        super(matricula, nome, rg, cpf, endereco, telefone, email, senha, acesso);
        this.formacao = formacao;
    }

    public Professor(String formacao) {
        this.formacao = formacao;
    }

    public String getFormacao() {
        return formacao;
    }

    public void setFormacao(String formacao) {
        this.formacao = formacao;
    }
    
    public boolean insert(){
        Conexao c = new Conexao();
        Connection con = c.getConexao();
        
        String sql = "INSERT INTO FacProfessor(matricula,nome,rg,cpf,idEndereco,idTelefone,formacao,email,senha,acesso)"
                +"VALUES(?,?,?,?,?,?,?,?,?,?)";
        
        try {
            if(super.getEndereco().insert() == true && super.getTelefone().insert() == true){
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setLong(1, super.getMatricula());
                ps.setString(2, super.getNome());
                ps.setLong(3, super.getRg());
                ps.setString(4, super.getCpf());
                ps.setLong(5, super.getEndereco().getIdEndereco());
                ps.setLong(6, super.getTelefone().getIdTelefone());
                ps.setString(7, formacao);
                ps.setString(8, super.getEmail());
                ps.setString(9, super.getSenha());
                ps.setInt(10, super.getAcesso());
                
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println("Deu problema Professor");
            e.printStackTrace();
            return false;
        }
        return true;
        
    }
    
    @Override
    public String toString() {
        return super.toString()+
                "\nFormação: "+formacao+
                "\nEndereço: "+getEndereco().toString()+
                "\nTelefone: "+getEndereco().toString();
    }
    
    
}
