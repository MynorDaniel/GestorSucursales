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
public class Sucursal implements Runnable {

    private int id;
    private String nombre;
    private String ubicacion;
    private int tiempoIngreso;
    private int tiempoPreparacion;
    private int intervaloDespacho;

    private final Cola<Producto> colaEnvio;
    private final Cola<Producto> colaTraspaso;
    private final Cola<Producto> colaRecepcion;

    private final ListaEnlazadaOrdenada<Producto> listaOrdenada;
    private final ListaEnlazadaDesordenada<Producto> listaDesordenada;
    private final ArbolAVL avl;
    private final ArbolB arbolB;
    private final TablaHash<Producto, Producto> tablaHash;
    private final ArbolBMas arbolBMas;

    private final ListaEnlazadaDesordenada<EntradaListener> listeners;
    private Thread hiloProcesamiento;
    private volatile boolean activo;

    public Sucursal() {
        listaOrdenada = new ListaEnlazadaOrdenada<>();
        listaDesordenada = new ListaEnlazadaDesordenada<>();
        avl = new ArbolAVL();
        arbolB = new ArbolB(2);
        tablaHash = new TablaHash<>();
        arbolBMas = new ArbolBMas(2);

        listeners = new ListaEnlazadaDesordenada<>();
        activo = false;

        colaEnvio = new Cola<>();
        colaTraspaso = new Cola<>();
        colaRecepcion = new Cola<>();
    }

    public void agregarProducto(Producto p) throws ProductoException {
        if (tablaHash.buscar(p) != null) {
            throw new ProductoException("Codigo de barras repetido: " + p.getCodigoBarras());
        }
        listaOrdenada.insertar(p);
        listaDesordenada.insertar(p);
        avl.insertar(p);
        tablaHash.insertar(p, p);
        arbolB.insertar(p);
        arbolBMas.insertar(p);

        //colaRecepcion.insertar(p);
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

    public Cola<Producto> getColaEnvio() {
        return colaEnvio;
    }

    public Cola<Producto> getColaTraspaso() {
        return colaTraspaso;
    }

    public Cola<Producto> getColaRecepcion() {
        return colaRecepcion;
    }

    public synchronized void agregarListener(ProductoEventoListener listener) {
        if (listener == null || listeners.buscar(new EntradaListener(listener)) != null) {
            return;
        }
        listeners.insertar(new EntradaListener(listener));
    }

    public synchronized void removerListener(ProductoEventoListener listener) {
        if (listener == null) {
            return;
        }
        listeners.eliminar(new EntradaListener(listener));
    }

    public synchronized void iniciarProcesamiento() {
        if (activo && hiloProcesamiento != null && hiloProcesamiento.isAlive()) {
            return;
        }
        activo = true;
        hiloProcesamiento = new Thread(this, "Sucursal-" + id);
        hiloProcesamiento.setDaemon(true);
        hiloProcesamiento.start();
    }

    public synchronized void detenerProcesamiento() {
        activo = false;
        if (hiloProcesamiento != null) {
            hiloProcesamiento.interrupt();
        }
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

    @Override
    public void run() {
        while (activo) {
            boolean trabajo = false;
            trabajo |= procesarEntrada();
            trabajo |= procesarTraspaso();
            trabajo |= procesarSalida();

            if (!trabajo) {
                sleep(1);
            }
        }
    }

    private boolean procesarEntrada() {
        if (colaRecepcion.getSize() > 0) {
            Producto p = colaRecepcion.quitar();
            p.setEstado(Estado.EN_TRANSITO);
            notificar(p, Estado.EN_TRANSITO, "Ingreso en sucursal " + id);
            sleep(tiempoIngreso);

            if (p.esDestinoFinal()) {
                p.setEstado(Estado.DISPONIBLE);
                notificar(p, Estado.DISPONIBLE, "Producto llegó a destino en sucursal " + id);
            } else {
                colaTraspaso.insertar(p);
                notificar(p, Estado.EN_TRANSITO, "Producto en preparación de traspaso en sucursal " + id);
            }
            return true;
        }
        return false;
    }

    private boolean procesarTraspaso() {
        if (colaTraspaso.getSize() > 0) {
            Producto p = colaTraspaso.quitar();
            notificar(p, p.getEstado(), "Preparando envío en sucursal " + id);
            sleep(tiempoPreparacion);

            colaEnvio.insertar(p);
            notificar(p, p.getEstado(), "Producto listo para despacho en sucursal " + id);
            return true;
        }
        return false;
    }

    private boolean procesarSalida() {
        if (colaEnvio.getSize() > 0) {
            Producto p = colaEnvio.quitar();

            sleep(intervaloDespacho);

            Sucursal siguiente = p.siguiente();
            p.avanzar();
            notificar(p, p.getEstado(), "Despacho desde sucursal " + id + " hacia sucursal " + siguiente.getId());
            siguiente.getColaRecepcion().insertar(p);
            return true;
        }
        return false;
    }

    private void sleep(int segundos) {
        try {
            Thread.sleep(segundos * 1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private void notificar(Producto producto, Estado estado, String detalle) {
        ListaEnlazadaDesordenada<EntradaListener> copia = new ListaEnlazadaDesordenada<>();
        synchronized (this) {
            for (int i = 0; i < listeners.getLongitud(); i++) {
                EntradaListener actual = listeners.get(i);
                if (actual != null) {
                    copia.insertar(actual);
                }
            }
        }
        for (int i = 0; i < copia.getLongitud(); i++) {
            EntradaListener entry = copia.get(i);
            if (entry != null && entry.listener != null) {
                entry.listener.onEvento(this, producto, estado, detalle);
            }
        }
    }

    private class EntradaListener implements Comparable<EntradaListener> {

        private final ProductoEventoListener listener;

        private EntradaListener(ProductoEventoListener listener) {
            this.listener = listener;
        }

        @Override
        public int compareTo(EntradaListener other) {
            if (other == null || other.listener == null) {
                return 1;
            }
            if (listener == null) {
                return -1;
            }
            if (listener == other.listener) {
                return 0;
            }
            return Integer.compare(System.identityHashCode(listener), System.identityHashCode(other.listener));
        }
    }

}
