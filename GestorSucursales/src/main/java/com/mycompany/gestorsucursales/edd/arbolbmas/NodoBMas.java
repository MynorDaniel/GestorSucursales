/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestorsucursales.edd.arbolbmas;

import com.mycompany.gestorsucursales.modelos.Producto;

/**
 *
 * @author mynordma
 */
public class NodoBMas {

    private final int d;
    private final boolean esHoja;
    private int cantidadClaves;

    private String[] claves;
    private Producto[] productos;
    private NodoBMas[] hijos;
    private NodoBMas siguiente;

    private NodoBMas(int d, boolean esHoja) {
        this.d = d;
        this.esHoja = esHoja;
        this.cantidadClaves = 0;
        this.claves = new String[2 * d + 1];
        this.productos = new Producto[2 * d + 1];
        this.hijos = new NodoBMas[2 * d + 2];
    }

    public static NodoBMas crearHoja(int d) {
        return new NodoBMas(d, true);
    }

    public static NodoBMas crearInterno(int d) {
        return new NodoBMas(d, false);
    }

    public boolean esHoja() {
        return esHoja;
    }

    public int getCantidadClaves() {
        return cantidadClaves;
    }

    public void setCantidadClaves(int cantidadClaves) {
        this.cantidadClaves = cantidadClaves;
    }

    public NodoBMas getHijo(int indice) {
        return hijos[indice];
    }

    public void setHijo(int indice, NodoBMas hijo) {
        hijos[indice] = hijo;
    }

    public String getClave(int indice) {
        return claves[indice];
    }

    public void setClave(int indice, String clave) {
        claves[indice] = clave;
    }

    public Producto getProducto(int indice) {
        return productos[indice];
    }

    public NodoBMas getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(NodoBMas siguiente) {
        this.siguiente = siguiente;
    }

    public void recorrer() {
        if (esHoja) {
            for (int i = 0; i < cantidadClaves; i++) {
                System.out.print(" " + productos[i]);
            }
            return;
        }

        for (int i = 0; i < cantidadClaves; i++) {
            hijos[i].recorrer();
        }
        hijos[cantidadClaves].recorrer();
    }

    public NodoBMas encontrarHoja(String categoria) {
        NodoBMas actual = this;
        while (actual != null && !actual.esHoja) {
            int indice = actual.encontrarIndiceDescenso(categoria);
            actual = actual.hijos[indice];
        }
        return actual;
    }

    public int encontrarIndiceDescenso(String categoria) {
        int i = 0;
        while (i < cantidadClaves && compararCategoria(categoria, claves[i]) >= 0) {
            i++;
        }
        return i;
    }

    public void insertarEnHoja(Producto producto) {
        int i = cantidadClaves - 1;
        while (i >= 0 && compararProductos(productos[i], producto) > 0) {
            productos[i + 1] = productos[i];
            i--;
        }
        productos[i + 1] = producto;
        cantidadClaves++;
    }

    public ResultadoDivision insertar(Producto producto) {
        if (esHoja) {
            insertarEnHoja(producto);
            if (cantidadClaves > 2 * d) {
                return dividirHoja();
            }
            return null;
        }

        int indice = encontrarIndiceDescenso(producto.getCategoria());
        ResultadoDivision division = hijos[indice].insertar(producto);
        if (division != null) {
            insertarClaveYHijo(indice, division.clavePromovida, division.nuevoNodo);
        }

        if (cantidadClaves > 2 * d) {
            return dividirInterno();
        }
        return null;
    }

    private void insertarClaveYHijo(int indice, String clave, NodoBMas nuevoHijo) {
        for (int i = cantidadClaves; i > indice; i--) {
            claves[i] = claves[i - 1];
        }
        for (int i = cantidadClaves + 1; i > indice + 1; i--) {
            hijos[i] = hijos[i - 1];
        }
        claves[indice] = clave;
        hijos[indice + 1] = nuevoHijo;
        cantidadClaves++;
    }

    private ResultadoDivision dividirHoja() {
        NodoBMas nuevaHoja = crearHoja(d);
        int inicioDerecha = d;
        int total = cantidadClaves;
        int tamDerecha = total - inicioDerecha;

        System.arraycopy(productos, inicioDerecha, nuevaHoja.productos, 0, tamDerecha);
        nuevaHoja.cantidadClaves = tamDerecha;
        for (int i = inicioDerecha; i < total; i++) {
            productos[i] = null;
        }

        cantidadClaves = inicioDerecha;
        nuevaHoja.siguiente = this.siguiente;
        this.siguiente = nuevaHoja;

        String clavePromovida = nuevaHoja.getPrimerCategoria();
        return new ResultadoDivision(clavePromovida, nuevaHoja);
    }

    private ResultadoDivision dividirInterno() {
        NodoBMas nuevoInterno = crearInterno(d);
        int indicePromovido = d;
        String clavePromovida = claves[indicePromovido];

        int clavesDerecha = cantidadClaves - indicePromovido - 1;
        System.arraycopy(claves, indicePromovido + 1, nuevoInterno.claves, 0, clavesDerecha);
        System.arraycopy(hijos, indicePromovido + 1, nuevoInterno.hijos, 0, clavesDerecha + 1);

        for (int i = indicePromovido; i < cantidadClaves; i++) {
            claves[i] = null;
            hijos[i + 1] = null;
        }

        nuevoInterno.cantidadClaves = clavesDerecha;
        cantidadClaves = indicePromovido;

        return new ResultadoDivision(clavePromovida, nuevoInterno);
    }

