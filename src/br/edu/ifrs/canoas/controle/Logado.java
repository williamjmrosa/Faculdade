/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifrs.canoas.controle;

import br.edu.ifrs.canoas.modelo.Aluno;
import br.edu.ifrs.canoas.modelo.Funcionario;
import br.edu.ifrs.canoas.modelo.Pessoa;
import br.edu.ifrs.canoas.modelo.Professor;
import br.edu.ifrs.canoas.modelo.Responsavel;

/**
 *
 * @author William
 */
public class Logado {
    private static Pessoa pessoa = null;
    private static Responsavel responsavel = null;

    public Logado() {
    }

    public static Pessoa getPessoa() {
        return pessoa;
    }

    public static void setPessoa(Pessoa pessoa) {
        Logado.pessoa = pessoa;
    }
    
    
    
    public static Funcionario getFuncionario(){
       Funcionario f = (Funcionario) Logado.getPessoa();
       return f;
    }
    
    public static Professor getProfessor(){
        Professor p = (Professor) Logado.getPessoa();
        return p;
    }
    
    public static Aluno getAluno(){
        Aluno a = (Aluno) Logado.pessoa;
        return a;
    }

    public static Responsavel getResponsavel() {
        return responsavel;
    }

    public static void setResponsavel(Responsavel responsavel) {
        Logado.responsavel = responsavel;
    }
    public static int getTipo(){
        if(pessoa != null){
            return pessoa.getAcesso();
        }else{
            return responsavel.getAcesso();
        }
    }
}
