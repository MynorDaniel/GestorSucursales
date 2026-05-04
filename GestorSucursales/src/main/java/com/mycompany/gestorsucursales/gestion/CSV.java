/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestorsucursales.gestion;

import com.mycompany.gestorsucursales.edd.grafo.Grafo;
import com.mycompany.gestorsucursales.edd.pila.Pila;
import com.mycompany.gestorsucursales.excepciones.ProductoException;
import com.mycompany.gestorsucursales.excepciones.SucursalException;
import com.mycompany.gestorsucursales.modelos.Producto;
import com.mycompany.gestorsucursales.modelos.Sucursal;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

/**
 *
 * @author mynordma
 */
public class CSV {

    private final Pila<Producto> pilaProductos;
    private final Pila<Sucursal> pilaSucursales;

    private final StringBuilder log;

    public CSV() {
        pilaProductos = new Pila<>();
        pilaSucursales = new Pila<>();

        log = new StringBuilder();
    }

    public void cargarSucursales(Grafo grafo, String ruta) {
        try (CSVReader reader = new CSVReader(new FileReader(ruta))) {
            String[] datos;
            int numeroLinea = 0;

            reader.readNext();
            numeroLinea++;

            while ((datos = reader.readNext()) != null) {
                numeroLinea++;

                if (filaVacia(datos)) {
                    continue;
                }

                if (datos.length != 6) {
                    log.append("Error en linea ").append(numeroLinea).append(": cantidad de campos invalida").append("\n");
                    continue;
                }

                try {
                    int id = Integer.parseInt(datos[0]);
                    String nombre = datos[1];
                    String ubicacion = datos[2];
                    int tiempoIngreso = Integer.parseInt(datos[3]);
                    int tiempoTraspaso = Integer.parseInt(datos[4]);
                    int tiempoDespacho = Integer.parseInt(datos[5]);

                    Sucursal sucursal = new Sucursal();
                    sucursal.setId(id);
                    sucursal.setIntervaloDespacho(tiempoDespacho);
                    sucursal.setNombre(nombre);
                    sucursal.setTiempoIngreso(tiempoIngreso);
                    sucursal.setTiempoPreparacion(tiempoTraspaso);
                    sucursal.setUbicacion(ubicacion);
                    sucursal.validar();
                    grafo.agregarSucursal(sucursal);
                    log.append("Sucursal ").append(sucursal.getId()).append(" agregada al grafo").append("\n");
                } catch (NumberFormatException e) {
                    log.append("Error en linea ").append(numeroLinea).append(": numero invalido").append("\n");
                } catch (SucursalException ex) {
                    log.append("Error en linea ").append(numeroLinea).append(": ").append(ex.getMessage()).append("\n");
                }
            }

        } catch (IOException e) {
            log.append("Error al leer el archivo: ").append(e.getMessage()).append("\n");
        } catch (CsvValidationException ex) {
            System.getLogger(CSV.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        
        guardarLog();
    }

    public void cargarConexiones(Grafo grafo, String ruta) {
        try (CSVReader reader = new CSVReader(new FileReader(ruta))) {
            String[] datos;
            int numeroLinea = 0;

            reader.readNext();
            numeroLinea++;

            while ((datos = reader.readNext()) != null) {
                numeroLinea++;

                if (filaVacia(datos)) {
                    continue;
                }

                if (datos.length != 4) {
                    log.append("Error en linea ").append(numeroLinea).append(": cantidad de campos invalida").append("\n");
                    continue;
                }

                try {
                    int origen = Integer.parseInt(datos[0]);
                    int destino = Integer.parseInt(datos[1]);
                    double tiempo = Double.parseDouble(datos[2]);
                    double costo = Double.parseDouble(datos[3]);

                    if (tiempo < 0 || costo < 0) {
                        throw new SucursalException("Costo o tiempo negativo");
                    }

                    grafo.agregarArista(origen, destino, costo, tiempo);
                    log.append("Conexion ").append(origen).append(" -> ").append(destino).append(" agregada al grafo").append("\n");
                } catch (NumberFormatException e) {
                    log.append("Error en linea ").append(numeroLinea).append(": numero invalido").append("\n");
                } catch (SucursalException ex) {
                    log.append("Error en linea ").append(numeroLinea).append(": ").append(ex.getMessage()).append("\n");
                }
            }

        } catch (IOException e) {
            log.append("Error al leer el archivo: ").append(e.getMessage()).append("\n");
        } catch (CsvValidationException ex) {
            System.getLogger(CSV.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        
        guardarLog();
    }

    public void cargarProductos(Grafo grafo, String ruta) {
        try (CSVReader reader = new CSVReader(new FileReader(ruta))) {
            String[] datos;
            int numeroLinea = 0;

            reader.readNext();
            numeroLinea++;

            while ((datos = reader.readNext()) != null) {
                numeroLinea++;

                if (filaVacia(datos)) {
                    continue;
                }

                if (datos.length != 8) {
                    log.append("Error en linea ").append(numeroLinea).append(": cantidad de campos invalida").append("\n");
                    continue;
                }

                try {
                    int sucursalID = Integer.parseInt(datos[0]);
                    String nombre = datos[1];
                    String codigoBarras = datos[2];
                    String categoria = datos[3];
                    String fechaCaducidad = datos[4];
                    String marca = datos[5];
                    double precio = Double.parseDouble(datos[6]);
                    int stock = Integer.parseInt(datos[7]);

                    Producto p = new Producto();
                    p.setCategoria(categoria);
                    p.setCodigoBarras(codigoBarras);
                    p.setFechaVencimiento(fechaCaducidad);
                    p.setMarca(marca);
                    p.setNombre(nombre);
                    p.setPrecio(precio);
                    p.setStock(stock);
                    
                    Sucursal sucursal = grafo.getSucursales().buscar(sucursalID);
                    
                    if(sucursal == null) throw new SucursalException("Sucursal no existe: " + sucursalID);
                    
                    p.validar();
                    
                    sucursal.agregarProducto(p);

                    log.append("Producto ").append(codigoBarras).append(" agregado a la sucursal ").append(sucursalID).append("\n");
                } catch (NumberFormatException e) {
                    log.append("Error en linea ").append(numeroLinea).append(": numero invalido").append("\n");
                } catch (ProductoException | SucursalException ex) {
                    log.append("Error en linea ").append(numeroLinea).append(": ").append(ex.getMessage()).append("\n");
                }
            }

        } catch (IOException e) {
            log.append("Error al leer el archivo: ").append(e.getMessage()).append("\n");
        } catch (CsvValidationException ex) {
            System.getLogger(CSV.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        
        guardarLog();
    }

    public String getLog() {
        return log.toString();
    }

    private void guardarLog() {
        String ruta = System.getProperty("user.dir");
        File archivo = new File(ruta, "errores.log");

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivo, false))) {
            bw.write(log.toString());
            bw.newLine();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private boolean filaVacia(String[] datos) {
        if (datos == null || datos.length == 0) {
            return true;
        }
        for (String dato : datos) {
            if (dato != null && !dato.trim().isEmpty()) {
                return false;
            }
        }
        return true;
    }
}
