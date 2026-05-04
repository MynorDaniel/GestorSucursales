/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.gestorsucursales.vistas;

import com.mycompany.gestorsucursales.edd.grafo.Grafo;
import com.mycompany.gestorsucursales.edd.listas.ListaEnlazadaDesordenada;
import com.mycompany.gestorsucursales.excepciones.ProductoException;
import com.mycompany.gestorsucursales.excepciones.SucursalException;
import com.mycompany.gestorsucursales.modelos.Producto;
import com.mycompany.gestorsucursales.modelos.Sucursal;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

/**
 *
 * @author mynordma
 */
public class Ventana extends javax.swing.JFrame {

    private final Grafo grafo;

    public Ventana() {
        initComponents();
        setLocationRelativeTo(null);
        setTitle("Gestor de Sucursales");
        grafo = new Grafo();
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
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        graphvizItem = new javax.swing.JMenuItem();

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

        jMenuItem4.setText("Productos");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        csvProductos.add(jMenuItem4);

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
            .addGroup(layout.createSequentialGroup()
                .addGap(272, 272, 272)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 525, Short.MAX_VALUE)
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
            consola.append("Sucursal agregada");
            consola.append(grafo.toString());
            consola.append("\n");
        } catch (SucursalException ex) {
            consola.append(ex.getMessage());
            consola.append("\n");
        } catch (NullPointerException e) {
            System.out.println("x");
        }
    }//GEN-LAST:event_agregarSucursalItemActionPerformed

    private void eliminarSucursalItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminarSucursalItemActionPerformed

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

            consola.append("Producto agregado");
            consola.append("\n");
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
                consola.append("Producto eliminado");
                consola.append("\n");
            } else {
                consola.append("Producto no encontrado");
                consola.append("\n");
            }

        }
    }//GEN-LAST:event_eliminarProductoItemActionPerformed

    private void categoriaItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_categoriaItemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_categoriaItemActionPerformed

    private void nombreItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nombreItemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nombreItemActionPerformed

    private void fechaItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fechaItemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fechaItemActionPerformed

    private void codigoItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_codigoItemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_codigoItemActionPerformed

    private void listarPorNombreItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_listarPorNombreItemActionPerformed
        try {
            SucursalIDForm idForm = new SucursalIDForm(this, true);
            idForm.setVisible(true);

            Integer sucursalID = idForm.getSucursalID();

            if (sucursalID != null) {
                Sucursal s = grafo.getSucursales().buscar(sucursalID);
                String inorder = s.getAvl().inorder();

                consola.append(inorder);
                consola.append("\n");

            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No hay productos para los parametros ingresados");
        }
    }//GEN-LAST:event_listarPorNombreItemActionPerformed

    private void compararBusquedasMenuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_compararBusquedasMenuMouseClicked
        System.out.println("hfalshf");
    }//GEN-LAST:event_compararBusquedasMenuMouseClicked

    private void csvSucursalesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_csvSucursalesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_csvSucursalesActionPerformed

    private void csvConexionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_csvConexionesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_csvConexionesActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void graphvizItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_graphvizItemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_graphvizItemActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem agregarProductoItem;
    private javax.swing.JMenuItem agregarSucursalItem;
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
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JMenuItem listarPorNombreItem;
    private javax.swing.JMenuItem nombreItem;
    private javax.swing.JMenu productosMenu;
    private javax.swing.JMenu sucursalesMenu;
    // End of variables declaration//GEN-END:variables
}
