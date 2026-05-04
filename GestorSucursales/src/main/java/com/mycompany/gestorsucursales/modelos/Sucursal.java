/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestorsucursales.modelos;

import com.mycompany.gestorsucursales.edd.arbolb.ArbolB;
import com.mycompany.gestorsucursales.edd.arbolbmas.ArbolBMas;
import com.mycompany.gestorsucursales.edd.avl.ArbolAVL;
import com.mycompany.gestorsucursales.edd.cola.Cola;
import com.mycompany.gestorsucursales.edd.listas.ListaEnlazadaDesordenada;
import com.mycompany.gestorsucursales.edd.listas.ListaEnlazadaOrdenada;
import com.mycompany.gestorsucursales.edd.tablahash.TablaHash;
import com.mycompany.gestorsucursales.excepciones.ProductoException;
import com.mycompany.gestorsucursales.excepciones.SucursalException;

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

    private final ListaEnlazadaOrdenada<Producto> listaOrdenada;
    private final ListaEnlazadaDesordenada<Producto> listaDesordenada;
    private final ArbolAVL avl;
    private final ArbolB arbolB;
    private final TablaHash<Producto, Producto> tablaHash;
    private final ArbolBMas arbolBMas;

    public Sucursal() {
        listaOrdenada = new ListaEnlazadaOrdenada<>();
        listaDesordenada = new ListaEnlazadaDesordenada<>();
        avl = new ArbolAVL();
        arbolB = new ArbolB(2);
        tablaHash = new TablaHash<>();
        arbolBMas = new ArbolBMas(2);
    }

    public void agregarProducto(Producto p) throws ProductoException {
        if(tablaHash.buscar(p) != null) throw new ProductoException("Codigo de barras repetido: " + p.getCodigoBarras());
        listaOrdenada.insertar(p);
        listaDesordenada.insertar(p);
        avl.insertar(p);
        tablaHash.insertar(p, p);
        arbolB.insertar(p);
        arbolBMas.insertar(p);
    }

    public void eliminarProducto(Producto p) {
        listaOrdenada.eliminar(p);
        listaDesordenada.eliminar(p);
        avl.eliminar(p);
        tablaHash.eliminar(p);
        arbolB.eliminar(p);
        arbolBMas.eliminar(p);
    }

    public ListaEnlazadaOrdenada getListaOrdenada() {
        return listaOrdenada;
    }

    public ListaEnlazadaDesordenada getListaDesordenada() {
        return listaDesordenada;
    }

    public ArbolAVL getAvl() {
        return avl;
    }

    public ArbolB getArbolB() {
        return arbolB;
    }

    public TablaHash<Producto, Producto> getTablaHash() {
        return tablaHash;
    }

    public ArbolBMas getArbolBMas() {
        return arbolBMas;
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

    @Override
    public String toString() {
        return "Sucursal{"
                + "id=" + id
                + ", nombre='" + nombre + '\''
                + ", ubicacion='" + ubicacion + '\''
                + '}';
    }

    public void validar() throws SucursalException {
        if (id < 0) {
            throw new SucursalException("Error al crear sucursal: ID negativo");
        }
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new SucursalException("Error al crear sucursal: nombre vacio");
        }
        if (ubicacion == null || ubicacion.trim().isEmpty()) {
            throw new SucursalException("Error al crear sucursal: ubicación vacia");
        }
        if (tiempoIngreso < 0 || tiempoPreparacion < 0 || intervaloDespacho < 0) {
            throw new SucursalException("Error al crear sucursal: tiempo negativo");
        }
    }

}
