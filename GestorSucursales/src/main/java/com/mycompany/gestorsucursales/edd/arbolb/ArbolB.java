/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestorsucursales.edd.arbolb;

import com.mycompany.gestorsucursales.modelos.Producto;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mynordma
 */
public class ArbolB {

    private final int d;
    private NodoB raiz;

    public ArbolB() {
        this(2);
    }

    public ArbolB(int d) {
        if (d < 2) {
            throw new IllegalArgumentException("d debe ser >= 2");
        }
        this.d = d;
        this.raiz = null;
    }

    public List<Producto> buscar(String fechaInicial, String fechaFinal) {
        List<Producto> resultado = new ArrayList<>();
        if (fechaInicial == null || fechaFinal == null || raiz == null) {
            return resultado;
        }

        if (fechaInicial.compareTo(fechaFinal) > 0) {
            return resultado;
        }

        buscarPorRangoRecursivo(raiz, fechaInicial, fechaFinal, resultado);
        return resultado;
    }

    private void buscarPorRangoRecursivo(NodoB nodo, String fechaInicial, String fechaFinal, List<Producto> salida) {
        if (nodo == null) {
            return;
        }

        int i;
        for (i = 0; i < nodo.getCantidadClaves(); i++) {
            Producto actual = nodo.getClave(i);

            if (!nodo.esHoja() && actual.getFechaVencimiento().compareTo(fechaInicial) >= 0) {
                buscarPorRangoRecursivo(nodo.getHijo(i), fechaInicial, fechaFinal, salida);
            }

            if (actual.getFechaVencimiento().compareTo(fechaInicial) >= 0
                    && actual.getFechaVencimiento().compareTo(fechaFinal) <= 0) {
                salida.add(actual);
            }
        }

        if (!nodo.esHoja()) {
            buscarPorRangoRecursivo(nodo.getHijo(i), fechaInicial, fechaFinal, salida);
        }
    }

    public void insertar(Producto producto) {
        if (producto == null || producto.getFechaVencimiento() == null || producto.getNombre() == null) {
            return;
        }

        if (raiz == null) {
            raiz = new NodoB(d, true);
            raiz.setClave(0, producto);
            raiz.setCantidadClaves(1);
            return;
        }

        List<NodoB> caminoPadres = new ArrayList<>();
        List<Integer> caminoIndices = new ArrayList<>();

        NodoB actual = raiz;
        while (!actual.esHoja()) {
            int indiceHijo = 0;
            while (indiceHijo < actual.getCantidadClaves()
                    && compararPorFecha(actual.getClave(indiceHijo), producto) <= 0) {
                indiceHijo++;
            }

            caminoPadres.add(actual);
            caminoIndices.add(indiceHijo);
            actual = actual.getHijo(indiceHijo);
        }

        insertarOrdenadoEnHoja(actual, producto);

        NodoB nodoEnRevision = actual;
        while (nodoEnRevision.getCantidadClaves() > 2 * d) {
            int indiceMedio = d;
            int cantidadActual = nodoEnRevision.getCantidadClaves();

            Producto clavePromovida = nodoEnRevision.getClave(indiceMedio);
            NodoB derecho = new NodoB(d, nodoEnRevision.esHoja());

            int jDerecho = 0;
            for (int j = indiceMedio + 1; j < cantidadActual; j++) {
                derecho.setClave(jDerecho++, nodoEnRevision.getClave(j));
            }
            derecho.setCantidadClaves(jDerecho);

            if (!nodoEnRevision.esHoja()) {
                int hDerecho = 0;
                for (int j = indiceMedio + 1; j <= cantidadActual; j++) {
                    derecho.setHijo(hDerecho++, nodoEnRevision.getHijo(j));
                }
            }

            nodoEnRevision.setCantidadClaves(indiceMedio);

            if (caminoPadres.isEmpty()) {
                NodoB nuevaRaiz = new NodoB(d, false);
                nuevaRaiz.setClave(0, clavePromovida);
                nuevaRaiz.setHijo(0, nodoEnRevision);
                nuevaRaiz.setHijo(1, derecho);
                nuevaRaiz.setCantidadClaves(1);
                raiz = nuevaRaiz;
                return;
            }

            int ultimo = caminoPadres.size() - 1;
            NodoB padre = caminoPadres.remove(ultimo);
            int indiceEnPadre = caminoIndices.remove(ultimo);

            insertarPromovidaEnPadre(padre, indiceEnPadre, clavePromovida, derecho);
            nodoEnRevision = padre;
        }
    }

    private void insertarOrdenadoEnHoja(NodoB nodo, Producto clave) {
        int i = nodo.getCantidadClaves() - 1;
        while (i >= 0 && compararPorFecha(nodo.getClave(i), clave) > 0) {
            nodo.setClave(i + 1, nodo.getClave(i));
            i--;
        }
        nodo.setClave(i + 1, clave);
        nodo.setCantidadClaves(nodo.getCantidadClaves() + 1);
    }

