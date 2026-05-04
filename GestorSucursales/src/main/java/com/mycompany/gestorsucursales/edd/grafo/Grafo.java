package com.mycompany.gestorsucursales.edd.grafo;

import com.mycompany.gestorsucursales.edd.listas.ListaEnlazadaDesordenada;
import com.mycompany.gestorsucursales.edd.listas.ListaEnlazadaOrdenada;
import com.mycompany.gestorsucursales.edd.tablahash.TablaHash;
import com.mycompany.gestorsucursales.excepciones.SucursalException;
import com.mycompany.gestorsucursales.modelos.Sucursal;
import java.util.Arrays;
import java.util.Objects;

public class Grafo {

    private final TablaHash<Integer, Sucursal> sucursales;
    private final TablaHash<Integer, ListaEnlazadaDesordenada<Arista>> adyacencia;

    public Grafo() {
        this.sucursales = new TablaHash<>();
        this.adyacencia = new TablaHash<>();
    }

    public void agregarSucursal(Sucursal sucursal) throws SucursalException {
        Objects.requireNonNull(sucursal, "La sucursal no puede ser null");
        if (sucursales.buscar(sucursal.getId()) != null) {
            throw new SucursalException("La sucursal con id " + sucursal.getId() + " ya existe");
        }
        sucursales.insertar(sucursal.getId(), sucursal);
        adyacencia.insertar(sucursal.getId(), new ListaEnlazadaDesordenada<>());
    }

    public boolean contieneSucursal(int id) {
        return sucursales.buscar(id) != null;
    }

    public void agregarArista(int origen, int destino, double peso, double tiempo) throws SucursalException {
        validarNodo(origen);
        validarNodo(destino);
        if (peso < 0 || tiempo < 0) {
            throw new SucursalException("Peso y tiempo deben ser >= 0");
        }
        ListaEnlazadaDesordenada<Arista> lista = adyacencia.buscar(origen);
        if (lista == null) {
            lista = new ListaEnlazadaDesordenada<>();
            adyacencia.insertar(origen, lista);
        }
        lista.insertar(new Arista(destino, peso, tiempo));
    }

    public void agregarAristaBidireccional(int origen, int destino, double peso, double tiempo) throws SucursalException {
        agregarArista(origen, destino, peso, tiempo);
        agregarArista(destino, origen, peso, tiempo);
    }

    public Ruta rutaMinima(int origen, int destino, Criterio criterio) throws SucursalException {
        validarNodo(origen);
        validarNodo(destino);

        if (origen == destino) {
            Sucursal[] sucursalesRuta = new Sucursal[]{sucursales.buscar(origen)};
            return new Ruta(new int[]{origen}, sucursalesRuta, 0.0, criterio);
        }

        DijkstraResultado resultado = dijkstra(origen, criterio);
        Integer destinoIndexCosto = resultado.indicePorId.buscar(destino);
        if (destinoIndexCosto == null) {
            return new Ruta(new int[0], new Sucursal[0], Double.POSITIVE_INFINITY, criterio);
        }
        double costo = resultado.distancias[destinoIndexCosto];
        if (Double.isInfinite(costo)) {
            return new Ruta(new int[0], new Sucursal[0], Double.POSITIVE_INFINITY, criterio);
        }

        int[] camino = reconstruirCamino(destino, resultado);
        Sucursal[] caminoSucursales = new Sucursal[camino.length];
        for (int i = 0; i < camino.length; i++) {
            caminoSucursales[i] = sucursales.buscar(camino[i]);
        }
        return new Ruta(camino, caminoSucursales, costo, criterio);
    }

    public TablaHash<Integer, Double> distanciasDesde(int origen, Criterio criterio) throws SucursalException {
        validarNodo(origen);
        DijkstraResultado resultado = dijkstra(origen, criterio);
        TablaHash<Integer, Double> salida = new TablaHash<>();
        for (int i = 0; i < resultado.ids.length; i++) {
            salida.insertar(resultado.ids[i], resultado.distancias[i]);
        }
        return salida;
    }

    private DijkstraResultado dijkstra(int origen, Criterio criterio) {
        Object[] claves = sucursales.claves();
        int[] ids = new int[claves.length];
        for (int i = 0; i < claves.length; i++) {
            ids[i] = (Integer) claves[i];
        }
        Arrays.sort(ids);

        TablaHash<Integer, Integer> indicePorId = new TablaHash<>();
        for (int i = 0; i < ids.length; i++) {
            indicePorId.insertar(ids[i], i);
        }

        double[] distancias = new double[ids.length];
        int[] predecesores = new int[ids.length];
        boolean[] visitados = new boolean[ids.length];
        Arrays.fill(distancias, Double.POSITIVE_INFINITY);
        Arrays.fill(predecesores, -1);

        Integer origenIndex = indicePorId.buscar(origen);
        if (origenIndex == null) {
            return new DijkstraResultado(ids, distancias, predecesores, indicePorId);
        }
        distancias[origenIndex] = 0.0;

        ListaEnlazadaOrdenada<NodoDistancia> cola = new ListaEnlazadaOrdenada<>();
        cola.insertar(new NodoDistancia(origenIndex, 0.0));

        while (!cola.isEmpty()) {
            NodoDistancia actual = cola.extraerPrimero();
            if (visitados[actual.indice]) {
                continue;
            }
            visitados[actual.indice] = true;

            int idActual = ids[actual.indice];
            ListaEnlazadaDesordenada<Arista> aristas = adyacencia.buscar(idActual);
            if (aristas == null) {
                continue;
            }
            for (int i = 0; i < aristas.getLongitud(); i++) {
                Arista arista = aristas.get(i);
                Integer indiceDestino = indicePorId.buscar(arista.getDestino());
                if (indiceDestino == null) {
                    continue;
                }
                double costo = actual.distancia + obtenerCosto(arista, criterio);
                if (costo < distancias[indiceDestino]) {
                    distancias[indiceDestino] = costo;
                    predecesores[indiceDestino] = actual.indice;
                    cola.insertar(new NodoDistancia(indiceDestino, costo));
                }
            }
        }

        return new DijkstraResultado(ids, distancias, predecesores, indicePorId);
    }

