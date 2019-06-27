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
public class Disciplina {
    private Long idDisciplina;
    private String nome;
    private String descricao;
    private String texto;

    public Disciplina() {
    }

    public Long getIdDisciplina() {
        return idDisciplina;
    }

    public void setIdDisciplina(Long idDisciplina) {
        this.idDisciplina = idDisciplina;
    }
    
    public Disciplina(String nome) {
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
    
    public void mostrar(int s){
        if(s == 1){
            texto = idDisciplina+" - "+nome;
        }else{
            texto = "\nID Disciplina: "+idDisciplina+
                "\nDisciplina: "+nome+
                "\nDescrição: "+descricao;
        }
    }
    
    @Override
    public String toString() {
        return  texto;
    }
    
    
}
