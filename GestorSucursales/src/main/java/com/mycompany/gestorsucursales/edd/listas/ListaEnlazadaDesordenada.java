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
public class ListaEnlazadaDesordenada extends ListaEnlazada {

    @Override
    public void insertar(Producto dato) {
        NodoLista nuevo = new NodoLista(dato);
        nuevo.setSiguiente(primero);
        primero = nuevo;
        longitud++;
    }

    @Override
    public void eliminar(Producto dato) {
        NodoLista actual, anterior;
        boolean encontrado = false;
        actual = primero;
        anterior = null;
        
        while((actual != null) && !encontrado){
            encontrado = (actual.getDato() == null) ? dato == null : actual.getDato().getCodigoBarras().equals(dato.getCodigoBarras());
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

    @Override
    public Producto buscar(Producto dato) {
        NodoLista indice;
        for(indice = primero; indice != null; indice = indice.getSiguiente()){
            if(indice.getDato() == null ? dato == null : indice.getDato().getCodigoBarras().equals(dato.getCodigoBarras())){
                return indice.getDato();
            }
        }
        return null;
    }
    
}
