/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestorsucursales.gestion;

import com.mycompany.gestorsucursales.edd.grafo.Criterio;
import com.mycompany.gestorsucursales.edd.grafo.Grafo;
import com.mycompany.gestorsucursales.edd.grafo.Ruta;
import com.mycompany.gestorsucursales.excepciones.ProductoException;
import com.mycompany.gestorsucursales.excepciones.SucursalException;
import com.mycompany.gestorsucursales.modelos.Producto;
import com.mycompany.gestorsucursales.modelos.Sucursal;

/**
 *
 * @author mynordma
 */
public class Transferencia {

    public Ruta transferir(Grafo grafo, Sucursal origen, Sucursal destino, Criterio criterio, Producto producto)
            throws SucursalException, ProductoException {
        if (grafo == null || origen == null || destino == null || criterio == null || producto == null) {
            throw new SucursalException("Parámetros inválidos para la transferencia");
        }

        Producto productoOrigen = origen.getTablaHash().buscar(producto);
        if (productoOrigen == null) {
            throw new ProductoException("El producto no existe en la sucursal de origen");
        }

        Ruta ruta = grafo.rutaMinima(origen.getId(), destino.getId(), criterio);
        if (ruta.getCaminoSucursales().length == 0 || Double.isInfinite(ruta.getCosto())) {
            throw new SucursalException("No existe una ruta válida para la transferencia");
        }

        Sucursal[] rutaSucursales = ruta.getCaminoSucursales();
        productoOrigen.setRuta(rutaSucursales);
        productoOrigen.setIndiceRuta(0);

        for (Sucursal sucursal : rutaSucursales) {
            sucursal.iniciarProcesamiento();
        }

        origen.getColaRecepcion().insertar(productoOrigen);

        return ruta;
    }

}
