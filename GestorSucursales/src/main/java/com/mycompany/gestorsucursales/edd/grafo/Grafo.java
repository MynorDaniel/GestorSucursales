package com.mycompany.gestorsucursales.edd.grafo;

import com.mycompany.gestorsucursales.modelos.Sucursal;

public class Grafo {

    private final ListaAdyacencia sucursales;

    public Grafo() {
        sucursales = new ListaAdyacencia();
    }

    public void agregarSucursal(Sucursal sucursal) {
        if (!sucursales.contains(sucursal)) {
            sucursales.insertar(sucursal);
        }
    }

    public void agregarConexion(Sucursal origen, Sucursal destino) {
        agregarSucursal(origen);
        agregarSucursal(destino);

        origen.getAdyacentes().insertar(destino);
    }

    public ListaAdyacencia obtenerAdyacentes(Sucursal sucursal) {
        return sucursal.getAdyacentes();
    }

    public int obtenerPeso(Sucursal origen, Sucursal destino) {
        return origen.calcularPeso(destino);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        NodoAdyacencia nodoSucursal = sucursales.getPrimero();

        while (nodoSucursal != null) {
            Sucursal origen = nodoSucursal.getDato();

            sb.append(origen.getId())
              .append(" -> ");

            NodoAdyacencia nodoAdyacente =
                    origen.getAdyacentes().getPrimero();

            while (nodoAdyacente != null) {
                Sucursal destino = nodoAdyacente.getDato();

                sb.append("[")
                  .append(destino.getId())
                  .append(", peso=")
                  .append(origen.calcularPeso(destino))
                  .append("] ");

                nodoAdyacente = nodoAdyacente.getSiguiente();
            }

            sb.append(System.lineSeparator());
            nodoSucursal = nodoSucursal.getSiguiente();
        }

        return sb.toString();
    }
}