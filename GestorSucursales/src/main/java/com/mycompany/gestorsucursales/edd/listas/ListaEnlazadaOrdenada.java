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
public class ListaEnlazadaOrdenada extends ListaEnlazada {

    @Override
    public void insertar(Producto dato) {
        NodoLista nuevo = new NodoLista(dato);
        if(primero == null || dato.getCodigoBarras().compareTo(primero.getDato().getCodigoBarras()) < 0){
            nuevo.setSiguiente(primero);
            primero = nuevo;
            longitud++;
            return;
        }
        NodoLista actual = primero;

        while (actual.getSiguiente() != null &&
            dato.getCodigoBarras().compareTo(actual.getSiguiente().getDato().getCodigoBarras()) > 0) {
            actual = actual.getSiguiente();
        }

        nuevo.setSiguiente(actual.getSiguiente());
        actual.setSiguiente(nuevo);
        
        longitud++;
    }

    @Override
    public void eliminar(Producto dato) {
        if (primero == null) return;

        if (primero.getDato().getCodigoBarras().equals(dato.getCodigoBarras())) {
            primero = primero.getSiguiente();
            longitud--;
            return;
        }

        NodoLista actual = primero;

        while (actual.getSiguiente() != null) {
            int cmp = actual.getSiguiente().getDato().getCodigoBarras()
                    .compareTo(dato.getCodigoBarras());

            if (cmp == 0) {
                actual.setSiguiente(actual.getSiguiente().getSiguiente());
                longitud--;
                return;
            } else if (cmp > 0) {
                return;
            }

            actual = actual.getSiguiente();
        }
        
    }

    @Override
    public Producto buscar(Producto dato) {
        NodoLista actual = primero;

        while (actual != null) {
            int cmp = actual.getDato().getCodigoBarras()
                    .compareTo(dato.getCodigoBarras());

            if (cmp == 0) {
                return actual.getDato();
            } else if (cmp > 0) {
                return null;
            }

            actual = actual.getSiguiente();
        }

        return null;
    }
    
}
