/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestorsucursales.modelos;

import com.mycompany.gestorsucursales.excepciones.ProductoException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Objects;

/**
 *
 * @author mynordma
 */
public class Producto implements Comparable<Producto> {

    private String nombre;
    private String codigoBarras;
    private String categoria;
    private String fechaVencimiento;
    private String marca;
    private double precio;
    private int stock;
    
    private Estado estado;
    
    private Sucursal[] ruta;
    private int indiceRuta;

    public Sucursal siguiente() {
        return ruta[indiceRuta + 1];
    }

    public void avanzar() {
        indiceRuta++;
    }

    public Sucursal[] getRuta() {
        return ruta;
    }

    public void setRuta(Sucursal[] ruta) {
        this.ruta = ruta;
    }

    public int getIndiceRuta() {
        return indiceRuta;
    }

    public void setIndiceRuta(int indiceRuta) {
        this.indiceRuta = indiceRuta;
    }

    public boolean esDestinoFinal() {
        return indiceRuta == ruta.length - 1;
    }

    public void validar() throws ProductoException {
        if (!esFechaISO(fechaVencimiento)) {
            throw new ProductoException("Error al crear producto: formato de fecha invalido");
        }
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new ProductoException("Error al crear producto: nombre vacio");
        }
        if (codigoBarras == null || codigoBarras.trim().isEmpty()) {
            throw new ProductoException("Error al crear producto: codigo de barras vacio");
        }
        if(codigoBarras.trim().length() != 10){
            throw new ProductoException("El codigo debe tener 10 caracteres");
        }
        if (categoria == null || categoria.trim().isEmpty()) {
            throw new ProductoException("Error al crear producto: categoria vacia");
        }
        if (marca == null || marca.trim().isEmpty()) {
            throw new ProductoException("Error al crear producto: marca vacia");
        }
        if (precio < 0 || stock < 0) {
            throw new ProductoException("Error al crear producto: precio o stock negativo");
        }
    }

    private boolean esFechaISO(String texto) {
        if (texto == null) {
            return false;
        }

        try {
            LocalDate.parse(texto, DateTimeFormatter.ISO_LOCAL_DATE);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    public Producto() {
        estado = Estado.DISPONIBLE;
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

    public void setCodigoBarras(String codigoBarras) {
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

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Producto: " + nombre + ", " + codigoBarras + ", " + categoria + ", " + fechaVencimiento + ", " + marca + ", " + precio + ", " + stock;
    }

    @Override
    public int hashCode() {
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

    @Override
    public int compareTo(Producto other) {
        if (other == null) {
            return 1;
        }
        return this.getCodigoBarras().compareTo(other.getCodigoBarras());
    }
}
