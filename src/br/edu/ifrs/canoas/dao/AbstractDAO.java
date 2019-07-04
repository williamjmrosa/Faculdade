/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifrs.canoas.dao;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author William José
 */
public abstract class AbstractDAO<T> {
       
    public abstract T getOne(Long id) throws SQLException;
    
    public abstract Long insert(T o) throws SQLException;
    
    public abstract boolean delete(T o) throws SQLException;
    
    public abstract boolean update(T o) throws SQLException;
    
    public abstract ArrayList<T> buscar(Long id) throws SQLException;
    
    public abstract ArrayList<T> filtrar(Long id) throws SQLException;
    
    public abstract ArrayList<T> filtrar(String texto) throws SQLException;
}
