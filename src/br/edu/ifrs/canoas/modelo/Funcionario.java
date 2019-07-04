/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifrs.canoas.modelo;

/**
 *
 * @author William
 */
public class Funcionario extends Pessoa{

    public Funcionario() {
    }

    public Funcionario(Long matricula, String nome, Long rg, String cpf, Endereco endereco, Telefone telefone, String email, String senha, int acesso) {
        super(matricula, nome, rg, cpf, endereco, telefone, email, senha, acesso);
    }
    
    @Override
    public void mostrar(int s) {
        if(s == 1){
            super.setTexto(super.getMatricula()+" - "+super.getNome());
        }else{
            super.setTexto(super.toString()+
                    "\nEndereco: "+super.getEndereco()+
                    "\nTelefone: "+super.getTelefone());
        }
    }

    @Override
    public String toString() {
        return super.getTexto(); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
