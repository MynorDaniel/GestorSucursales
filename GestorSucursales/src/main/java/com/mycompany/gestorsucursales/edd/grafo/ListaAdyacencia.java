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
public class ListaAdyacencia {
    
    private NodoAdyacencia primero;
    private int longitud;
    
    public void insertar(Sucursal dato) {
        NodoAdyacencia nuevo = new NodoAdyacencia(dato);
        nuevo.setSiguiente(primero);
        primero = nuevo;
        longitud++;
    }

    public void eliminar(Sucursal dato) {
        NodoAdyacencia actual, anterior;
        boolean encontrado = false;
        actual = primero;
        anterior = null;
        
        while((actual != null) && !encontrado){
            encontrado = (actual.getDato() == null) ? dato == null : actual.getDato().getId() == (dato.getId());
            if(!encontrado){
                anterior = actual;
                actual = actual.getSiguiente();
            }
        }
        
        if(actual != null){
            if(actual == primero){
                primero = actual.getSiguiente();
            }else{
                anterior.setSiguiente(actual.getSiguiente());
            }
            longitud--;
        }
        
    }

    public Sucursal buscar(Sucursal dato) {
        NodoAdyacencia indice;
        for(indice = primero; indice != null; indice = indice.getSiguiente()){
            if(indice.getDato() == null ? dato == null : indice.getDato().getId() == (dato.getId())){
                return indice.getDato();
            }
        }
        return null;
    }

    boolean contains(Sucursal sucursal) {
        return buscar(sucursal) != null;
    }

    public NodoAdyacencia getPrimero() {
        return primero;
    }

    public int getLongitud() {
        return longitud;
    }
}
