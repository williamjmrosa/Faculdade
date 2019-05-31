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
public class Turma {
    private int idTurma;
    private String nome;
    private Professor professor;
    private Disciplina disciplina;

    public Turma() {
    }

    public Turma(int idTurma, String nome, Professor professor, Disciplina disciplina) {
        this.idTurma = idTurma;
        this.nome = nome;
        this.professor = professor;
        this.disciplina = disciplina;
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public Disciplina getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(Disciplina disciplina) {
        this.disciplina = disciplina;
    }

    public void addDisciplina(Disciplina dis){
        this.disciplina = dis;
    }
    
    public void addProfessor(Professor pro){
        this.professor = pro;
    }
    
    @Override
    public String toString() {
        return "\nNome: "+nome+
                "\nDisciplina: "+disciplina.toString()+
                "\nProfessor: "+professor.toString();
    }
    
    
}
