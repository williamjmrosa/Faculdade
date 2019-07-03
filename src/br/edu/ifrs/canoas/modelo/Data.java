/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifrs.canoas.modelo;

/**
 *
 * @author Aluno
 */
public class Data {
    private int dia;
    private int mes;
    private int ano;

    public Data() {
    }

    public Data(int dia, int mes, int ano){
        setDia(dia);
        setMes(mes);
        setAno(ano);
    }

    public int getDia() {
        return dia;
    }

    public void setDia(int dia){
        if(dia <= 31 && dia > 0){
            this.dia = dia;
        }else{
            
        }
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes){
        if(mes <= 12 && mes > 0){
            this.mes = mes;
        }else{
           
        }
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano){
        if(ano > 0){
            this.ano = ano;
        }else{
            
        }
    }
    
    
}