    public boolean eliminarDeHoja(Producto producto) {
        int indice = encontrarIndiceProducto(producto);
        if (indice < 0) {
            return false;
        }

        System.arraycopy(productos, indice + 1, productos, indice, cantidadClaves - indice - 1);
        productos[cantidadClaves - 1] = null;
        cantidadClaves--;
        return true;
    }

    private int encontrarIndiceProducto(Producto producto) {
        for (int i = 0; i < cantidadClaves; i++) {
            if (compararProductos(productos[i], producto) == 0) {
                return i;
            }
        }
        return -1;
    }

    public void tomarPrestadoDeIzquierdaHoja(NodoBMas izquierdo) {
        for (int i = cantidadClaves; i > 0; i--) {
            productos[i] = productos[i - 1];
        }
        productos[0] = izquierdo.productos[izquierdo.cantidadClaves - 1];
        izquierdo.productos[izquierdo.cantidadClaves - 1] = null;
        izquierdo.cantidadClaves--;
        cantidadClaves++;
    }

    public void tomarPrestadoDeDerechaHoja(NodoBMas derecho) {
        productos[cantidadClaves] = derecho.productos[0];
        System.arraycopy(derecho.productos, 1, derecho.productos, 0, derecho.cantidadClaves - 1);
        derecho.productos[derecho.cantidadClaves - 1] = null;
        derecho.cantidadClaves--;
        cantidadClaves++;
    }

    public void fusionarConHoja(NodoBMas derecho) {
        System.arraycopy(derecho.productos, 0, productos, cantidadClaves, derecho.cantidadClaves);
        cantidadClaves += derecho.cantidadClaves;
        this.siguiente = derecho.siguiente;
    }

    public void tomarPrestadoDeIzquierdaInterno(NodoBMas izquierdo, String clavePadre) {
        for (int i = cantidadClaves; i > 0; i--) {
            claves[i] = claves[i - 1];
        }
        for (int i = cantidadClaves + 1; i > 0; i--) {
            hijos[i] = hijos[i - 1];
        }
        claves[0] = clavePadre;
        hijos[0] = izquierdo.hijos[izquierdo.cantidadClaves];

        claves[cantidadClaves + 1] = null;
        hijos[cantidadClaves + 1] = null;

        cantidadClaves++;
    }

    public void tomarPrestadoDeDerechaInterno(NodoBMas derecho, String clavePadre) {
        claves[cantidadClaves] = clavePadre;
        hijos[cantidadClaves + 1] = derecho.hijos[0];

        System.arraycopy(derecho.claves, 1, derecho.claves, 0, derecho.cantidadClaves - 1);
        System.arraycopy(derecho.hijos, 1, derecho.hijos, 0, derecho.cantidadClaves);
        derecho.claves[derecho.cantidadClaves - 1] = null;
        derecho.hijos[derecho.cantidadClaves] = null;

        derecho.cantidadClaves--;
        cantidadClaves++;
    }

    public void fusionarConInterno(NodoBMas derecho, String clavePadre) {
        claves[cantidadClaves] = clavePadre;
        System.arraycopy(derecho.claves, 0, claves, cantidadClaves + 1, derecho.cantidadClaves);
        System.arraycopy(derecho.hijos, 0, hijos, cantidadClaves + 1, derecho.cantidadClaves + 1);
        cantidadClaves += derecho.cantidadClaves + 1;
    }

    public void eliminarClaveYHijo(int indiceClave) {
        System.arraycopy(claves, indiceClave + 1, claves, indiceClave, cantidadClaves - indiceClave - 1);
        System.arraycopy(hijos, indiceClave + 2, hijos, indiceClave + 1, cantidadClaves - indiceClave - 1);
        claves[cantidadClaves - 1] = null;
        hijos[cantidadClaves] = null;
        cantidadClaves--;
    }

    public String getPrimerCategoria() {
        if (esHoja) {
            return productos[0].getCategoria();
        }
        return claves[0];
    }

    public String getPrimeraClave() {
        return claves[0];
    }

    public String getUltimaClave() {
        return claves[cantidadClaves - 1];
    }

    public String formatearNodo() {
        StringBuilder sb = new StringBuilder("[");
        if (esHoja) {
            for (int i = 0; i < cantidadClaves; i++) {
                sb.append(productos[i].getCategoria())
                        .append("(")
                        .append(productos[i].getCodigoBarras())
                        .append(")");
                if (i < cantidadClaves - 1) {
                    sb.append(" | ");
                }
            }
        } else {
            for (int i = 0; i < cantidadClaves; i++) {
                sb.append(claves[i]);
                if (i < cantidadClaves - 1) {
                    sb.append(" | ");
                }
            }
        }
        sb.append("]");
        return sb.toString();
    }

    public static int compararCategoria(String a, String b) {
        String sa = a == null ? "" : a;
        String sb = b == null ? "" : b;
        return sa.compareTo(sb);
    }

    public static int compararProductos(Producto p1, Producto p2) {
        int cmpCat = compararCategoria(p1.getCategoria(), p2.getCategoria());
        if (cmpCat != 0) {
            return cmpCat;
        }

        int cmpCodigo = compararCategoria(p1.getCodigoBarras(), p2.getCodigoBarras());
        if (cmpCodigo != 0) {
            return cmpCodigo;
        }

        return compararCategoria(p1.getFechaVencimiento(), p2.getFechaVencimiento());
    }

    public static class ResultadoDivision {

        private final String clavePromovida;
        private final NodoBMas nuevoNodo;

        public ResultadoDivision(String clavePromovida, NodoBMas nuevoNodo) {
            this.clavePromovida = clavePromovida;
            this.nuevoNodo = nuevoNodo;
        }

        public String getClavePromovida() {
            return clavePromovida;
        }

        public NodoBMas getNuevoNodo() {
            return nuevoNodo;
        }
    }
}
