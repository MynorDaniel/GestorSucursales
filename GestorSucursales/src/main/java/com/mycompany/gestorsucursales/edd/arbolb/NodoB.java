/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestorsucursales.edd.arbolb;

import com.mycompany.gestorsucursales.modelos.Producto;

/**
 *
 * @author mynordma
 */
public class NodoB {

    private int cantidadClaves;
    private final Producto[] claves;
    private final NodoB[] hijos;
    private final int d;
    private boolean hoja;

    public NodoB(int d, boolean hoja) {
        this.d = d;
        this.hoja = hoja;
        this.cantidadClaves = 0;

        this.claves = new Producto[2 * d + 1];
        this.hijos = new NodoB[2 * d + 2];
    }

    public int getCantidadClaves() {
        return cantidadClaves;
    }

    public void setCantidadClaves(int cantidadClaves) {
        this.cantidadClaves = cantidadClaves;
    }

    public Producto getClave(int i) {
        return claves[i];
    }

    public void setClave(int i, Producto producto) {
        this.claves[i] = producto;
    }

    public NodoB getHijo(int i) {
        return hijos[i];
    }

    public void setHijo(int i, NodoB hijo) {
        this.hijos[i] = hijo;
    }

    public int getD() {
        return d;
    }

    public boolean esHoja() {
        return hoja;
    }

    public void setHoja(boolean hoja) {
        this.hoja = hoja;
    }
}