/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestorsucursales.edd.grafo;

import com.mycompany.gestorsucursales.modelos.Sucursal;
import java.util.Arrays;

/**
 *
 * @author mynordma
 */
public class Ruta {

    private final int[] caminoIds;
    private final Sucursal[] caminoSucursales;
    private final double costo;
    private final Criterio criterio;

    public Ruta(int[] caminoIds, Sucursal[] caminoSucursales, double costo, Criterio criterio) {
        this.caminoIds = caminoIds;
        this.caminoSucursales = caminoSucursales;
        this.costo = costo;
        this.criterio = criterio;
    }

    public int[] getCaminoIds() {
        return caminoIds;
    }

    public Sucursal[] getCaminoSucursales() {
        return caminoSucursales;
    }

    public double getCosto() {
        return costo;
    }

    public Criterio getCriterio() {
        return criterio;
    }

    @Override
    public String toString() {
        return "Ruta{"
                + "caminoIds=" + Arrays.toString(caminoIds)
                + ", caminoSucursales=" + Arrays.toString(caminoSucursales)
                + ", costo=" + costo
                + ", criterio=" + criterio
                + '}';
    }
}
