/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestorsucursales.modelos;

import com.mycompany.gestorsucursales.edd.cola.Cola;
import com.mycompany.gestorsucursales.edd.grafo.ListaAdyacencia;

/**
 *
 * @author mynordma
 */
public class Sucursal {
    
    private int id;
    private String nombre;
    private String ubicacion;
    private int tiempoIngreso;
    private int tiempoPreparacion;
    private int intervaloDespacho;
    private Cola colaDespacho;
    
    private final ListaAdyacencia adyacentes = new ListaAdyacencia();

    public ListaAdyacencia getAdyacentes() {
        return adyacentes;
    }

    public int calcularPeso(Sucursal adyacente){
        return 3;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public int getTiempoIngreso() {
        return tiempoIngreso;
    }

    public void setTiempoIngreso(int tiempoIngreso) {
        this.tiempoIngreso = tiempoIngreso;
    }

    public int getTiempoPreparacion() {
        return tiempoPreparacion;
    }

    public void setTiempoPreparacion(int tiempoPreparacion) {
        this.tiempoPreparacion = tiempoPreparacion;
    }

    public int getIntervaloDespacho() {
        return intervaloDespacho;
    }

    public void setIntervaloDespacho(int intervaloDespacho) {
        this.intervaloDespacho = intervaloDespacho;
    }

    public Cola getColaDespacho() {
        return colaDespacho;
    }

    public void setColaDespacho(Cola colaDespacho) {
        this.colaDespacho = colaDespacho;
    }
    
    
    
}
