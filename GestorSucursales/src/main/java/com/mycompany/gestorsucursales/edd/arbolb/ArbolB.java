/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestorsucursales.edd.arbolb;

import com.mycompany.gestorsucursales.modelos.Producto;
import java.util.ArrayDeque;
import java.util.Queue;

/**
 *
 * @author mynordma
 */
public class ArbolB {
    private NodoB raiz;
    private int d;

    public ArbolB(int d) {
        if (d < 1) {
            throw new IllegalArgumentException("El grado d debe ser >= 1");
        }
        this.raiz = null;
        this.d = d;
    }

    public void recorrer() {
        if (raiz != null) {
            raiz.recorrer();
        }
    }

    public NodoB buscar(Producto clave) {
        return raiz == null ? null : raiz.buscar(clave);
    }

    public void insertar(Producto clave) {
        if (raiz == null) {
            raiz = new NodoB(d, true);
            raiz.insertarNoLleno(clave);
            return;
        }

        raiz.insertarNoLleno(clave);

        if (raiz.getCantidadClaves() > 2 * d) {
            NodoB nuevaRaiz = new NodoB(d, false);
            nuevaRaiz.setHijo(0, raiz);
            nuevaRaiz.dividirHijo(0, raiz);
            raiz = nuevaRaiz;
        }
    }

    public void eliminar(Producto clave) {
        if (raiz == null) {
            return;
        }

        if (buscar(clave) == null) {
            return;
        }

        raiz.eliminar(clave);

        if (raiz != null && raiz.getCantidadClaves() > 2 * d) {
            NodoB nuevaRaiz = new NodoB(d, false);
            nuevaRaiz.setHijo(0, raiz);
            nuevaRaiz.dividirHijo(0, raiz);
            raiz = nuevaRaiz;
        }

        if (raiz != null && raiz.getCantidadClaves() == 0) {
            if (raiz.esHoja()) {
                raiz = null;
            } else {
                raiz = raiz.getHijo(0);
            }
        }
    }

    @Override
    public String toString() {
        if (raiz == null) {
            return "Árbol vacío";
        }

        StringBuilder sb = new StringBuilder();
        Queue<NodoB> cola = new ArrayDeque<>();
        cola.offer(raiz);
        int nivel = 0;

        while (!cola.isEmpty()) {
            int cantidadNodos = cola.size();
            sb.append("Nivel ").append(nivel).append(": ");

            for (int i = 0; i < cantidadNodos; i++) {
                NodoB nodo = cola.poll();
                sb.append(nodo.formatearNodo());

                if (i < cantidadNodos - 1) {
                    sb.append(" ");
                }

                if (!nodo.esHoja()) {
                    for (int j = 0; j <= nodo.getCantidadClaves(); j++) {
                        NodoB hijo = nodo.getHijo(j);
                        if (hijo != null) {
                            cola.offer(hijo);
                        }
                    }
                }
            }

            if (!cola.isEmpty()) {
                sb.append(System.lineSeparator());
            }

            nivel++;
        }

        return sb.toString();
    }
}

