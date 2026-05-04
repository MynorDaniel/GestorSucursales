/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.gestorsucursales.vistas;

import com.mycompany.gestorsucursales.edd.grafo.Criterio;
import com.mycompany.gestorsucursales.gestion.CSV;
import com.mycompany.gestorsucursales.edd.grafo.Grafo;
import com.mycompany.gestorsucursales.edd.grafo.Ruta;
import com.mycompany.gestorsucursales.edd.listas.ListaEnlazadaDesordenada;
import com.mycompany.gestorsucursales.excepciones.ProductoException;
import com.mycompany.gestorsucursales.excepciones.SucursalException;
import com.mycompany.gestorsucursales.gestion.Graphviz;
import com.mycompany.gestorsucursales.gestion.Medicion;
import com.mycompany.gestorsucursales.gestion.Transferencia;
import com.mycompany.gestorsucursales.modelos.Producto;
import com.mycompany.gestorsucursales.modelos.ProductoEventoListener;
import com.mycompany.gestorsucursales.modelos.Sucursal;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.geom.AffineTransform;
import java.io.File;
import java.net.URI;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import org.apache.batik.swing.JSVGCanvas;
import org.apache.batik.swing.svg.SVGDocumentLoaderAdapter;
import org.apache.batik.swing.svg.SVGDocumentLoaderEvent;

/**
 *
 * @author mynordma
 */
public class Ventana extends javax.swing.JFrame {

    private final Grafo grafo;

    private final Transferencia transferencia;
    private final ProductoEventoListener eventoListener;