    private double obtenerCosto(Arista arista, Criterio criterio) {
        return criterio == Criterio.PESO ? arista.getPeso() : arista.getTiempo();
    }

    private int[] reconstruirCamino(int destino, DijkstraResultado resultado) {
        Integer destinoIndex = resultado.indicePorId.buscar(destino);
        if (destinoIndex == null) {
            return new int[0];
        }
        int[] buffer = new int[resultado.ids.length];
        int size = 0;
        int actual = destinoIndex;
        while (actual != -1) {
            buffer[size++] = resultado.ids[actual];
            actual = resultado.predecesores[actual];
        }

        int[] camino = new int[size];
        for (int i = 0; i < size; i++) {
            camino[i] = buffer[size - 1 - i];
        }
        return camino;
    }

    private void validarNodo(int id) throws SucursalException {
        if (sucursales.buscar(id) == null) {
            throw new SucursalException("Sucursal inexistente: " + id);
        }
    }

    @Override
    public String toString() {
        if (sucursales.estaVacia()) {
            return "Matriz de adyacencia vacía";
        }

        Object[] claves = sucursales.claves();
        int[] ids = new int[claves.length];
        for (int i = 0; i < claves.length; i++) {
            ids[i] = (Integer) claves[i];
        }
        Arrays.sort(ids);

        TablaHash<Integer, Integer> indicePorId = new TablaHash<>();
        for (int i = 0; i < ids.length; i++) {
            indicePorId.insertar(ids[i], i);
        }

        double[][] pesos = new double[ids.length][ids.length];
        double[][] tiempos = new double[ids.length][ids.length];
        boolean[][] existe = new boolean[ids.length][ids.length];

        for (int i = 0; i < ids.length; i++) {
            int origen = ids[i];
            ListaEnlazadaDesordenada<Arista> aristas = adyacencia.buscar(origen);
            if (aristas == null) {
                continue;
            }
            for (int j = 0; j < aristas.getLongitud(); j++) {
                Arista arista = aristas.get(j);
                Integer destinoIndex = indicePorId.buscar(arista.getDestino());
                if (destinoIndex == null) {
                    continue;
                }
                pesos[i][destinoIndex] = arista.getPeso();
                tiempos[i][destinoIndex] = arista.getTiempo();
                existe[i][destinoIndex] = true;
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append("Matriz de adyacencia (peso/tiempo)").append(System.lineSeparator());
        sb.append("    ");
        for (int id : ids) {
            sb.append(String.format("%8s", id));
        }
        sb.append(System.lineSeparator());

        for (int i = 0; i < ids.length; i++) {
            sb.append(String.format("%4s", ids[i]));
            for (int j = 0; j < ids.length; j++) {
                if (existe[i][j]) {
                    sb.append(String.format("%8s", formatoPesoTiempo(pesos[i][j], tiempos[i][j])));
                } else {
                    sb.append(String.format("%8s", "-"));
                }
            }
            sb.append(System.lineSeparator());
        }

        return sb.toString();
    }

    private String formatoPesoTiempo(double peso, double tiempo) {
        if (peso == Math.rint(peso) && tiempo == Math.rint(tiempo)) {
            return String.format("%d/%d", (long) peso, (long) tiempo);
        }
        return String.format("%.2f/%.2f", peso, tiempo);
    }

    private class NodoDistancia implements Comparable<NodoDistancia> {

        private final int indice;
        private final double distancia;

        private NodoDistancia(int indice, double distancia) {
            this.indice = indice;
            this.distancia = distancia;
        }

        @Override
        public int compareTo(NodoDistancia other) {
            return Double.compare(this.distancia, other.distancia);
        }
    }

    private class DijkstraResultado {

        private final int[] ids;
        private final double[] distancias;
        private final int[] predecesores;
        private final TablaHash<Integer, Integer> indicePorId;

        private DijkstraResultado(int[] ids, double[] distancias, int[] predecesores, TablaHash<Integer, Integer> indicePorId) {
            this.ids = ids;
            this.distancias = distancias;
            this.predecesores = predecesores;
            this.indicePorId = indicePorId;
        }
    }

    public TablaHash<Integer, Sucursal> getSucursales() {
        return sucursales;
    }
}
