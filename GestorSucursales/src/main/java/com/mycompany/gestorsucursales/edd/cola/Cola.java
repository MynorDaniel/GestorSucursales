/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestorsucursales.edd.cola;

import com.mycompany.gestorsucursales.edd.listas.NodoLista;

/**
 *
 * @author mynordma
 * @param <T>
 */
public class Cola<T> {
    
    private NodoLista<T> frente;
    private NodoLista<T> ultimo;
    private int size;

    public NodoLista<T> getFrente() {
        return frente;
    }
    
    public void insertar(T dato){
        if(dato == null) return;
        NodoLista<T> nuevo = new NodoLista<>(dato);
        if(frente == null){
            frente = nuevo;
        }else{
            ultimo.setSiguiente(nuevo);
        }
        ultimo = nuevo;
        
        size++;
    }
    
    public T quitar(){
        if(frente == null){
            return null;
        }else{
            T aux = frente.getDato();
            frente = frente.getSiguiente();
            
            if(frente == null) ultimo = null;
            
            size--;
            return aux;
        }
    }
    
    public T verCima(){
        if(frente == null){
            return null;
        }else{
            return frente.getDato();
        }
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Cola de tamaño: " ).append(size).append("\n");
        
        NodoLista<T> actual = frente;

        while (actual != null) {
            sb.append(String.valueOf(actual.getDato())).append("\n");
            actual = actual.getSiguiente();
        }

        return sb.toString();
    }
    
    public int getSize() {
        return size;
    }
}
