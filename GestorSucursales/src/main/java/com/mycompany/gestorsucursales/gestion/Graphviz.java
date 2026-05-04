/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestorsucursales.gestion;

import com.mycompany.gestorsucursales.edd.arbolb.ArbolB;
import com.mycompany.gestorsucursales.edd.arbolb.NodoB;
import com.mycompany.gestorsucursales.edd.arbolbmas.ArbolBMas;
import com.mycompany.gestorsucursales.edd.arbolbmas.NodoBMas;
import com.mycompany.gestorsucursales.edd.avl.ArbolAVL;
import com.mycompany.gestorsucursales.edd.avl.NodoAVL;
import com.mycompany.gestorsucursales.edd.cola.Cola;
import com.mycompany.gestorsucursales.edd.grafo.Arista;
import com.mycompany.gestorsucursales.edd.grafo.Grafo;
import com.mycompany.gestorsucursales.edd.listas.ListaEnlazadaDesordenada;
import com.mycompany.gestorsucursales.edd.listas.ListaEnlazadaOrdenada;
import com.mycompany.gestorsucursales.edd.listas.NodoLista;
import com.mycompany.gestorsucursales.edd.tablahash.Entrada;
import com.mycompany.gestorsucursales.edd.tablahash.TablaHash;
import com.mycompany.gestorsucursales.modelos.Producto;
import com.mycompany.gestorsucursales.modelos.Sucursal;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author mynordma
 */
public class Graphviz {

    public String generarImagenListaOrdenada(ListaEnlazadaOrdenada lista, String ruta, String nombreImagen) {
        String dot = generarDotLista(lista == null ? null : lista.getPrimero(), "Lista Ordenada");
        return renderizar(dot, ruta, nombreImagen);
    }

    public String generarImagenListaDesordenada(ListaEnlazadaDesordenada lista, String ruta, String nombreImagen) {
        String dot = generarDotLista(lista == null ? null : lista.getPrimero(), "Lista Desordenada");
        return renderizar(dot, ruta, nombreImagen);
    }

    public String generarImagenArbolAVL(ArbolAVL arbol, String ruta, String nombreImagen) {
        NodoAVL raiz = arbol == null ? null : arbol.getRaiz();
        StringBuilder sb = new StringBuilder();
        sb.append("digraph AVL {\nnode [shape=box];\n");
        if (raiz != null) {
            generarDotAVL(raiz, sb);
        }
        sb.append("}\n");
        return renderizar(sb.toString(), ruta, nombreImagen);
    }

    public String generarImagenTablaHash(TablaHash tabla, String ruta, String nombreImagen) {
        StringBuilder sb = new StringBuilder();
        sb.append("digraph Hash {\nnode [shape=record];\n");
        if (tabla != null) {
            Entrada[] entradas = tabla.getTabla();
            for (int i = 0; i < entradas.length; i++) {
                String label;
                if (entradas[i] == null) {
                    label = i + ": null";
                } else {
                    label = i + ": " + escape(String.valueOf(entradas[i].getClave()));
                }
                sb.append("slot").append(i).append(" [label=\"")
                        .append(label)
                        .append("\"];\n");
            }
        }
        sb.append("}\n");
        return renderizar(sb.toString(), ruta, nombreImagen);
    }

    public String generarImagenArbolB(ArbolB arbol, String ruta, String nombreImagen) {
        StringBuilder sb = new StringBuilder();
        sb.append("digraph ArbolB {\nnode [shape=record];\n");
        if (arbol != null && arbol.getRaiz() != null) {
            Set<Integer> visitados = new HashSet<>();
            generarDotArbolB(arbol.getRaiz(), sb, visitados);
        }
        sb.append("}\n");
        return renderizar(sb.toString(), ruta, nombreImagen);
    }

