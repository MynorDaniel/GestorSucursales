/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.gestorsucursales;

import com.mycompany.gestorsucursales.edd.grafo.Grafo;
import com.mycompany.gestorsucursales.modelos.Sucursal;
import com.mycompany.gestorsucursales.vistas.Ventana;

/**
 *
 * @author mynordma
 */
public class GestorSucursales {

    public static void main(String[] args) {
        //Ventana ventana = new Ventana();
        //ventana.setVisible(true);
        test();
    }
    
    private static void test(){
        Grafo grafo = new Grafo();

        Sucursal central = new Sucursal();
        central.setId(1);
        Sucursal zona1   = new Sucursal();
        zona1.setId(2);
        Sucursal xela    = new Sucursal();
        xela.setId(3);
        Sucursal antigua = new Sucursal();
        antigua.setId(4);
        Sucursal peten   = new Sucursal();
        peten.setId(5);

        grafo.agregarConexion(central, zona1);
        grafo.agregarConexion(central, xela);
        grafo.agregarConexion(central, antigua);

        grafo.agregarConexion(zona1, xela);
        grafo.agregarConexion(zona1, peten);

        grafo.agregarConexion(xela, antigua);
        grafo.agregarConexion(antigua, peten);
        grafo.agregarConexion(peten, central);

        System.out.println("GRAFO DE SUCURSALES");
        System.out.println(grafo);
    }
}
