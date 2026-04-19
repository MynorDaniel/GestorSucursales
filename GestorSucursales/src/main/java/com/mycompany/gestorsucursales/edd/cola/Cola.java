/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestorsucursales.edd.cola;

import com.mycompany.gestorsucursales.edd.listas.NodoLista;
import com.mycompany.gestorsucursales.modelos.Producto;

/**
 *
 * @author mynordma
 */
public class Cola {
    
    private NodoLista frente;
    private NodoLista ultimo;
    private int size;
    
    public void insertar(Producto p){
        if(p == null) return;
        NodoLista nuevo = new NodoLista(p);
        if(frente == null){
            frente = nuevo;
        }else{
            ultimo.setSiguiente(nuevo);
        }
        ultimo = nuevo;
        
        size++;
    }
    
    public Producto quitar(){
        if(frente == null){
            return null;
        }else{
            Producto aux = frente.getDato();
            frente = frente.getSiguiente();
            
            if(frente == null) ultimo = null;
            
            size--;
            return aux;
        }
    }
    
    public Producto verCima(){
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
        
        NodoLista actual = frente;

        while (actual != null) {
            sb.append(actual.getDato().toString()).append("\n");
            actual = actual.getSiguiente();
        }

        return sb.toString();
    }
    
    public int getSize() {
        return size;
    }
}