    public String generarImagenArbolBMas(ArbolBMas arbol, String ruta, String nombreImagen) {
        StringBuilder sb = new StringBuilder();
        sb.append("digraph ArbolBMas {\nnode [shape=record];\n");
        if (arbol != null && arbol.getRaiz() != null) {
            Set<Integer> visitados = new HashSet<>();
            generarDotArbolBMas(arbol.getRaiz(), sb, visitados);
        }
        sb.append("}\n");
        return renderizar(sb.toString(), ruta, nombreImagen);
    }

    public String generarImagenGrafo(Grafo grafo, String ruta, String nombreImagen) {
        StringBuilder sb = new StringBuilder();
        sb.append("digraph Grafo {\nnode [shape=ellipse];\n");
        if (grafo != null) {
            TablaHash<Integer, Sucursal> sucursales = grafo.getSucursales();
            Object[] claves = sucursales.claves();
            for (Object clave : claves) {
                int id = (Integer) clave;
                Sucursal s = sucursales.buscar(id);
                String label = s == null ? String.valueOf(id) : id + "\\n" + escape(s.getNombre());
                sb.append("n").append(id).append(" [label=\"").append(label).append("\"];\n");
            }

            TablaHash<Integer, ListaEnlazadaDesordenada<Arista>> ady = grafo.getAdyacencia();
            for (Object clave : claves) {
                int origen = (Integer) clave;
                ListaEnlazadaDesordenada<Arista> lista = ady.buscar(origen);
                if (lista == null) {
                    continue;
                }
                for (int i = 0; i < lista.getLongitud(); i++) {
                    Arista arista = lista.get(i);
                    if (arista == null) {
                        continue;
                    }
                    sb.append("n").append(origen)
                            .append(" -> n").append(arista.getDestino())
                            .append(" [label=\"")
                            .append("t=").append(arista.getTiempo())
                            .append(",p=").append(arista.getPeso())
                            .append("\"];\n");
                }
            }
        }
        sb.append("}\n");
        return renderizar(sb.toString(), ruta, nombreImagen);
    }

    public String generarImagenCola(Cola cola, String ruta, String nombreImagen) {
        NodoLista frente = cola == null ? null : cola.getFrente();
        String dot = generarDotLista(frente, "Cola");
        return renderizar(dot, ruta, nombreImagen);
    }

    private String generarDotLista(NodoLista primero, String titulo) {
        StringBuilder sb = new StringBuilder();
        sb.append("digraph Lista {\nrankdir=LR;\nnode [shape=box];\nlabel=\"")
                .append(escape(titulo)).append("\";\nlabelloc=top;\n");
        int index = 0;
        NodoLista actual = primero;
        while (actual != null) {
            String nodeId = "n" + index;
            String label = escape(String.valueOf(actual.getDato()));
            sb.append(nodeId).append(" [label=\"").append(label).append("\"];\n");
            if (actual.getSiguiente() != null) {
                sb.append(nodeId).append(" -> n").append(index + 1).append(";\n");
            }
            actual = actual.getSiguiente();
            index++;
        }
        sb.append("}\n");
        return sb.toString();
    }

    private void generarDotAVL(NodoAVL nodo, StringBuilder sb) {
        if (nodo == null) {
            return;
        }
        int id = System.identityHashCode(nodo);
        Producto p = nodo.getProducto();
        String label = p == null ? "null" : escape(p.getNombre() + "\n" + p.getCodigoBarras());
        sb.append("n").append(id).append(" [label=\"").append(label).append("\"];\n");
        if (nodo.getIzquierdo() != null) {
            sb.append("n").append(id).append(" -> n").append(System.identityHashCode(nodo.getIzquierdo()))
                    .append(" [label=\"L\"];\n");
            generarDotAVL(nodo.getIzquierdo(), sb);
        }
        if (nodo.getDerecho() != null) {
            sb.append("n").append(id).append(" -> n").append(System.identityHashCode(nodo.getDerecho()))
                    .append(" [label=\"R\"];\n");
            generarDotAVL(nodo.getDerecho(), sb);
        }
    }

