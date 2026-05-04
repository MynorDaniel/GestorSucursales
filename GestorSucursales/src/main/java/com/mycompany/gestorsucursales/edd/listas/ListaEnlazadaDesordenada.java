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
public class ListaEnlazadaDesordenada<T extends Comparable<T>> {

    private NodoLista<T> primero;
    private int longitud;

    public void insertar(T dato) {
        NodoLista<T> nuevo = new NodoLista<>(dato);
        nuevo.setSiguiente(primero);
        primero = nuevo;
        longitud++;
    }

    public void eliminar(T dato) {
        NodoLista<T> actual, anterior;
        boolean encontrado = false;
        actual = primero;
        anterior = null;

        while ((actual != null) && !encontrado) {
            encontrado = (actual.getDato() == null) ? dato == null : actual.getDato().compareTo(dato) == 0;
            if (!encontrado) {
                anterior = actual;
                actual = actual.getSiguiente();
            }
        }

        if (actual != null) {
            if (actual == primero) {
                primero = actual.getSiguiente();
            } else {
                anterior.setSiguiente(actual.getSiguiente());
            }
            longitud--;
        }

    }

    public T buscar(T dato) {
        NodoLista<T> indice;
        for (indice = primero; indice != null; indice = indice.getSiguiente()) {
            if (indice.getDato() == null ? dato == null : indice.getDato().compareTo(dato) == 0) {
                return indice.getDato();
            }
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

    public T get(int indice) {
        if (indice < 0 || indice >= longitud) {
            throw new IndexOutOfBoundsException("Índice fuera de rango: " + indice);
        }

        NodoLista<T> actual = primero;
        int i = 0;
        while (actual != null) {
            if (i == indice) {
                return actual.getDato();
            }
            actual = actual.getSiguiente();
            i++;
        }
        return null;
    }

}
