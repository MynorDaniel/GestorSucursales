package com.mycompany.gestorsucursales.edd.avl;

import com.mycompany.gestorsucursales.modelos.Producto;
import java.util.ArrayList;
import java.util.List;

public class ArbolAVL {

    private NodoAVL raiz;

    public void insertar(Producto producto) {
        if (producto == null || producto.getNombre() == null) return;
        raiz = insertarRecursivo(raiz, producto);
    }

    public void eliminar(Producto producto) {
        if (producto == null || producto.getNombre() == null) return;
        raiz = eliminarRecursivo(raiz, producto);
    }

    public List<Producto> buscar(Producto p) {
        List<Producto> resultado = new ArrayList<>();
        buscarRecursivo(raiz, p.getNombre(), resultado);
        return resultado;
    }

    private int comparar(Producto a, Producto b) {
        int cmp = a.getNombre().compareTo(b.getNombre());
        if (cmp != 0) return cmp;

        return a.getCodigoBarras().compareTo(b.getCodigoBarras());
    }

    private void buscarRecursivo(NodoAVL nodo, String nombre, List<Producto> resultado) {
        if (nodo == null) return;

        int cmp = nombre.compareTo(nodo.getProducto().getNombre());

        if (cmp < 0) {
            buscarRecursivo(nodo.getIzquierdo(), nombre, resultado);
        } 
        else if (cmp > 0) {
            buscarRecursivo(nodo.getDerecho(), nombre, resultado);
        } 
        else {

            buscarRecursivo(nodo.getIzquierdo(), nombre, resultado);
            resultado.add(nodo.getProducto());
            buscarRecursivo(nodo.getDerecho(), nombre, resultado);
        }
    }

    private NodoAVL insertarRecursivo(NodoAVL nodo, Producto producto) {
        if (nodo == null) return new NodoAVL(producto);

        int cmp = comparar(producto, nodo.getProducto());

        if (cmp < 0) {
            nodo.setIzquierdo(insertarRecursivo(nodo.getIzquierdo(), producto));
        } else if (cmp > 0) {
            nodo.setDerecho(insertarRecursivo(nodo.getDerecho(), producto));
        } else {
            return nodo;
        }

        actualizarAltura(nodo);
        return rebalancear(nodo);
    }

    private NodoAVL eliminarRecursivo(NodoAVL nodo, Producto producto) {
        if (nodo == null) return null;

        int cmp = comparar(producto, nodo.getProducto());

        if (cmp < 0) {
            nodo.setIzquierdo(eliminarRecursivo(nodo.getIzquierdo(), producto));
        } else if (cmp > 0) {
            nodo.setDerecho(eliminarRecursivo(nodo.getDerecho(), producto));
        } else {
            // nodo encontrado

            // 0 o 1 hijo
            if (nodo.getIzquierdo() == null || nodo.getDerecho() == null) {
                NodoAVL temp = (nodo.getIzquierdo() != null)
                        ? nodo.getIzquierdo()
                        : nodo.getDerecho();

                if (temp == null) {
                    return null;
                } else {
                    nodo = temp;
                }
            } else {
                // 2 hijos
                NodoAVL sucesor = minimo(nodo.getDerecho());
                nodo.setProducto(sucesor.getProducto());
                nodo.setDerecho(eliminarRecursivo(nodo.getDerecho(), sucesor.getProducto()));
            }
        }

        actualizarAltura(nodo);
        return rebalancear(nodo);
    }

    private NodoAVL minimo(NodoAVL nodo) {
        while (nodo.getIzquierdo() != null) {
            nodo = nodo.getIzquierdo();
        }
        return nodo;
    }

    private int altura(NodoAVL n) {
        return n == null ? 0 : n.getAltura();
    }

    private void actualizarAltura(NodoAVL n) {
        n.setAltura(1 + Math.max(altura(n.getIzquierdo()), altura(n.getDerecho())));
    }

    private int balance(NodoAVL n) {
        return (n == null) ? 0 : altura(n.getIzquierdo()) - altura(n.getDerecho());
    }

    private NodoAVL rebalancear(NodoAVL n) {
        int bf = balance(n);

        // LL
        if (bf > 1 && balance(n.getIzquierdo()) >= 0)
            return rotacionDerecha(n);

        // LR
        if (bf > 1 && balance(n.getIzquierdo()) < 0) {
            n.setIzquierdo(rotacionIzquierda(n.getIzquierdo()));
            return rotacionDerecha(n);
        }

        // RR
        if (bf < -1 && balance(n.getDerecho()) <= 0)
            return rotacionIzquierda(n);

        // RL
        if (bf < -1 && balance(n.getDerecho()) > 0) {
            n.setDerecho(rotacionDerecha(n.getDerecho()));
            return rotacionIzquierda(n);
        }

        return n;
    }

    private NodoAVL rotacionDerecha(NodoAVL y) {
        NodoAVL x = y.getIzquierdo();
        NodoAVL T2 = x.getDerecho();

        x.setDerecho(y);
        y.setIzquierdo(T2);

        actualizarAltura(y);
        actualizarAltura(x);

        return x;
    }

    private NodoAVL rotacionIzquierda(NodoAVL x) {
        NodoAVL y = x.getDerecho();
        NodoAVL T2 = y.getIzquierdo();

        y.setIzquierdo(x);
        x.setDerecho(T2);

        actualizarAltura(x);
        actualizarAltura(y);

        return y;
    }

    @Override
    public String toString() {
        if (raiz == null) return "(vacio)";
        StringBuilder sb = new StringBuilder();
        imprimir(raiz, sb, "", true);
        return sb.toString();
    }

    private void imprimir(NodoAVL nodo, StringBuilder sb, String prefijo, boolean esIzq) {
        if (nodo == null) return;

        if (nodo.getDerecho() != null) {
            imprimir(nodo.getDerecho(), sb, prefijo + (esIzq ? "│   " : "    "), false);
        }

        sb.append(prefijo)
          .append(esIzq ? "└── " : "┌── ")
          .append(nodo.getProducto().getNombre())
          .append(" (").append(nodo.getProducto().getCodigoBarras()).append(")\n");

        if (nodo.getIzquierdo() != null) {
            imprimir(nodo.getIzquierdo(), sb, prefijo + (esIzq ? "    " : "│   "), true);
        }
    }
}