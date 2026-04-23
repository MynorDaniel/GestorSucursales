/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestorsucursales.edd.arbolb;

import com.mycompany.gestorsucursales.modelos.Producto;

/**
 *
 * @author mynordma
 */
public class NodoB {
    private Producto[] claves;
    private int d;
    private NodoB[] hijos;
    private int cantidadClaves;
    private boolean esHoja;

    public NodoB(int d, boolean esHoja) {
        this.claves = new Producto[2 * d + 1];
        this.d = d;
        this.hijos = new NodoB[2 * d + 2];
        this.cantidadClaves = 0;
        this.esHoja = esHoja;
    }
    
    private int compararProductos(Producto p1, Producto p2){
        int cmpFecha = p1.getFechaVencimiento().compareTo(p2.getFechaVencimiento());
        if(cmpFecha != 0){
            return cmpFecha;
        }
        
        return p1.getCodigoBarras().compareTo(p2.getCodigoBarras());
    }

    public void insertarNoLleno(Producto clave) {
        int i = cantidadClaves - 1;

        if (esHoja) {
            while (i >= 0 && compararProductos(claves[i], clave) > 0) {
                claves[i + 1] = claves[i];
                i--;
            }
            claves[i + 1] = clave;
            cantidadClaves++;
            return;
        }

        while (i >= 0 && compararProductos(claves[i], clave) > 0) {
            i--;
        }

        int indiceHijo = i + 1;
        hijos[indiceHijo].insertarNoLleno(clave);

        if (hijos[indiceHijo].cantidadClaves > 2 * d) {
            dividirHijo(indiceHijo, hijos[indiceHijo]);
        }
    }

    public void dividirHijo(int indice, NodoB hijoDesbordado) {
        NodoB nuevoHermano = new NodoB(d, hijoDesbordado.esHoja);
        Producto clavePromovida = hijoDesbordado.claves[d];

        nuevoHermano.cantidadClaves = d;
        for (int j = 0; j < d; j++) {
            nuevoHermano.claves[j] = hijoDesbordado.claves[d + 1 + j];
        }

        if (!hijoDesbordado.esHoja) {
            for (int j = 0; j <= d; j++) {
                nuevoHermano.hijos[j] = hijoDesbordado.hijos[d + 1 + j];
                hijoDesbordado.hijos[d + 1 + j] = null;
            }
        }

        hijoDesbordado.cantidadClaves = d;

        for (int j = cantidadClaves; j >= indice + 1; j--) {
            hijos[j + 1] = hijos[j];
        }
        hijos[indice + 1] = nuevoHermano;

        for (int j = cantidadClaves - 1; j >= indice; j--) {
            claves[j + 1] = claves[j];
        }
        claves[indice] = clavePromovida;
        cantidadClaves++;
    }

    public void recorrer() {
        for (int i = 0; i < cantidadClaves; i++) {
            if (!esHoja) {
                hijos[i].recorrer();
            }
            System.out.print(" " + claves[i]);
        }

        if (!esHoja) {
            hijos[cantidadClaves].recorrer();
        }
    }

    public NodoB buscar(Producto clave) {
        int i = 0;
        while (i < cantidadClaves && compararProductos(clave, claves[i]) > 0) {
            i++;
        }

        if (i < cantidadClaves && compararProductos(clave, claves[i]) == 0) {
            return this;
        }

        if (esHoja) {
            return null;
        }

        return hijos[i].buscar(clave);
    }

    private int encontrarIndiceClave(Producto clave) {
        int indice = 0;
        while (indice < cantidadClaves && compararProductos(claves[indice], clave) < 0) {
            indice++;
        }
        return indice;
    }

    public void eliminar(Producto clave) {
        int indice = encontrarIndiceClave(clave);

        if (indice < cantidadClaves && compararProductos(claves[indice], clave) == 0) {
            if (esHoja) {
                eliminarDeHoja(indice);
            } else {
                eliminarDeInterno(indice);
            }
            return;
        }

        if (esHoja) {
            return;
        }

        boolean eraUltimoHijo = (indice == cantidadClaves);

        if (hijos[indice].cantidadClaves == d) {
            rellenar(indice);
        }

        if (eraUltimoHijo && indice > cantidadClaves) {
            int indiceDescenso = indice - 1;
            hijos[indiceDescenso].eliminar(clave);
            if (hijos[indiceDescenso].cantidadClaves > 2 * d) {
                dividirHijo(indiceDescenso, hijos[indiceDescenso]);
            }
        } else {
            int indiceDescenso = indice;
            hijos[indiceDescenso].eliminar(clave);
            if (hijos[indiceDescenso].cantidadClaves > 2 * d) {
                dividirHijo(indiceDescenso, hijos[indiceDescenso]);
            }
        }
    }

