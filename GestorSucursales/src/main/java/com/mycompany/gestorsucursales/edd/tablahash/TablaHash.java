package com.mycompany.gestorsucursales.edd.tablahash;

import com.mycompany.gestorsucursales.modelos.Producto;

public class TablaHash {

    private Producto[] t;
    private int n;
    private int m;

    private final double FC_IDEAL = 0.8;
    private final double R = 0.6180339887;

    public TablaHash() {
        m = 16;
        t = new Producto[m];
        n = 0;
    }

    /**
     * Hash por multiplicación.
     */
    private int h(int x) {
        double rx = x * R;
        double frac = rx - Math.floor(rx);
        return (int) (m * frac);
    }

    private double factorCarga() {
        return (double) n / m;
    }

    /**
     * Inserta usando exploración lineal.
     * @param p
     */
    public void insertar(Producto p) {
        if (factorCarga() > FC_IDEAL) {
            redimensionar();
        }

        int x = Integer.parseInt(p.getCodigoBarras());
        int i = h(x);
        int inicio = i;

        do {
            if (t[i] == null) {
                t[i] = p;
                n++;
                return;
            }

            if (t[i].getCodigoBarras().equals(p.getCodigoBarras())) {
                t[i] = p; // actualizar
                return;
            }

            i = (i + 1) % m;
        } while (i != inicio);
        
    }

    /**
     * Busca usando exploración lineal.
     * @param p
     * @return 
     */
    public Producto buscar(Producto p) {
        int x = Integer.parseInt(p.getCodigoBarras());
        int i = h(x);
        int inicio = i;

        do {
            if (t[i] == null) {
                return null;
            }

            if (t[i].getCodigoBarras().equals(p.getCodigoBarras())) {
                return t[i];
            }

            i = (i + 1) % m;
        } while (i != inicio);

        return null;
    }

    public void eliminar(Producto p) {
        int x = Integer.parseInt(p.getCodigoBarras());
        int i = h(x);
        int inicio = i;

        do {
            if (t[i] == null) {
                return;
            }

            if (t[i].getCodigoBarras().equals(p.getCodigoBarras())) {
                t[i] = null;
                n--;

                reinsertar((i + 1) % m);
                return;
            }

            i = (i + 1) % m;
        } while (i != inicio);
    }

    /**
     * Reinsertar elementos consecutivos tras una eliminación.
     */
    private void reinsertar(int inicio) {
        int i = inicio;

        while (t[i] != null) {
            Producto temp = t[i];
            t[i] = null;
            n--;

            insertar(temp);

            i = (i + 1) % m;
        }
    }

    /**
     * Duplica el tamaño.
     */
    private void redimensionar() {
        Producto[] vieja = t;

        m *= 2;
        t = new Producto[m];
        n = 0;

        for (Producto p : vieja) {
            if (p != null) {
                insertar(p);
            }
        }
    }

    public int size() {
        return n;
    }

    public boolean estaVacia() {
        return n == 0;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < m; i++) {
            sb.append(i).append(": ");

            if (t[i] == null) {
                sb.append("null");
            } else {
                sb.append(t[i].getCodigoBarras());
            }

            sb.append(System.lineSeparator());
        }

        return sb.toString();
    }
}