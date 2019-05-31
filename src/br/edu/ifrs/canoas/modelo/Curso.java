/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifrs.canoas.modelo;

import java.util.ArrayList;

/**
 *
 * @author William José
 */
public class Curso {
    private int idCurso;
    private String nome;
    private String descricao;
    private ArrayList<Disciplina> l = new ArrayList<>();

    public Curso() {
    }

    public Curso(int idCurso, String nome, String descricao) {
        this.idCurso = idCurso;
        this.nome = nome;
        this.descricao = descricao;
    }
    
    public int getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(int idCurso) {
        this.idCurso = idCurso;
    }
    
    public Curso(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    public ArrayList<Disciplina> getL() {
        return l;
    }

    public void setL(ArrayList<Disciplina> l) {
        this.l = l;
    }
    
    public void addDisciplina(Disciplina dis){
        l.add(dis);
    }

    @Override
    public String toString() {
        return "\nNome: "+nome+
                "\nDisciplinas "+l.toString();
    }
    
}
    