    private void eliminarDeHoja(int indice) {
        System.arraycopy(claves, indice + 1, claves, indice, cantidadClaves - indice - 1);
        cantidadClaves--;
    }

    private void eliminarDeInterno(int indice) {
        Producto clave = claves[indice];

        if (hijos[indice].cantidadClaves >= d + 1) {
            Producto predecesor = obtenerPredecesor(indice);
            claves[indice] = predecesor;
            hijos[indice].eliminar(predecesor);
        } else if (hijos[indice + 1].cantidadClaves >= d + 1) {
            Producto sucesor = obtenerSucesor(indice);
            claves[indice] = sucesor;
            hijos[indice + 1].eliminar(sucesor);
        } else {
            fusionar(indice);
            hijos[indice].eliminar(clave);
            if (hijos[indice].cantidadClaves > 2 * d) {
                dividirHijo(indice, hijos[indice]);
            }
        }
    }

    private Producto obtenerPredecesor(int indice) {
        NodoB actual = hijos[indice];
        while (!actual.esHoja) {
            actual = actual.hijos[actual.cantidadClaves];
        }
        return actual.claves[actual.cantidadClaves - 1];
    }

    private Producto obtenerSucesor(int indice) {
        NodoB actual = hijos[indice + 1];
        while (!actual.esHoja) {
            actual = actual.hijos[0];
        }
        return actual.claves[0];
    }

    private void rellenar(int indice) {
        if (indice != 0 && hijos[indice - 1].cantidadClaves >= d + 1) {
            prestarDelAnterior(indice);
        } else if (indice != cantidadClaves && hijos[indice + 1].cantidadClaves >= d + 1) {
            prestarDelSiguiente(indice);
        } else {
            if (indice != cantidadClaves) {
                fusionar(indice);
            } else {
                fusionar(indice - 1);
            }
        }
    }

    private void prestarDelAnterior(int indice) {
        NodoB hijo = hijos[indice];
        NodoB hermano = hijos[indice - 1];

        System.arraycopy(hijo.claves, 0, hijo.claves, 1, hijo.cantidadClaves);
        if (!hijo.esHoja) {
            System.arraycopy(hijo.hijos, 0, hijo.hijos, 1, hijo.cantidadClaves + 1);
        }

        hijo.claves[0] = claves[indice - 1];
        if (!hijo.esHoja) {
            hijo.hijos[0] = hermano.hijos[hermano.cantidadClaves];
        }

        claves[indice - 1] = hermano.claves[hermano.cantidadClaves - 1];

        hijo.cantidadClaves++;
        hermano.cantidadClaves--;
    }

    private void prestarDelSiguiente(int indice) {
        NodoB hijo = hijos[indice];
        NodoB hermano = hijos[indice + 1];

        hijo.claves[hijo.cantidadClaves] = claves[indice];
        if (!hijo.esHoja) {
            hijo.hijos[hijo.cantidadClaves + 1] = hermano.hijos[0];
        }

        claves[indice] = hermano.claves[0];

        System.arraycopy(hermano.claves, 1, hermano.claves, 0, hermano.cantidadClaves - 1);
        if (!hermano.esHoja) {
            System.arraycopy(hermano.hijos, 1, hermano.hijos, 0, hermano.cantidadClaves);
        }

        hijo.cantidadClaves++;
        hermano.cantidadClaves--;
    }

    private void fusionar(int indice) {
        NodoB hijo = hijos[indice];
        NodoB hermano = hijos[indice + 1];

        hijo.claves[hijo.cantidadClaves] = claves[indice];

        System.arraycopy(hermano.claves, 0, hijo.claves, hijo.cantidadClaves + 1, hermano.cantidadClaves);

        if (!hijo.esHoja) {
            System.arraycopy(hermano.hijos, 0, hijo.hijos, hijo.cantidadClaves + 1, hermano.cantidadClaves + 1);
        }

        hijo.cantidadClaves += hermano.cantidadClaves + 1;

        System.arraycopy(claves, indice + 1, claves, indice, cantidadClaves - indice - 1);
        System.arraycopy(hijos, indice + 2, hijos, indice + 1, cantidadClaves - indice - 1);

        cantidadClaves--;
        hijos[cantidadClaves + 1] = null;
    }

    public int getCantidadClaves() {
        return cantidadClaves;
    }

    public boolean esHoja() {
        return esHoja;
    }

    public NodoB getHijo(int indice) {
        return hijos[indice];
    }

    public void setHijo(int indice, NodoB hijo) {
        hijos[indice] = hijo;
    }

    public String formatearNodo() {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < cantidadClaves; i++) {
            sb.append(claves[i].getFechaVencimiento()).append("(").append(claves[i].getCodigoBarras()).append(")");
            if (i < cantidadClaves - 1) {
                sb.append(" | ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}