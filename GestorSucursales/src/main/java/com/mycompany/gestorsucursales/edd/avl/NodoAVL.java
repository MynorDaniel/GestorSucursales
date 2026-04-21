/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestorsucursales.edd.avl;

import com.mycompany.gestorsucursales.modelos.Producto;

/**
 *
 * @author mynordma
 */
public class NodoAVL {
    
    private Producto producto;
    private NodoAVL izquierdo;
    private NodoAVL derecho;
    private int altura;

    NodoAVL(Producto producto) {
            this.producto = producto;
            this.altura = 1;
    }

    public Producto getProducto() {
            return producto;
    }

    public void setProducto(Producto producto) {
            this.producto = producto;
    }

    public NodoAVL getIzquierdo() {
            return izquierdo;
    }

    public void setIzquierdo(NodoAVL izquierdo) {
            this.izquierdo = izquierdo;
    }

    public NodoAVL getDerecho() {
            return derecho;
    }

    public void setDerecho(NodoAVL derecho) {
            this.derecho = derecho;
    }

    public int getAltura() {
            return altura;
    }

    public void setAltura(int altura) {
            this.altura = altura;
    }
}