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
public abstract class Pessoa {
    private Long matricula;
    private String nome;
    private Long rg;
    private String cpf;
    private Endereco endereco;
    private Telefone telefone;
    private String email;
    private String senha;
    private int acesso;
    private String texto;

    public Pessoa() {
    }

    public Pessoa(Long matricula, String nome, Long rg, String cpf, Endereco endereco, Telefone telefone, String email, String senha, int acesso) {
        this.matricula = matricula;
        this.nome = nome;
        this.rg = rg;
        this.cpf = cpf;
        this.endereco = endereco;
        this.telefone = telefone;
        this.email = email;
        this.senha = senha;
        this.acesso = acesso;
    }

    public Long getMatricula() {
        return matricula;
    }

    public void setMatricula(Long matricula) {
        this.matricula = matricula;
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

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
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

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }
    
    public abstract void mostrar(int s);
   
    @Override
    public String toString() {
        return  "\nMatricula: "+matricula+
                "\nNome: "+nome+
                "\nRG: "+rg+
                "\nCPF: "+cpf+
                "\nE-mail: "+email+
                "\nSenha: "+senha+
                "\nAcesso: "+acesso;
    }
    
    
}
