/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestorsucursales.modelos;

import com.mycompany.gestorsucursales.excepciones.ProductoException;
import java.util.Objects;

/**
 *
 * @author mynordma
 */
public class Producto {
    
    private String nombre;
    private String codigoBarras;
    private String categoria;
    private String fechaVencimiento;
    private String marca;
    private double precio;
    private int stock;

    public Producto() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCodigoBarras() {
        return codigoBarras;
    }

    public void setCodigoBarras(String codigoBarras) throws ProductoException {
        
        if(codigoBarras.trim().length() != 10) throw new ProductoException("Código de barras debe tener 10 carácteres");
        
        this.codigoBarras = codigoBarras;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(String fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
    
    @Override
    public String toString(){
        return "Producto: " + nombre + ", " + codigoBarras + ", " + categoria + ", " + fechaVencimiento + ", " + marca + ", " + precio + ", " + stock;
    }
    
    @Override
    public int hashCode(){
        return Integer.parseInt(codigoBarras);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Producto other = (Producto) obj;
        return Objects.equals(this.codigoBarras, other.codigoBarras);
    }
}
