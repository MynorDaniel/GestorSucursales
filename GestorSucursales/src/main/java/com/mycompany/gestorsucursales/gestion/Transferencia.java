/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestorsucursales.gestion;

import com.mycompany.gestorsucursales.edd.grafo.Criterio;
import com.mycompany.gestorsucursales.edd.grafo.Grafo;
import com.mycompany.gestorsucursales.edd.grafo.Ruta;
import com.mycompany.gestorsucursales.excepciones.SucursalException;
import com.mycompany.gestorsucursales.modelos.Producto;
import com.mycompany.gestorsucursales.modelos.Sucursal;

/**
 *
 * @author mynordma
 */
public class Transferencia {

    public void transferir(Grafo grafo, Sucursal origen, Sucursal destino, Criterio criterio, Producto producto) {
        try {
            Ruta ruta = grafo.rutaMinima(origen.getId(), destino.getId(), criterio);
            Sucursal[] rutaSucursales = ruta.getCaminoSucursales();
            producto.setRuta(rutaSucursales);
            producto.setIndiceRuta(0);

            origen.getColaRecepcion().insertar(producto);

        } catch (SucursalException ex) {
            System.getLogger(Transferencia.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }

}
