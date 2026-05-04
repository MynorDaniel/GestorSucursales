package com.mycompany.gestorsucursales.edd.tablahash;

import com.mycompany.gestorsucursales.modelos.Producto;

public class TablaHash<K, V> {

    public Entrada<K, V>[] getTabla() {
        return t;
    }

    private Entrada<K, V>[] t;
    private int n;
    private int m;

    private final double FC_IDEAL = 0.8;
    private final double R = 0.6180339887;

    public TablaHash() {
        m = 16;
        t = crearTabla(m);
        n = 0;
    }

    /**
     * Hash por multiplicación.
     */
    private int h(long x) {
        double rx = x * R;
        double frac = rx - Math.floor(rx);
        return (int) (m * frac);
    }

    private long hashKey(K key) {
        if (key == null) {
            throw new IllegalArgumentException("La clave no puede ser null");
        }

        if (key instanceof Producto producto) {
            long codigo = Long.parseLong(producto.getCodigoBarras());
            return codigo;
        }

        return key.hashCode();
    }

    public Object[] claves() {
        Object[] claves = new Object[n];
        int idx = 0;
        for (int i = 0; i < m; i++) {
            if (t[i] != null) {
                claves[idx++] = t[i].getClave();
            }
        }
        return claves;
    }

    private boolean clavesIguales(K a, K b) {
        if (a == null || b == null) {
            return a == b;
        }

        if (a instanceof Producto && b instanceof Producto) {
            Producto pa = (Producto) a;
            Producto pb = (Producto) b;
            return pa.getCodigoBarras().equals(pb.getCodigoBarras());
        }

        return a.equals(b);
    }

    private double factorCarga() {
        return (double) n / m;
    }

    /**
     * Inserta usando exploración lineal.
     *
     * @param clave
     * @param valor
     */
    public void insertar(K clave, V valor) {
        if (factorCarga() > FC_IDEAL) {
            redimensionar();
        }

        int i = h(hashKey(clave));
        int inicio = i;

        do {
            if (t[i] == null) {
                t[i] = new Entrada<>(clave, valor);
                n++;
                return;
            }

            if (clavesIguales(t[i].getClave(), clave)) {
                t[i].setValor(valor); // actualizar
                return;
            }

            i = (i + 1) % m;
        } while (i != inicio);

    }

    /**
     * Busca usando exploración lineal.
     *
     * @param clave
     * @return
     */
    public V buscar(K clave) {
        int i = h(hashKey(clave));
        int inicio = i;

        do {
            if (t[i] == null) {
                return null;
            }

            if (clavesIguales(t[i].getClave(), clave)) {
                return t[i].getValor();
            }

            i = (i + 1) % m;
        } while (i != inicio);

        return null;
    }

    public void eliminar(K clave) {
        int i = h(hashKey(clave));
        int inicio = i;

        do {
            if (t[i] == null) {
                return;
            }

            if (clavesIguales(t[i].getClave(), clave)) {
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
            Entrada<K, V> temp = t[i];
            t[i] = null;
            n--;

            insertar(temp.getClave(), temp.getValor());

            i = (i + 1) % m;
        }
    }

    /**
     * Duplica el tamaño.
     */
    private void redimensionar() {
        Entrada<K, V>[] vieja = t;

        m *= 2;
        t = crearTabla(m);
        n = 0;

        for (Entrada<K, V> e : vieja) {
            if (e != null) {
                insertar(e.getClave(), e.getValor());
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
                sb.append(String.valueOf(t[i].getClave()));
            }

            sb.append(System.lineSeparator());
        }

        return sb.toString();
    }

    private Entrada<K, V>[] crearTabla(int capacidad) {
        return (Entrada<K, V>[]) new Entrada[capacidad];
    }
}
