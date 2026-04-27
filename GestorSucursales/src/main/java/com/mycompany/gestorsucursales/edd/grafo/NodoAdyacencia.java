/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestorsucursales.edd.grafo;

import com.mycompany.gestorsucursales.modelos.Sucursal;

/**
 *
 * @author mynordma
 */
public class NodoAdyacencia {
    
    private Sucursal dato;
    private NodoAdyacencia siguiente;
    
    public NodoAdyacencia(Sucursal dato){
        this.dato = dato;
    }

    public Sucursal getDato() {
        return dato;
    }

    public void setDato(Sucursal dato) {
        this.dato = dato;
    }

    public NodoAdyacencia getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(NodoAdyacencia siguiente) {
        this.siguiente = siguiente;
    }
}
