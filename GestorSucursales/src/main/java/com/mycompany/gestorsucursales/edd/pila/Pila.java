/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestorsucursales.edd.pila;

import com.mycompany.gestorsucursales.edd.listas.NodoLista;
import com.mycompany.gestorsucursales.modelos.Producto;

/**
 *
 * @author mynordma
 */
public class Pila {
    
    private NodoLista cima;
    private int size;
    
    public void insertar(Producto p){
        if(p == null) return;
        NodoLista nuevo = new NodoLista(p);
        nuevo.setSiguiente(cima);
        cima = nuevo;
        size++;
    }
    
    public Producto quitar(){
        if(cima == null){
            return null;
        }else{
            Producto aux = cima.getDato();
            cima = cima.getSiguiente();
            size--;
            return aux;
        }
    }
    
    public Producto verCima(){
        if(cima == null){
            return null;
        }else{
            return cima.getDato();
        }
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Pila de tamaño: " ).append(size).append("\n");
        
        NodoLista actual = cima;

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
