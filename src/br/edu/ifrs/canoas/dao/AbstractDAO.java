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
    
    private static Long id;

    public static Long getId() {
        return id;
    }

    public static void setId(Long id) {
        AbstractDAO.id = id;
    }
    
    public abstract T getOne();
    
    public abstract Long insert(T o);
    
    public abstract boolean delete(T o);
    
    public abstract boolean update(T o);
    
    public abstract ArrayList<T> buscar(Long id);
    
    public abstract T login(String usuario, String senha);
    
    public abstract T logado(int id);
    
}