    private void insertarPromovidaEnPadre(NodoB padre, int indiceHijoIzquierdo,
                                          Producto clavePromovida, NodoB nuevoHijoDerecho) {
        int cantidad = padre.getCantidadClaves();

        for (int i = cantidad - 1; i >= indiceHijoIzquierdo; i--) {
            padre.setClave(i + 1, padre.getClave(i));
        }

        for (int i = cantidad; i >= indiceHijoIzquierdo + 1; i--) {
            padre.setHijo(i + 1, padre.getHijo(i));
        }

        padre.setClave(indiceHijoIzquierdo, clavePromovida);
        padre.setHijo(indiceHijoIzquierdo + 1, nuevoHijoDerecho);
        padre.setCantidadClaves(cantidad + 1);
    }

    public void eliminar(String fechaCaducidad) {
        if (fechaCaducidad == null) {
            return;
        }

        List<Producto> todos = new ArrayList<>();
        recolectarEnOrden(raiz, todos);

        boolean removido = false;
        List<Producto> restantes = new ArrayList<>();
        for (Producto p : todos) {
            if (!removido && p.getFechaVencimiento().compareTo(fechaCaducidad) == 0) {
                removido = true;
            } else {
                restantes.add(p);
            }
        }

        if (!removido) {
            return;
        }

        raiz = null;
        for (Producto p : restantes) {
            insertar(p);
        }
    }

    public void eliminar(Producto producto) {
        if (producto == null) {
            return;
        }
        eliminar(producto.getFechaVencimiento());
    }

    private int compararPorFecha(Producto a, Producto b) {
        int cmpFecha = a.getFechaVencimiento().compareTo(b.getFechaVencimiento());
        if (cmpFecha != 0) {
            return cmpFecha;
        }

        String nombreA = a.getNombre() == null ? "" : a.getNombre();
        String nombreB = b.getNombre() == null ? "" : b.getNombre();
        int cmpNombre = nombreA.compareTo(nombreB);
        if (cmpNombre != 0) {
            return cmpNombre;
        }

        String codA = a.getCodigoBarras() == null ? "" : a.getCodigoBarras();
        String codB = b.getCodigoBarras() == null ? "" : b.getCodigoBarras();
        return codA.compareTo(codB);
    }

    public List<String> recorrerEnOrdenNombres() {
        List<Producto> productos = new ArrayList<>();
        recolectarEnOrden(raiz, productos);

        List<String> nombres = new ArrayList<>();
        for (Producto p : productos) {
            nombres.add(p.getNombre());
        }
        return nombres;
    }

    private void recolectarEnOrden(NodoB nodo, List<Producto> salida) {
        if (nodo == null) {
            return;
        }

        int i;
        for (i = 0; i < nodo.getCantidadClaves(); i++) {
            if (!nodo.esHoja()) {
                recolectarEnOrden(nodo.getHijo(i), salida);
            }
            salida.add(nodo.getClave(i));
        }
        if (!nodo.esHoja()) {
            recolectarEnOrden(nodo.getHijo(i), salida);
        }
    }

    public String toStringPorNiveles() {
        if (raiz == null) {
            return "(vacio)";
        }

        StringBuilder sb = new StringBuilder();
        List<NodoB> nivelActual = new ArrayList<>();
        nivelActual.add(raiz);

        int nivel = 0;
        while (!nivelActual.isEmpty()) {
            sb.append("Nivel ").append(nivel).append(": ");
            List<NodoB> siguiente = new ArrayList<>();

            for (int i = 0; i < nivelActual.size(); i++) {
                NodoB nodo = nivelActual.get(i);
                sb.append("[");
                for (int k = 0; k < nodo.getCantidadClaves(); k++) {
                    sb.append(formatearClave(nodo.getClave(k)));
                    if (k < nodo.getCantidadClaves() - 1) {
                        sb.append(" | ");
                    }
                }
                sb.append("]");
                if (i < nivelActual.size() - 1) {
                    sb.append("  ");
                }

                if (!nodo.esHoja()) {
                    for (int h = 0; h <= nodo.getCantidadClaves(); h++) {
                        NodoB hijo = nodo.getHijo(h);
                        if (hijo != null) {
                            siguiente.add(hijo);
                        }
                    }
                }
            }

            sb.append("\n");
            nivelActual = siguiente;
            nivel++;
        }

        return sb.toString().trim();
    }

    private String formatearClave(Producto producto) {
        String fecha = producto.getFechaVencimiento() == null ? "sin-fecha" : producto.getFechaVencimiento();
        String codigo = producto.getCodigoBarras() == null ? "sin-codigo" : producto.getCodigoBarras();
        return fecha + "(" + codigo + ")";
    }

    @Override
    public String toString() {
        return toStringPorNiveles();
    }

}
