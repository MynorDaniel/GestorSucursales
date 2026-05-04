/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestorsucursales.edd.arbolbmas;

import com.mycompany.gestorsucursales.edd.listas.ListaEnlazadaDesordenada;
import com.mycompany.gestorsucursales.edd.pila.Pila;
import com.mycompany.gestorsucursales.modelos.Producto;
import java.util.*;

/**
 *
 * @author mynordma
 */
public class ArbolBMas {

    private NodoBMas raiz;
    private int d;

    public ArbolBMas(int d) {
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

    public ListaEnlazadaDesordenada<Producto> buscarPorCategoria(String categoria) {
        ListaEnlazadaDesordenada<Producto> resultados = new ListaEnlazadaDesordenada<>();
        if (raiz == null) {
            return resultados;
        }

        NodoBMas hoja = raiz.encontrarHoja(categoria);
        while (hoja != null) {
            for (int i = 0; i < hoja.getCantidadClaves(); i++) {
                Producto producto = hoja.getProducto(i);
                int cmp = NodoBMas.compararCategoria(producto.getCategoria(), categoria);
                if (cmp == 0) {
                    resultados.insertar(producto);
                } else if (cmp > 0) {
                    return resultados;
                }
            }
            hoja = hoja.getSiguiente();
        }

        return resultados;
    }

    public void insertar(Producto producto) {
        validarProducto(producto);

        if (raiz == null) {
            raiz = NodoBMas.crearHoja(d);
            raiz.insertarEnHoja(producto);
            return;
        }

        NodoBMas.ResultadoDivision division = raiz.insertar(producto);
        if (division != null) {
            NodoBMas nuevaRaiz = NodoBMas.crearInterno(d);
            nuevaRaiz.setClave(0, division.getClavePromovida());
            nuevaRaiz.setHijo(0, raiz);
            nuevaRaiz.setHijo(1, division.getNuevoNodo());
            nuevaRaiz.setCantidadClaves(1);
            raiz = nuevaRaiz;
        }
    }

    public void eliminar(Producto producto) {
        validarProducto(producto);

        if (raiz == null) {
            return;
        }

        Pila<NodoBMas> pilaNodos = new Pila<>();
        Pila<Integer> pilaIndices = new Pila<>();
        NodoBMas actual = raiz;

        while (actual != null && !actual.esHoja()) {
            int indice = actual.encontrarIndiceDescenso(producto.getCategoria());
            pilaNodos.insertar(actual);
            pilaIndices.insertar(indice);
            actual = actual.getHijo(indice);
        }

        if (actual == null) {
            return;
        }

        boolean eliminado = actual.eliminarDeHoja(producto);
        if (!eliminado) {
            return;
        }

        if (actual == raiz) {
            if (actual.getCantidadClaves() == 0) {
                raiz = null;
            }
            return;
        }

        rebalancearDesde(actual, pilaNodos, pilaIndices);

        if (raiz != null && !raiz.esHoja() && raiz.getCantidadClaves() == 0) {
            raiz = raiz.getHijo(0);
        }
    }

    private void rebalancearDesde(NodoBMas nodo, Pila<NodoBMas> padres, Pila<Integer> indices) {
        NodoBMas actual = nodo;

        while (actual != null && !(padres.getSize() < 1)) {
            if (actual.getCantidadClaves() >= d) {
                break;
            }

            NodoBMas padre = padres.quitar();
            int indice = indices.quitar();

            NodoBMas hermanoIzq = indice > 0 ? padre.getHijo(indice - 1) : null;
            NodoBMas hermanoDer = indice < padre.getCantidadClaves() ? padre.getHijo(indice + 1) : null;

            if (actual.esHoja()) {
                if (hermanoIzq != null && hermanoIzq.getCantidadClaves() > d) {
                    actual.tomarPrestadoDeIzquierdaHoja(hermanoIzq);
                    padre.setClave(indice - 1, actual.getPrimerCategoria());
                    break;
                }
                if (hermanoDer != null && hermanoDer.getCantidadClaves() > d) {
                    actual.tomarPrestadoDeDerechaHoja(hermanoDer);
                    padre.setClave(indice, hermanoDer.getPrimerCategoria());
                    break;
                }

                if (hermanoIzq != null) {
                    hermanoIzq.fusionarConHoja(actual);
                    padre.eliminarClaveYHijo(indice - 1);
                    actual = hermanoIzq;
                } else if (hermanoDer != null) {
                    actual.fusionarConHoja(hermanoDer);
                    padre.eliminarClaveYHijo(indice);
                }
            } else {
                if (hermanoIzq != null && hermanoIzq.getCantidadClaves() > d) {
                    actual.tomarPrestadoDeIzquierdaInterno(hermanoIzq, padre.getClave(indice - 1));
                    padre.setClave(indice - 1, hermanoIzq.getUltimaClave());
                    break;
                }
                if (hermanoDer != null && hermanoDer.getCantidadClaves() > d) {
                    actual.tomarPrestadoDeDerechaInterno(hermanoDer, padre.getClave(indice));
                    padre.setClave(indice, hermanoDer.getPrimeraClave());
                    break;
                }

                if (hermanoIzq != null) {
                    hermanoIzq.fusionarConInterno(actual, padre.getClave(indice - 1));
                    padre.eliminarClaveYHijo(indice - 1);
                    actual = hermanoIzq;
                } else if (hermanoDer != null) {
                    actual.fusionarConInterno(hermanoDer, padre.getClave(indice));
                    padre.eliminarClaveYHijo(indice);
                }
            }

            if (padre == raiz && padre.getCantidadClaves() == 0) {
                raiz = actual;
                break;
            }

            actual = padre;
        }
    }

    private void validarProducto(Producto producto) {
        if (producto == null) {
            throw new IllegalArgumentException("El producto no puede ser null");
        }
        if (producto.getCategoria() == null || producto.getCodigoBarras() == null) {
            throw new IllegalArgumentException("El producto debe tener categoría y código de barras");
        }
        if (producto.getFechaVencimiento() == null) {
            throw new IllegalArgumentException("El producto debe tener fecha de vencimiento");
        }
    }

    @Override
    public String toString() {
        if (raiz == null) {
            return "Árbol vacío";
        }

        StringBuilder sb = new StringBuilder();
        Queue<NodoBMas> cola = new ArrayDeque<>();
        cola.offer(raiz);
        int nivel = 0;

        while (!cola.isEmpty()) {
            int cantidad = cola.size();
            sb.append("Nivel ").append(nivel).append(": ");

            for (int i = 0; i < cantidad; i++) {
                NodoBMas nodo = cola.poll();
                sb.append(nodo.formatearNodo());
                if (i < cantidad - 1) {
                    sb.append(" ");
                }

                if (!nodo.esHoja()) {
                    for (int j = 0; j <= nodo.getCantidadClaves(); j++) {
                        NodoBMas hijo = nodo.getHijo(j);
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
