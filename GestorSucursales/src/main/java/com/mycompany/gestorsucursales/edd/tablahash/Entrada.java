/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestorsucursales.edd.tablahash;

/**
 *
 * @author mynordma
 * @param <K>
 * @param <V>
 */
public class Entrada<K, V> {

    private final K clave;
    private V valor;

    public Entrada(K clave, V valor) {
        this.clave = clave;
        this.valor = valor;
    }

    public V getValor() {
        return valor;
    }

    public void setValor(V valor) {
        this.valor = valor;
    }

    public K getClave() {
        return clave;
    }
}
