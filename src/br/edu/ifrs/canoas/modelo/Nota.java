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
    private Turma turma;
    private Aluno aluno;

    public Nota() {
    }

    public Nota(int idNota, double nota1, double nota2, double nota3, Turma turma, Aluno aluno) {
        this.idNota = idNota;
        this.nota1 = nota1;
        this.nota2 = nota2;
        this.nota3 = nota3;
        this.turma = turma;
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

    public Turma getTurma() {
        return turma;
    }

    public void setTurma(Turma turma) {
        this.turma = turma;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }
    
    public String getNome(){
        return aluno.getNome();
    }
    
    public double getMedia(){
        return calcularNota();
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
