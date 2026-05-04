/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestorsucursales.edd.arbolb;

import com.mycompany.gestorsucursales.edd.listas.ListaEnlazadaDesordenada;
import com.mycompany.gestorsucursales.modelos.Producto;

/**
 *
 * @author mynordma
 */
public class ArbolB {

    private NodoB raiz;
    private int d;

    public NodoB getRaiz() {
        return raiz;
    }

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

    public ListaEnlazadaDesordenada<Producto> buscar(Producto clave) {
        if (raiz == null) {
            return null;
        }

        Producto[] posiblesProductos = raiz.buscar(clave).getClaves();
        ListaEnlazadaDesordenada<Producto> productos = new ListaEnlazadaDesordenada<>();

        for (Producto posibleProducto : posiblesProductos) {
            if (posibleProducto.getFechaVencimiento().equals(clave.getFechaVencimiento())) {
                productos.insertar(posibleProducto);
            }
        }
        return productos;
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

}
