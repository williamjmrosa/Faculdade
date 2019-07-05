/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifrs.canoas.modelo;

/**
 *
 * @author William José
 */
public class Responsavel {
    private Long idResponsavel;
    private String nome;
    private Long rg;
    private String cpf;
    private Telefone telefone;
    private String email;
    private String senha;
    private int acesso;

    public Responsavel() {
    }

    public Responsavel(Long idResponsavel, String nome, Long rg, String cpf, Telefone telefone, String email, String senha, int acesso) {
        this.idResponsavel = idResponsavel;
        this.nome = nome;
        this.rg = rg;
        this.cpf = cpf;
        this.telefone = telefone;
        this.email = email;
        this.senha = senha;
        this.acesso = acesso;
    }
    
    public Long getIdResponsavel() {
        return idResponsavel;
    }

    public void setIdResponsavel(Long idResponsavel) {
        this.idResponsavel = idResponsavel;
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getRg() {
        return rg;
    }

    public void setRg(Long rg) {
        this.rg = rg;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public int getAcesso() {
        return acesso;
    }

    public void setAcesso(int acesso) {
        this.acesso = acesso;
    }

    public Telefone getTelefone() {
        return telefone;
    }

    public void setTelefone(Telefone telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    
    @Override
    public String toString() {
        return  "\nNome: "+nome+
                "\nRG: "+rg+
                "\nCPF: "+cpf+
                "\nE-mail: "+email+
                "\nSenha: "+senha+
                "\nAcesso: "+acesso+
                "\nTelefone: "+telefone.toString();
    }
    
}
