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
public class Nota {
    private int idNota;
    private double nota1;
    private double nota2;
    private double nota3;
    private Professor professor;
    private Aluno aluno;

    public Nota() {
    }

    public Nota(int idNota, double nota1, double nota2, double nota3, Professor professor, Aluno aluno) {
        this.idNota = idNota;
        this.nota1 = nota1;
        this.nota2 = nota2;
        this.nota3 = nota3;
        this.professor = professor;
        this.aluno = aluno;
    }
    
    public int getIdNota() {
        return idNota;
    }

    public void setIdNota(int idNota) {
        this.idNota = idNota;
    }
    
    public double getNota1() {
        return nota1;
    }

    public void setNota1(double nota1) {
        this.nota1 = nota1;
    }

    public double getNota2() {
        return nota2;
    }

    public void setNota2(double nota2) {
        this.nota2 = nota2;
    }

    public double getNota3() {
        return nota3;
    }

    public void setNota3(double nota3) {
        this.nota3 = nota3;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public double calcularNota(){
        return (nota1+nota2+nota3)/3;
    }
    
    @Override
    public String toString() {
        return  "\nID Nota: "+idNota+
                "\nNota 1: "+nota1+
                "\nNota 2: "+nota2+
                "\nNota 3: "+nota3+
                "\nMédia: "+calcularNota();
                
    }
    
    
    
}
