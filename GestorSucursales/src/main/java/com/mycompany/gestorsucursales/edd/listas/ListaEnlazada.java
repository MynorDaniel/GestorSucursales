/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestorsucursales.edd.listas;

import com.mycompany.gestorsucursales.modelos.Producto;

/**
 *
 * @author mynordma
 */
public abstract class ListaEnlazada {
    
    protected NodoLista primero;
    protected int longitud;
   
    public abstract void insertar(Producto dato);
    public abstract void eliminar(Producto dato);
    public abstract Producto buscar(Producto dato);

    public NodoLista getPrimero() {
        return primero;
    }

    public void setPrimero(NodoLista primero) {
        this.primero = primero;
    }

    public int getLongitud() {
        return longitud;
    }

    public void setLongitud(int longitud) {
        this.longitud = longitud;
    }
    
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Lista de tamaño: ").append(longitud).append("\n");
        NodoLista indice;
        for(indice = primero; indice != null; indice = indice.getSiguiente()){
            if(indice.getDato() != null){
                sb.append(indice.getDato().toString()).append("\n");
            }
        }
        return sb.toString();
    }

}