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
public class Aluno extends Pessoa{
    
    private String dataNascimento;
    private Curso curso;
    private Responsavel responsavel;

    public Aluno() {
    }

    public Aluno(String dataNascimento, Curso curso, Responsavel responsavel) {
        this.dataNascimento = dataNascimento;
        this.curso = curso;
        this.responsavel = responsavel;
    }

    public Aluno(String dataNascimento, Curso curso, Responsavel responsavel, Long matricula, String nome, Long rg, String cpf, Endereco endereco, Telefone telefone, String email, String senha, int acesso) {
        super(matricula, nome, rg, cpf, endereco, telefone, email, senha, acesso);
        this.dataNascimento = dataNascimento;
        this.curso = curso;
        this.responsavel = responsavel;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public Responsavel getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(Responsavel responsavel) {
        this.responsavel = responsavel;
    }

    @Override
    public String toString() {
        return  super.toString()+
                "\nData Nascimento: "+dataNascimento+
                "\nCurso:"+curso.toString()+
                "\nEndereco: "+getEndereco().toString()+
                "\nTelefone: "+getTelefone().toString();
    }
    
    
}