    public Ventana() {
        initComponents();
        setLocationRelativeTo(null);
        setTitle("Gestor de Sucursales");
        grafo = new Grafo();
        transferencia = new Transferencia();
        eventoListener = (sucursal, producto, estado, detalle) -> appendConsola(detalle + " | Estado: " + estado + " | Producto: " + producto.getCodigoBarras());
        jScrollPane2.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuItem1 = new javax.swing.JMenuItem();
        jScrollPane2 = new javax.swing.JScrollPane();
        consola = new javax.swing.JTextArea();
        jMenuBar1 = new javax.swing.JMenuBar();
        sucursalesMenu = new javax.swing.JMenu();
        agregarSucursalItem = new javax.swing.JMenuItem();
        eliminarSucursalItem = new javax.swing.JMenuItem();
        productosMenu = new javax.swing.JMenu();
        agregarProductoItem = new javax.swing.JMenuItem();
        eliminarProductoItem = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        nombreItem = new javax.swing.JMenuItem();
        codigoItem = new javax.swing.JMenuItem();
        categoriaItem = new javax.swing.JMenuItem();
        fechaItem = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        listarPorNombreItem = new javax.swing.JMenuItem();
        compararBusquedasMenu = new javax.swing.JMenu();
        csvProductos = new javax.swing.JMenu();
        csvSucursales = new javax.swing.JMenuItem();
        csvConexiones = new javax.swing.JMenuItem();
        cargarProductosItem = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        graphvizItem = new javax.swing.JMenuItem();
        transferirBtn = new javax.swing.JMenu();

        jMenuItem1.setText("jMenuItem1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        consola.setEditable(false);
        consola.setColumns(20);
        consola.setRows(5);
        jScrollPane2.setViewportView(consola);

        sucursalesMenu.setText("Sucursales");

        agregarSucursalItem.setText("Agregar");
        agregarSucursalItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agregarSucursalItemActionPerformed(evt);
            }
        });
        sucursalesMenu.add(agregarSucursalItem);

        eliminarSucursalItem.setText("Eliminar");
        eliminarSucursalItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eliminarSucursalItemActionPerformed(evt);
            }
        });
        sucursalesMenu.add(eliminarSucursalItem);

        jMenuBar1.add(sucursalesMenu);

        productosMenu.setText("Productos");

        agregarProductoItem.setText("Agregar");
        agregarProductoItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agregarProductoItemActionPerformed(evt);
            }
        });
        productosMenu.add(agregarProductoItem);

        eliminarProductoItem.setText("Eliminar");
        eliminarProductoItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eliminarProductoItemActionPerformed(evt);
            }
        });
        productosMenu.add(eliminarProductoItem);

        jMenuBar1.add(productosMenu);

        jMenu3.setText("Buscar Productos");

        nombreItem.setText("Por nombre");
        nombreItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nombreItemActionPerformed(evt);
            }
        });
        jMenu3.add(nombreItem);

        codigoItem.setText("Por codigo");
        codigoItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                codigoItemActionPerformed(evt);
            }
        });
        jMenu3.add(codigoItem);

        categoriaItem.setText("Por categoria");
        categoriaItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                categoriaItemActionPerformed(evt);
            }
        });
        jMenu3.add(categoriaItem);

        fechaItem.setText("Por fecha");
        fechaItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fechaItemActionPerformed(evt);
            }
        });
        jMenu3.add(fechaItem);

        jMenuBar1.add(jMenu3);

        jMenu4.setText("Listar");

        listarPorNombreItem.setText("Por nombre");
        listarPorNombreItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                listarPorNombreItemActionPerformed(evt);
            }
        });
        jMenu4.add(listarPorNombreItem);

        jMenuBar1.add(jMenu4);

        compararBusquedasMenu.setText("Comparar Búsquedas");
        compararBusquedasMenu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                compararBusquedasMenuMouseClicked(evt);
            }
        });
        jMenuBar1.add(compararBusquedasMenu);

        csvProductos.setText("Cargar CSV");

        csvSucursales.setText("Sucursales");
        csvSucursales.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                csvSucursalesActionPerformed(evt);
            }
        });
        csvProductos.add(csvSucursales);

        csvConexiones.setText("Conexiones");
        csvConexiones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                csvConexionesActionPerformed(evt);
            }
        });
        csvProductos.add(csvConexiones);

        cargarProductosItem.setText("Productos");
        cargarProductosItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cargarProductosItemActionPerformed(evt);
            }
        });
        csvProductos.add(cargarProductosItem);

        jMenuBar1.add(csvProductos);

        jMenu1.setText("Visualizar");

        graphvizItem.setText("Graphviz");
        graphvizItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                graphvizItemActionPerformed(evt);
            }
        });
        jMenu1.add(graphvizItem);

        jMenuBar1.add(jMenu1);

        transferirBtn.setText("Transferir Producto");
        transferirBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                transferirBtnMouseClicked(evt);
            }
        });
        jMenuBar1.add(transferirBtn);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 949, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(56, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 741, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void agregarSucursalItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agregarSucursalItemActionPerformed
        try {
            AgregarSucursalForm sucursalForm = new AgregarSucursalForm(this, true);
            sucursalForm.setVisible(true);
            Sucursal sucursal = sucursalForm.getSucursal();
            grafo.agregarSucursal(sucursal);
            registrarSucursal(sucursal);
            appendConsola("Sucursal agregada");
            appendConsola(grafo.toString());
        } catch (SucursalException ex) {
            appendConsola(ex.getMessage());
        } catch (NullPointerException e) {
            System.out.println("x");
        }
    }//GEN-LAST:event_agregarSucursalItemActionPerformed

    private void eliminarSucursalItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminarSucursalItemActionPerformed
        try {
            SucursalIDForm idForm = new SucursalIDForm(this, true);
            idForm.setVisible(true);

            Integer sucursalID = idForm.getSucursalID();

            if (sucursalID != null) {
                grafo.eliminarSucursal(sucursalID);
                appendConsola("Sucursal eliminada: " + sucursalID);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }//GEN-LAST:event_eliminarSucursalItemActionPerformed

    private void agregarProductoItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agregarProductoItemActionPerformed
        try {
            AgregarProductoForm productoForm = new AgregarProductoForm(this, true);
            productoForm.setVisible(true);
            Producto p = productoForm.getP();
            Sucursal s = grafo.getSucursales().buscar(productoForm.getSucursalID());

            if (s == null) {
                throw new NullPointerException("Sucursal no existe");
            }

            s.agregarProducto(p);

            appendConsola("Producto agregado");
        } catch (NullPointerException | ProductoException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

    }//GEN-LAST:event_agregarProductoItemActionPerformed

    private void eliminarProductoItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminarProductoItemActionPerformed
        ProductoIDForm idForm = new ProductoIDForm(this, true);
        idForm.setVisible(true);

        String parametro = idForm.getParametro();
        Integer sucursalID = idForm.getSucursalID();

        if (parametro != null && sucursalID != null) {
            Sucursal s = grafo.getSucursales().buscar(sucursalID);
            Producto pTemporal = new Producto();
            pTemporal.setCodigoBarras(parametro);

            Producto p = s.getTablaHash().buscar(pTemporal);

            if (p != null) {
                s.eliminarProducto(p);
                appendConsola("Producto eliminado");
            } else {
                appendConsola("Producto no encontrado");
            }

        }
    }//GEN-LAST:event_eliminarProductoItemActionPerformed

    private void categoriaItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_categoriaItemActionPerformed
        ProductoIDForm idForm = new ProductoIDForm(this, true);
        idForm.setVisible(true);

        String parametro = idForm.getParametro();
        Integer sucursalID = idForm.getSucursalID();

        if (parametro != null && sucursalID != null) {
            Sucursal s = grafo.getSucursales().buscar(sucursalID);
            ListaEnlazadaDesordenada<Producto> productos = s.getArbolBMas().buscarPorCategoria(parametro);

            appendConsola(productos.toString());

        }
    }//GEN-LAST:event_categoriaItemActionPerformed

    private void nombreItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nombreItemActionPerformed
        ProductoIDForm idForm = new ProductoIDForm(this, true);
        idForm.setVisible(true);

        String parametro = idForm.getParametro();
        Integer sucursalID = idForm.getSucursalID();

        if (parametro != null && sucursalID != null) {
            Sucursal s = grafo.getSucursales().buscar(sucursalID);
            Producto p = new Producto();
            p.setNombre(parametro);
            p.setCodigoBarras("0000000000");
            ListaEnlazadaDesordenada<Producto> productos = s.getAvl().buscar(p);

            appendConsola(productos.toString());

        }
    }//GEN-LAST:event_nombreItemActionPerformed

    private void fechaItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fechaItemActionPerformed
        ProductoIDForm idForm = new ProductoIDForm(this, true);
        idForm.setVisible(true);

        String parametro = idForm.getParametro();
        Integer sucursalID = idForm.getSucursalID();

        if (parametro != null && sucursalID != null) {
            Sucursal s = grafo.getSucursales().buscar(sucursalID);
            Producto p = new Producto();
            p.setFechaVencimiento(parametro);
            p.setCodigoBarras("0000000000");
            ListaEnlazadaDesordenada<Producto> productos = s.getArbolB().buscar(p);

            appendConsola(productos.toString());

        }
    }//GEN-LAST:event_fechaItemActionPerformed

    private void codigoItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_codigoItemActionPerformed
        ProductoIDForm idForm = new ProductoIDForm(this, true);
        idForm.setVisible(true);

        String parametro = idForm.getParametro();
        Integer sucursalID = idForm.getSucursalID();

        if (parametro != null && sucursalID != null) {
            Sucursal s = grafo.getSucursales().buscar(sucursalID);
            Producto p = new Producto();
            p.setCodigoBarras(parametro);
            Producto productoEncontrado = s.getTablaHash().buscar(p);

            appendConsola(productoEncontrado.toString());

        }
    }//GEN-LAST:event_codigoItemActionPerformed

    private void listarPorNombreItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_listarPorNombreItemActionPerformed
        try {
            SucursalIDForm idForm = new SucursalIDForm(this, true);
            idForm.setVisible(true);

            Integer sucursalID = idForm.getSucursalID();

            if (sucursalID != null) {
                Sucursal s = grafo.getSucursales().buscar(sucursalID);
                String inorder = s.getAvl().inorder();

                appendConsola(inorder);

            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No hay productos para los parametros ingresados");
        }
    }//GEN-LAST:event_listarPorNombreItemActionPerformed

    private void compararBusquedasMenuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_compararBusquedasMenuMouseClicked
        Medicion medicion = new Medicion();
        ListaEnlazadaDesordenada<Medicion.FilaTabla> filas = medicion.medirComparativo();

        Object[][] datos = medicion.toMatriz(filas);
        String[] columnas = Medicion.ENCABEZADOS;

        JTable tabla = new JTable(datos, columnas);
        JScrollPane scroll = new JScrollPane(tabla);

        JDialog dialogo = new JDialog(this, "Comparación de búsquedas", true);
        dialogo.getContentPane().add(scroll);
        dialogo.setSize(800, 400);
        dialogo.setLocationRelativeTo(this);
        dialogo.setVisible(true);
    }//GEN-LAST:event_compararBusquedasMenuMouseClicked

    private void csvSucursalesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_csvSucursalesActionPerformed
        String ruta = seleccionarArchivoCSV();

        if (ruta != null) {
            CSV csv = new CSV();
            csv.cargarSucursales(grafo, ruta);
            appendConsola(csv.getLog());
            appendConsola(grafo.toString());
            registrarSucursalesExistentes();
        }
    }//GEN-LAST:event_csvSucursalesActionPerformed

    private void csvConexionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_csvConexionesActionPerformed
        String ruta = seleccionarArchivoCSV();

        if (ruta != null) {
            CSV csv = new CSV();
            csv.cargarConexiones(grafo, ruta);
            appendConsola(csv.getLog());
            appendConsola(grafo.toString());
        }
    }//GEN-LAST:event_csvConexionesActionPerformed

    private void cargarProductosItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cargarProductosItemActionPerformed
        String ruta = seleccionarArchivoCSV();

        if (ruta != null) {
            CSV csv = new CSV();
            csv.cargarProductos(grafo, ruta);
            appendConsola(csv.getLog());
            appendConsola(grafo.toString());
        }
    }//GEN-LAST:event_cargarProductosItemActionPerformed

    private void graphvizItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_graphvizItemActionPerformed
        String[] opciones = {
            "Grafo",
            "Lista Ordenada",
            "Lista Desordenada",
            "AVL",
            "Tabla Hash",
            "Arbol B",
            "Arbol B+",
            "Cola Recepcion",
            "Cola Traspaso",
            "Cola Envio"
        };

        String seleccion = (String) JOptionPane.showInputDialog(this,
                "Selecciona la estructura a visualizar",
                "Graphviz",
                JOptionPane.QUESTION_MESSAGE,
                null,
                opciones,
                opciones[0]);

        if (seleccion == null) {
            return;
        }

        Graphviz graphviz = new Graphviz();
        String baseDir = new File("imagenes").getAbsolutePath();
        String rutaSvg;

        if ("Grafo".equals(seleccion)) {
            rutaSvg = graphviz.generarImagenGrafo(grafo, baseDir, "grafo");
        } else {
            SucursalIDForm idForm = new SucursalIDForm(this, true);
            idForm.setVisible(true);
            Integer sucursalId = idForm.getSucursalID();
            if (sucursalId == null) {
                return;
            }
            Sucursal sucursal = grafo.getSucursales().buscar(sucursalId);
            if (sucursal == null) {
                JOptionPane.showMessageDialog(this, "Sucursal no existe");
                return;
            }
            String nombreArchivo = seleccion.replace(" ", "_").toLowerCase() + "_" + sucursalId;

            switch (seleccion) {
                case "Lista Ordenada" ->
                    rutaSvg = graphviz.generarImagenListaOrdenada(sucursal.getListaOrdenada(), baseDir, nombreArchivo);
                case "Lista Desordenada" ->
                    rutaSvg = graphviz.generarImagenListaDesordenada(sucursal.getListaDesordenada(), baseDir, nombreArchivo);
                case "AVL" ->
                    rutaSvg = graphviz.generarImagenArbolAVL(sucursal.getAvl(), baseDir, nombreArchivo);
                case "Tabla Hash" ->
                    rutaSvg = graphviz.generarImagenTablaHash(sucursal.getTablaHash(), baseDir, nombreArchivo);
                case "Arbol B" ->
                    rutaSvg = graphviz.generarImagenArbolB(sucursal.getArbolB(), baseDir, nombreArchivo);
                case "Arbol B+" ->
                    rutaSvg = graphviz.generarImagenArbolBMas(sucursal.getArbolBMas(), baseDir, nombreArchivo);
                case "Cola Recepcion" ->
                    rutaSvg = graphviz.generarImagenCola(sucursal.getColaRecepcion(), baseDir, nombreArchivo);
                case "Cola Traspaso" ->
                    rutaSvg = graphviz.generarImagenCola(sucursal.getColaTraspaso(), baseDir, nombreArchivo);
                case "Cola Envio" ->
                    rutaSvg = graphviz.generarImagenCola(sucursal.getColaEnvio(), baseDir, nombreArchivo);
                default ->
                    rutaSvg = null;
            }
        }

        if (rutaSvg == null) {
            JOptionPane.showMessageDialog(this, "No se pudo generar la imagen");
            return;
        }

        mostrarSvgDialog(rutaSvg, "Graphviz - " + seleccion);
    }//GEN-LAST:event_graphvizItemActionPerformed

    private void mostrarSvgDialog(String rutaSvg, String titulo) {
        SwingUtilities.invokeLater(() -> {
            File archivo = new File(rutaSvg);
            if (!archivo.exists()) {
                JOptionPane.showMessageDialog(this, "No se encontró la imagen generada");
                return;
            }

            JDialog dialogo = new JDialog(this, titulo, false);
            dialogo.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialogo.setLayout(new BorderLayout());

            JSVGCanvas canvas = new JSVGCanvas();
            canvas.addSVGDocumentLoaderListener(new SVGDocumentLoaderAdapter() {
                @Override
                public void documentLoadingFailed(SVGDocumentLoaderEvent e) {
                    System.out.println(e);
                }
            });
            URI uri = archivo.toURI();
            canvas.setURI(uri.toString());

            JScrollPane scroll = new JScrollPane(canvas);
            dialogo.add(scroll, BorderLayout.CENTER);

            JPanel controles = new JPanel(new FlowLayout(FlowLayout.LEFT));
            JButton zoomIn = new JButton("+");
            JButton zoomOut = new JButton("-");
            final double[] zoom = {1.0};

            zoomIn.addActionListener(e -> {
                zoom[0] *= 1.25;
                canvas.setRenderingTransform(AffineTransform.getScaleInstance(zoom[0], zoom[0]));
            });
            zoomOut.addActionListener(e -> {
                zoom[0] /= 1.25;
                canvas.setRenderingTransform(AffineTransform.getScaleInstance(zoom[0], zoom[0]));
            });

            controles.add(zoomIn);
            controles.add(zoomOut);
            dialogo.add(controles, BorderLayout.NORTH);

            dialogo.setSize(900, 600);
            dialogo.setLocationRelativeTo(this);
            dialogo.setVisible(true);
        });
    }

    private void transferirBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_transferirBtnMouseClicked
        ProductoIDForm productoForm = new ProductoIDForm(this, true);
        productoForm.setTitle("Origen");
        productoForm.setVisible(true);

        String codigo = productoForm.getParametro();
        Integer origenId = productoForm.getSucursalID();

        if (codigo == null || origenId == null) {
            return;
        }

        Sucursal origen = grafo.getSucursales().buscar(origenId);
        if (origen == null) {
            JOptionPane.showMessageDialog(null, "Sucursal origen no existe");
            return;
        }

        SucursalIDForm destinoForm = new SucursalIDForm(this, true);
        destinoForm.setTitle("Destino");
        destinoForm.setVisible(true);
        Integer destinoId = destinoForm.getSucursalID();
        if (destinoId == null) {
            return;
        }

        Sucursal destino = grafo.getSucursales().buscar(destinoId);
        if (destino == null) {
            JOptionPane.showMessageDialog(null, "Sucursal destino no existe");
            return;
        }

        Criterio criterio = seleccionarCriterio();
        if (criterio == null) {
            return;
        }

        Producto pTemporal = new Producto();
        pTemporal.setCodigoBarras(codigo);

        try {
            Ruta ruta = transferencia.transferir(grafo, origen, destino, criterio, pTemporal);
            for (Sucursal sucursal : ruta.getCaminoSucursales()) {
                registrarSucursal(sucursal);
            }
            appendConsola("Transferencia iniciada: " + ruta.toString());
        } catch (SucursalException | ProductoException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }//GEN-LAST:event_transferirBtnMouseClicked

    private String seleccionarArchivoCSV() {
        JFileChooser chooser = new JFileChooser();

        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

        FileNameExtensionFilter filtro
                = new FileNameExtensionFilter("Archivos CSV (*.csv)", "csv");
        chooser.setFileFilter(filtro);

        chooser.setAcceptAllFileFilterUsed(false);

        int resultado = chooser.showOpenDialog(null);

        if (resultado == JFileChooser.APPROVE_OPTION) {
            File archivo = chooser.getSelectedFile();

            if (!archivo.getName().toLowerCase().endsWith(".csv")) {
                return null;
            }

            return archivo.getAbsolutePath();
        }

        return null;
    }

    private void registrarSucursal(Sucursal sucursal) {
        if (sucursal == null) {
            return;
        }
        sucursal.agregarListener(eventoListener);
    }

    private void registrarSucursalesExistentes() {
        Object[] claves = grafo.getSucursales().claves();
        for (Object clave : claves) {
            Sucursal s = grafo.getSucursales().buscar((Integer) clave);
            registrarSucursal(s);
        }
    }

    private Criterio seleccionarCriterio() {
        Object[] opciones = {"Tiempo", "Costo"};
        int seleccion = JOptionPane.showOptionDialog(this, "Selecciona el criterio", "Criterio",
                JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);
        if (seleccion == JOptionPane.CLOSED_OPTION) {
            return null;
        }
        return seleccion == 0 ? Criterio.TIEMPO : Criterio.PESO;
    }

    private void appendConsola(String texto) {
        SwingUtilities.invokeLater(() -> {
            consola.append(texto);
            consola.append("\n");
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem agregarProductoItem;
    private javax.swing.JMenuItem agregarSucursalItem;
    private javax.swing.JMenuItem cargarProductosItem;
    private javax.swing.JMenuItem categoriaItem;
    private javax.swing.JMenuItem codigoItem;
    private javax.swing.JMenu compararBusquedasMenu;
    private javax.swing.JTextArea consola;
    private javax.swing.JMenuItem csvConexiones;
    private javax.swing.JMenu csvProductos;
    private javax.swing.JMenuItem csvSucursales;
    private javax.swing.JMenuItem eliminarProductoItem;
    private javax.swing.JMenuItem eliminarSucursalItem;
    private javax.swing.JMenuItem fechaItem;
    private javax.swing.JMenuItem graphvizItem;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JMenuItem listarPorNombreItem;
    private javax.swing.JMenuItem nombreItem;
    private javax.swing.JMenu productosMenu;
    private javax.swing.JMenu sucursalesMenu;
    private javax.swing.JMenu transferirBtn;
    // End of variables declaration//GEN-END:variables
}
