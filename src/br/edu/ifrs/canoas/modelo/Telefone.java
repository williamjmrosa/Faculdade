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
public class Telefone {
    private int idTelefone;
    private String tipo;
    private String numero;

    public Telefone() {
    }

    public Telefone(int idTelefone, String tipo, String numero) {
        this.idTelefone = idTelefone;
        this.tipo = tipo;
        this.numero = numero;
    }

    public int getIdTelefone() {
        return idTelefone;
    }

    public void setIdTelefone(int idTelefone) {
        this.idTelefone = idTelefone;
    }
    
    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    @Override
    public String toString() {
        return  "\nID Telefone: "+idTelefone+
                "\nTipo: "+tipo+
                "\nNumero: "+numero;
    }
    
}
