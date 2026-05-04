/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.gestorsucursales;

import com.mycompany.gestorsucursales.edd.grafo.*;
import com.mycompany.gestorsucursales.modelos.Sucursal;
import com.mycompany.gestorsucursales.vistas.Ventana;
import com.mycompany.gestorsucursales.vistas.Ventana01;

/**
 *
 * @author mynordma
 */
public class GestorSucursales {

    public static void main(String[] args) {
        Ventana ventana = new Ventana();
        ventana.setVisible(true);
        //test();
    }

    private static void test() {
        /*Grafo grafo = new Grafo();*/

        //grafo.agregarSucursal(new Sucursal(1, "Sucursal Centro"));
        //grafo.agregarSucursal(new Sucursal(2, "Sucursal Norte"));
        //grafo.agregarSucursal(new Sucursal(3, "Sucursal Sur"));
        //grafo.agregarSucursal(new Sucursal(4, "Sucursal Este"));

        /*grafo.agregarAristaBidireccional(1, 2, 10, 5);
        grafo.agregarAristaBidireccional(2, 3, 6, 7);
        grafo.agregarAristaBidireccional(1, 3, 20, 13);
        grafo.agregarAristaBidireccional(3, 4, 4, 3);
        
        Ruta rutaPeso = grafo.rutaMinima(1, 4, Criterio.PESO);
        System.out.println("Ruta minima por peso: " + rutaPeso);
        System.out.println("Camino sucursales (peso): " + java.util.Arrays.toString(rutaPeso.getCaminoSucursales()));
        
        Ruta rutaTiempo = grafo.rutaMinima(1, 4, Criterio.TIEMPO);
        System.out.println("Ruta minima por tiempo: " + rutaTiempo);
        System.out.println("Camino sucursales (tiempo): " + java.util.Arrays.toString(rutaTiempo.getCaminoSucursales()));*/
    }
}






















