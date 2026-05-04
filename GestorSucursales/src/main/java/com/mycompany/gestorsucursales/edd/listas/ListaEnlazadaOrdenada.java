/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestorsucursales.edd.listas;

/**
 *
 * @author mynordma
 * @param <T>
 */
public class ListaEnlazadaOrdenada<T extends Comparable<T>> {

    private NodoLista<T> primero;
    private int longitud;

    public void insertar(T dato) {
        NodoLista<T> nuevo = new NodoLista<>(dato);
        if (primero == null || dato.compareTo(primero.getDato()) < 0) {
            nuevo.setSiguiente(primero);
            primero = nuevo;
            longitud++;
            return;
        }
        NodoLista<T> actual = primero;

        while (actual.getSiguiente() != null
                && dato.compareTo(actual.getSiguiente().getDato()) > 0) {
            actual = actual.getSiguiente();
        }

        nuevo.setSiguiente(actual.getSiguiente());
        actual.setSiguiente(nuevo);

        longitud++;
    }

    public void eliminar(T dato) {
        if (primero == null) {
            return;
        }

        if (primero.getDato().compareTo(dato) == 0) {
            primero = primero.getSiguiente();
            longitud--;
            return;
        }

        NodoLista<T> actual = primero;

        while (actual.getSiguiente() != null) {
            int cmp = actual.getSiguiente().getDato().compareTo(dato);

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

    public T buscar(T dato) {
        NodoLista<T> actual = primero;

        while (actual != null) {
            int cmp = actual.getDato().compareTo(dato);

            if (cmp == 0) {
                return actual.getDato();
            } else if (cmp > 0) {
                return null;
            }

            actual = actual.getSiguiente();
        }

        return null;
    }

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
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Lista de tamaño: ").append(longitud).append("\n");
        NodoLista indice;
        for (indice = primero; indice != null; indice = indice.getSiguiente()) {
            if (indice.getDato() != null) {
                sb.append(indice.getDato().toString()).append("\n");
            }
        }
        return sb.toString();
    }

    public boolean isEmpty() {
        return longitud == 0;
    }

    public int size() {
        return longitud;
    }

    public T extraerPrimero() {
        if (primero == null) {
            return null;
        }
        T dato = primero.getDato();
        primero = primero.getSiguiente();
        longitud--;
        return dato;
    }

}
