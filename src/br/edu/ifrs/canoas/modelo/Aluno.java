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
    
    private Data dataNascimento;
    private Curso curso;
    private Responsavel responsavel;

    public Aluno() {
    }

    public Aluno(Data dataNascimento, Curso curso, Responsavel responsavel) {
        this.dataNascimento = dataNascimento;
        this.curso = curso;
        this.responsavel = responsavel;
    }

    public Aluno(Data dataNascimento, Curso curso, Responsavel responsavel, Long matricula, String nome, Long rg, String cpf, Endereco endereco, Telefone telefone, String email, String senha, int acesso) {
        super(matricula, nome, rg, cpf, endereco, telefone, email, senha, acesso);
        this.dataNascimento = dataNascimento;
        this.curso = curso;
        this.responsavel = responsavel;
    }

    public Data getDataNascimento() {
        return dataNascimento;
    }    

    public void setDataNascimento(String dataNasc) {
        String[] partes;
        partes = dataNasc.split("/"); 
        Data d = new Data();
        
        d.setDia(Integer.parseInt(partes[0]));
        d.setMes(Integer.parseInt(partes[1]));
        d.setAno(Integer.parseInt(partes[2]));
        this.dataNascimento = d;
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
                "\nData Nascimento: "+dataNascimento.getDia()+"/"+dataNascimento.getMes()+"/"+dataNascimento.getAno()+
                "\nCurso:"+curso.toString()+
                "\nEndereco: "+getEndereco().toString()+
                "\nTelefone: "+getTelefone().toString();
    }
    
    
}
