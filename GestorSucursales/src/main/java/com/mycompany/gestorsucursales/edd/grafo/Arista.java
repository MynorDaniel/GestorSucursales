/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestorsucursales.edd.grafo;

/**
 *
 * @author mynordma
 */
public class Arista implements Comparable<Arista> {

    private final int destino;
    private final double peso;
    private final double tiempo;

    public Arista(int destino, double peso, double tiempo) {
        this.destino = destino;
        this.peso = peso;
        this.tiempo = tiempo;
    }

    public int getDestino() {
        return destino;
    }

    public double getPeso() {
        return peso;
    }

    public double getTiempo() {
        return tiempo;
    }

    @Override
    public int compareTo(Arista t) {
        return 1;
    }
}
