/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifrs.canoas.dao;

import java.util.ArrayList;

/**
 *
 * @author William José
 */
public abstract class AbstractDAO<T> {
       
    public abstract T getOne(Long id);
    
    public abstract Long insert(T o);
    
    public abstract boolean delete(T o);
    
    public abstract boolean update(T o);
    
    public abstract ArrayList<T> buscar(Long id);
    
    public abstract ArrayList<T> filtrar(Long id);
    
    public abstract ArrayList<T> filtrar(String texto);
}
