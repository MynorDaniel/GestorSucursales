/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestorsucursales.edd.tablahash;

import com.mycompany.gestorsucursales.modelos.Producto;

/**
 *
 * @author mynordma
 */
public class TablaHash {
    
    private Producto[] t;
    private double FC;
    private final double FC_IDEAL = 0.8;
    private final double R = 0.6180334;
    private int n;
    private int m;
    
    public TablaHash(){
        m = 16;
        t = new Producto[m];
        n = 0;
        FC = n / m;
    }
    
    /**
     * Función hash con método de la multiplicación
     * @param x
     * @return indice de x en t
     */
    private int h(int x) {
        double rx = x * R;
        double d = rx - Math.floor(rx);
        return (int)(m * d);
    }
    
    public Producto buscar(Producto p){
        int x = Integer.parseInt(p.getCodigoBarras());
        return t[h(x)];
    }
    
    public void insertar(Producto p){
        int x = Integer.parseInt(p.getCodigoBarras());
        t[h(x)] = p;
    }
    
    public void eliminar(Producto p){
        int x = Integer.parseInt(p.getCodigoBarras());
        t[h(x)] = null;
    }
}
