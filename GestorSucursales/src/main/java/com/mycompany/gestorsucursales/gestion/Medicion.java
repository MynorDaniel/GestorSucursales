/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestorsucursales.gestion;

import com.mycompany.gestorsucursales.edd.avl.ArbolAVL;
import com.mycompany.gestorsucursales.edd.listas.ListaEnlazadaDesordenada;
import com.mycompany.gestorsucursales.edd.listas.ListaEnlazadaOrdenada;
import com.mycompany.gestorsucursales.edd.tablahash.TablaHash;
import com.mycompany.gestorsucursales.modelos.Producto;

/**
 *
 * @author mynordma
 */
public class Medicion {

	private static final int RONDAS = 10;
	private static final int OPERACIONES = 1000;

	public static final String[] ENCABEZADOS = {
		"Estructura", "Ronda", "Insercion (ns)", "Eliminacion (ns)", "Busqueda (ns)"
	};

	public ListaEnlazadaDesordenada<FilaTabla> medirComparativo() {
		ListaEnlazadaDesordenada<FilaTabla> filas = new ListaEnlazadaDesordenada<>();

		medirListaDesordenada(filas);
		medirListaOrdenada(filas);
		medirAvl(filas);
		medirTablaHash(filas);
		agregarPromedios(filas);

		return filas;
	}

	public Object[][] toMatriz(ListaEnlazadaDesordenada<FilaTabla> filas) {
		if (filas == null || filas.getLongitud() == 0) {
			return new Object[0][0];
		}

		Object[][] datos = new Object[filas.getLongitud()][ENCABEZADOS.length];
		for (int i = 0; i < filas.getLongitud(); i++) {
			FilaTabla fila = filas.get(i);
			if (fila != null) {
				datos[i] = fila.toRow();
			}
		}
		return datos;
	}

	private void medirListaDesordenada(ListaEnlazadaDesordenada<FilaTabla> filas) {
		for (int ronda = 1; ronda <= RONDAS; ronda++) {
			ListaEnlazadaDesordenada<Producto> lista = new ListaEnlazadaDesordenada<>();
			Producto[] productos = crearProductos(ronda, OPERACIONES);

			long insercion = medirInsercionListaDesordenada(lista, productos);
			long eliminacion = medirEliminacionListaDesordenada(lista, productos);
			long busqueda = medirBusquedaListaDesordenada(lista, productos);

			filas.insertar(new FilaTabla("Lista Desordenada", ronda, insercion, eliminacion, busqueda));
		}
	}

	private void medirListaOrdenada(ListaEnlazadaDesordenada<FilaTabla> filas) {
		for (int ronda = 1; ronda <= RONDAS; ronda++) {
			ListaEnlazadaOrdenada<Producto> lista = new ListaEnlazadaOrdenada<>();
			Producto[] productos = crearProductos(ronda, OPERACIONES);

			long insercion = medirInsercionListaOrdenada(lista, productos);
			long eliminacion = medirEliminacionListaOrdenada(lista, productos);
			long busqueda = medirBusquedaListaOrdenada(lista, productos);

			filas.insertar(new FilaTabla("Lista Ordenada", ronda, insercion, eliminacion, busqueda));
		}
	}

	private void medirAvl(ListaEnlazadaDesordenada<FilaTabla> filas) {
		for (int ronda = 1; ronda <= RONDAS; ronda++) {
			ArbolAVL avl = new ArbolAVL();
			Producto[] productos = crearProductos(ronda, OPERACIONES);

			long insercion = medirInsercionAvl(avl, productos);
			long eliminacion = medirEliminacionAvl(avl, productos);
			long busqueda = medirBusquedaAvl(avl, productos);

			filas.insertar(new FilaTabla("AVL", ronda, insercion, eliminacion, busqueda));
		}
	}

	private void medirTablaHash(ListaEnlazadaDesordenada<FilaTabla> filas) {
		for (int ronda = 1; ronda <= RONDAS; ronda++) {
			TablaHash<Producto, Producto> tabla = new TablaHash<>();
			Producto[] productos = crearProductos(ronda, OPERACIONES);

			long insercion = medirInsercionTabla(tabla, productos);
			long eliminacion = medirEliminacionTabla(tabla, productos);
			long busqueda = medirBusquedaTabla(tabla, productos);

			filas.insertar(new FilaTabla("Tabla Hash", ronda, insercion, eliminacion, busqueda));
		}
	}

	private void agregarPromedios(ListaEnlazadaDesordenada<FilaTabla> filas) {
		agregarPromedioEstructura(filas, "Lista Desordenada");
		agregarPromedioEstructura(filas, "Lista Ordenada");
		agregarPromedioEstructura(filas, "AVL");
		agregarPromedioEstructura(filas, "Tabla Hash");
	}

	private void agregarPromedioEstructura(ListaEnlazadaDesordenada<FilaTabla> filas, String estructura) {
		long totalInsercion = 0;
		long totalEliminacion = 0;
		long totalBusqueda = 0;
		int conteo = 0;

		for (int i = 0; i < filas.getLongitud(); i++) {
			FilaTabla fila = filas.get(i);
			if (fila == null) {
				continue;
			}
			if (estructura.equals(fila.estructura)) {
				totalInsercion += fila.insercion;
				totalEliminacion += fila.eliminacion;
				totalBusqueda += fila.busqueda;
				conteo++;
			}
		}

		if (conteo == 0) {
			return;
		}

		long promedioInsercion = totalInsercion / conteo;
		long promedioEliminacion = totalEliminacion / conteo;
		long promedioBusqueda = totalBusqueda / conteo;
		filas.insertar(new FilaTabla(estructura + " (Promedio)", 0, promedioInsercion, promedioEliminacion, promedioBusqueda));
	}