    private void generarDotArbolB(NodoB nodo, StringBuilder sb, Set<Integer> visitados) {
        if (nodo == null) {
            return;
        }
        int id = System.identityHashCode(nodo);
        if (!visitados.add(id)) {
            return;
        }
        sb.append("n").append(id).append(" [label=\"")
                .append(escape(nodo.formatearNodo()))
                .append("\"];\n");
        if (!nodo.esHoja()) {
            for (int i = 0; i <= nodo.getCantidadClaves(); i++) {
                NodoB hijo = nodo.getHijo(i);
                if (hijo != null) {
                    sb.append("n").append(id).append(" -> n")
                            .append(System.identityHashCode(hijo)).append(";\n");
                    generarDotArbolB(hijo, sb, visitados);
                }
            }
        }
    }

    private void generarDotArbolBMas(NodoBMas nodo, StringBuilder sb, Set<Integer> visitados) {
        if (nodo == null) {
            return;
        }
        int id = System.identityHashCode(nodo);
        if (!visitados.add(id)) {
            return;
        }

        sb.append("n").append(id).append(" [label=\"")
                .append(escape(formatearNodoBMas(nodo)))
                .append("\"];\n");

        if (!nodo.esHoja()) {
            for (int i = 0; i <= nodo.getCantidadClaves(); i++) {
                NodoBMas hijo = nodo.getHijo(i);
                if (hijo != null) {
                    sb.append("n").append(id).append(" -> n")
                            .append(System.identityHashCode(hijo)).append(";\n");
                    generarDotArbolBMas(hijo, sb, visitados);
                }
            }
        } else if (nodo.getSiguiente() != null) {
            sb.append("n").append(id).append(" -> n")
                    .append(System.identityHashCode(nodo.getSiguiente()))
                    .append(" [style=dashed,label=\"sig\"];\n");
            generarDotArbolBMas(nodo.getSiguiente(), sb, visitados);
        }
    }

    private String formatearNodoBMas(NodoBMas nodo) {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < nodo.getCantidadClaves(); i++) {
            if (nodo.esHoja()) {
                Producto p = nodo.getProducto(i);
                if (p != null) {
                    sb.append(p.getCategoria()).append("(").append(p.getCodigoBarras()).append(")");
                } else {
                    sb.append("null");
                }
            } else {
                sb.append(nodo.getClave(i));
            }
            if (i < nodo.getCantidadClaves() - 1) {
                sb.append(" | ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    private String renderizar(String dot, String ruta, String nombreImagen) {
        if (dot == null) {
            return null;
        }
        String baseDir = (ruta == null || ruta.isBlank()) ? System.getProperty("user.dir") : ruta;
        String nombre = (nombreImagen == null || nombreImagen.isBlank()) ? "estructura" : nombreImagen;
        if (!nombre.endsWith(".svg")) {
            nombre += ".svg";
        }
        File dir = new File(baseDir);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        Path dotPath = new File(dir, nombre.replace(".svg", ".dot")).toPath();
        Path svgPath = new File(dir, nombre).toPath();

        try (BufferedWriter writer = Files.newBufferedWriter(dotPath, StandardCharsets.UTF_8)) {
            writer.write(dot);
        } catch (IOException e) {
            throw new RuntimeException("Error al escribir archivo DOT", e);
        }

        ProcessBuilder pb = new ProcessBuilder("dot", "-Tsvg", dotPath.toAbsolutePath().toString(),
                "-o", svgPath.toAbsolutePath().toString());
        pb.redirectErrorStream(true);
        try {
            Process proceso = pb.start();
            int exitCode = proceso.waitFor();
            if (exitCode != 0) {
                throw new RuntimeException("Graphviz falló con código " + exitCode);
            }
        } catch (IOException | InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Error al ejecutar Graphviz", e);
        }

        return svgPath.toAbsolutePath().toString();
    }

    private String escape(String texto) {
        if (texto == null) {
            return "null";
        }
        return texto.replace("\\", "\\\\")
                .replace("\"", "\\\"")
                .replace("\n", "\\n");
    }
}