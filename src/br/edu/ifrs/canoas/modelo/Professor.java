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
    
    @Override
    public String toString() {
        return super.toString()+
                "\nFormação: "+formacao+
                "\nEndereço: "+getEndereco().toString()+
                "\nTelefone: "+getEndereco().toString();
    }
    
    
}
