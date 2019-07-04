/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifrs.canoas.controle;

import br.edu.ifrs.canoas.modelo.Pessoa;
import br.edu.ifrs.canoas.modelo.Responsavel;

/**
 *
 * @author William
 */
public class Logado {
    private static Pessoa p = null;
    private static Responsavel r = null;

    public Logado() {
    }

    public static Pessoa getP() {
        return p;
    }

    public static void setP(Pessoa p) {
        Logado.p = p;
    }

    public static Responsavel getR() {
        return r;
    }

    public static void setR(Responsavel r) {
        Logado.r = r;
    }
    public Object getLogado(){
        if(p != null){
            return p;
        }else{
            return r;
        }
    }
}
