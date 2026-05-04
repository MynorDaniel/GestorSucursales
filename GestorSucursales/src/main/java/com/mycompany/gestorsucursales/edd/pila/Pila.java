/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestorsucursales.edd.pila;

import com.mycompany.gestorsucursales.edd.listas.NodoLista;

/**
 *
 * @author mynordma
 * @param <T>
 */
public class Pila<T> {

    private NodoLista<T> cima;
    private int size;

    public void insertar(T dato) {
        if (dato == null) {
            return;
        }
        NodoLista<T> nuevo = new NodoLista<>(dato);
        nuevo.setSiguiente(cima);
        cima = nuevo;
        size++;
    }

    public T quitar() {
        if (cima == null) {
            return null;
        } else {
            T aux = cima.getDato();
            cima = cima.getSiguiente();
            size--;
            return aux;
        }
    }

    public T verCima() {
        if (cima == null) {
            return null;
        } else {
            return cima.getDato();
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Pila de tamaño: ").append(size).append("\n");

        NodoLista<T> actual = cima;

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