	private long medirInsercionListaDesordenada(ListaEnlazadaDesordenada<Producto> lista, Producto[] productos) {
		long inicio = System.nanoTime();
		for (Producto producto : productos) {
			lista.insertar(producto);
		}
		return System.nanoTime() - inicio;
	}

	private long medirEliminacionListaDesordenada(ListaEnlazadaDesordenada<Producto> lista, Producto[] productos) {
		long inicio = System.nanoTime();
		for (Producto producto : productos) {
			lista.eliminar(producto);
		}
		return System.nanoTime() - inicio;
	}

	private long medirBusquedaListaDesordenada(ListaEnlazadaDesordenada<Producto> lista, Producto[] productos) {
		long inicio = System.nanoTime();
		for (Producto producto : productos) {
			lista.buscar(producto);
		}
		return System.nanoTime() - inicio;
	}

	private long medirInsercionListaOrdenada(ListaEnlazadaOrdenada<Producto> lista, Producto[] productos) {
		long inicio = System.nanoTime();
		for (Producto producto : productos) {
			lista.insertar(producto);
		}
		return System.nanoTime() - inicio;
	}

	private long medirEliminacionListaOrdenada(ListaEnlazadaOrdenada<Producto> lista, Producto[] productos) {
		long inicio = System.nanoTime();
		for (Producto producto : productos) {
			lista.eliminar(producto);
		}
		return System.nanoTime() - inicio;
	}

	private long medirBusquedaListaOrdenada(ListaEnlazadaOrdenada<Producto> lista, Producto[] productos) {
		long inicio = System.nanoTime();
		for (Producto producto : productos) {
			lista.buscar(producto);
		}
		return System.nanoTime() - inicio;
	}

	private long medirInsercionAvl(ArbolAVL avl, Producto[] productos) {
		long inicio = System.nanoTime();
		for (Producto producto : productos) {
			avl.insertar(producto);
		}
		return System.nanoTime() - inicio;
	}

	private long medirEliminacionAvl(ArbolAVL avl, Producto[] productos) {
		long inicio = System.nanoTime();
		for (Producto producto : productos) {
			avl.eliminar(producto);
		}
		return System.nanoTime() - inicio;
	}

	private long medirBusquedaAvl(ArbolAVL avl, Producto[] productos) {
		long inicio = System.nanoTime();
		for (Producto producto : productos) {
			avl.buscar(producto);
		}
		return System.nanoTime() - inicio;
	}

	private long medirInsercionTabla(TablaHash<Producto, Producto> tabla, Producto[] productos) {
		long inicio = System.nanoTime();
		for (Producto producto : productos) {
			tabla.insertar(producto, producto);
		}
		return System.nanoTime() - inicio;
	}

	private long medirEliminacionTabla(TablaHash<Producto, Producto> tabla, Producto[] productos) {
		long inicio = System.nanoTime();
		for (Producto producto : productos) {
			tabla.eliminar(producto);
		}
		return System.nanoTime() - inicio;
	}

	private long medirBusquedaTabla(TablaHash<Producto, Producto> tabla, Producto[] productos) {
		long inicio = System.nanoTime();
		for (Producto producto : productos) {
			tabla.buscar(producto);
		}
		return System.nanoTime() - inicio;
	}

	private Producto[] crearProductos(int ronda, int cantidad) {
		Producto[] productos = new Producto[cantidad];
		for (int i = 0; i < cantidad; i++) {
			Producto p = new Producto();
			p.setNombre("Producto" + ronda + "_" + i);
			p.setCodigoBarras(String.format("%02d%08d", ronda, i + 1));
			p.setCategoria("Categoria" + (i % 5));
			p.setFechaVencimiento("2030-01-" + String.format("%02d", (i % 28) + 1));
			p.setMarca("Marca" + (i % 4));
			p.setPrecio(10 + i);
			p.setStock(5 + i);
			productos[i] = p;
		}
		return productos;
	}

	public static class FilaTabla implements Comparable<FilaTabla> {

		private final String estructura;
		private final int ronda;
		private final long insercion;
		private final long eliminacion;
		private final long busqueda;

		public FilaTabla(String estructura, int ronda, long insercion, long eliminacion, long busqueda) {
			this.estructura = estructura;
			this.ronda = ronda;
			this.insercion = insercion;
			this.eliminacion = eliminacion;
			this.busqueda = busqueda;
		}

		public Object[] toRow() {
			return new Object[]{estructura, ronda, insercion, eliminacion, busqueda};
		}

		@Override
		public int compareTo(FilaTabla other) {
			if (other == null) {
				return 1;
			}
			int cmp = estructura.compareTo(other.estructura);
			if (cmp != 0) {
				return cmp;
			}
			return Integer.compare(ronda, other.ronda);
		}
	}
}
