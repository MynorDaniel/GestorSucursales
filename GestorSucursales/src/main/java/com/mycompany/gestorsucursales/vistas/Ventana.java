/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.gestorsucursales.vistas;

import com.mycompany.gestorsucursales.edd.cola.Cola;
import com.mycompany.gestorsucursales.edd.listas.ListaEnlazadaDesordenada;
import com.mycompany.gestorsucursales.edd.listas.ListaEnlazadaOrdenada;
import com.mycompany.gestorsucursales.edd.pila.Pila;
import com.mycompany.gestorsucursales.excepciones.ProductoException;
import com.mycompany.gestorsucursales.modelos.Producto;

/**
 *
 * @author mynordma
 */
public class Ventana extends javax.swing.JFrame {
    
    private final ListaEnlazadaOrdenada listaOrdenada;
    private final ListaEnlazadaDesordenada listaDesordenada;
    private final Pila pila;
    private final Cola cola;
    
    public Ventana() {
        initComponents();
        setLocationRelativeTo(null);
        listaOrdenada = new ListaEnlazadaOrdenada();
        listaDesordenada = new ListaEnlazadaDesordenada();
        pila = new Pila();
        cola = new Cola();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        insertarBtn = new javax.swing.JButton();
        tNombre = new javax.swing.JTextField();
        tCodigoBarras = new javax.swing.JTextField();
        tCategoria = new javax.swing.JTextField();
        tFechaVencimiento = new javax.swing.JTextField();
        tMarca = new javax.swing.JTextField();
        tPrecio = new javax.swing.JTextField();
        tStock = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        consola = new javax.swing.JTextPane();
        jLabel9 = new javax.swing.JLabel();
        eCodigoBarras = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        bCodigoBarras = new javax.swing.JTextField();
        eliminarBtn = new javax.swing.JButton();
        buscarBtn = new javax.swing.JButton();
        listarBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Nuevo producto");

        jLabel2.setText("Nombre");

        jLabel3.setText("Codigo barras");

        jLabel4.setText("Categoria");

        jLabel5.setText("Fecha vencimiento");

        jLabel6.setText("Marca");

        jLabel7.setText("Precio");

        jLabel8.setText("Stock");

        insertarBtn.setText("Agregar");
        insertarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                insertarBtnActionPerformed(evt);
            }
        });

        jScrollPane2.setViewportView(consola);

        jLabel9.setText("Eliminar");

        jLabel10.setText("Buscar");

        eliminarBtn.setText("Eliminar");
        eliminarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eliminarBtnActionPerformed(evt);
            }
        });

        buscarBtn.setText("Buscar");
        buscarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscarBtnActionPerformed(evt);
            }
        });

        listarBtn.setText("Listar");
        listarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                listarBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 852, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8))
                        .addGap(46, 46, 46)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(insertarBtn)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(listarBtn))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(tPrecio, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 258, Short.MAX_VALUE)
                                .addComponent(tMarca, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(tFechaVencimiento)
                                .addComponent(tCategoria, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(tCodigoBarras, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(tNombre, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(tStock)))
                        .addGap(25, 25, 25)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(eCodigoBarras, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(eliminarBtn))
                        .addGap(58, 58, 58)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(buscarBtn)
                            .addComponent(jLabel10)
                            .addComponent(bCodigoBarras, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(28, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(insertarBtn)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10)
                    .addComponent(listarBtn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(tNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(eCodigoBarras, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bCodigoBarras, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(tCodigoBarras, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(eliminarBtn)
                    .addComponent(buscarBtn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(tCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(tFechaVencimiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(tMarca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(tPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(tStock, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 493, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void insertarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_insertarBtnActionPerformed
        
        try {
            Producto p = new Producto();
            p.setCategoria(tCategoria.getText());
            p.setCodigoBarras(tCodigoBarras.getText());
            p.setFechaVencimiento(tFechaVencimiento.getText());
            p.setMarca(tMarca.getText());
            p.setNombre(tNombre.getText());
            p.setPrecio(Double.parseDouble(tPrecio.getText()));
            p.setStock(Integer.parseInt(tStock.getText()));
            
            listaOrdenada.insertar(p);
            listaDesordenada.insertar(p);
            pila.insertar(p);
            cola.insertar(p);
            imprimirEDD();
            
        } catch (ProductoException e) {
            consola.setText("Error al insertar producto: " + e.getMessage());
        }
    }//GEN-LAST:event_insertarBtnActionPerformed

    private void eliminarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminarBtnActionPerformed
        try {
            Producto p = new Producto();
            p.setCodigoBarras(eCodigoBarras.getText());
            
            listaOrdenada.eliminar(p);
            listaDesordenada.eliminar(p);
            pila.quitar();
            cola.quitar();
            imprimirEDD();
        } catch (ProductoException ex) {
            consola.setText("Error al eliminar producto: " + ex.getMessage());
        }
    }//GEN-LAST:event_eliminarBtnActionPerformed

    private void buscarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscarBtnActionPerformed
        try {
            Producto p = new Producto();
            p.setCodigoBarras(bCodigoBarras.getText());
            
            p = listaOrdenada.buscar(p);
            
            consola.setText(p == null ? "No encontrado" : p.toString());
        } catch (ProductoException ex) {
            consola.setText("Error al buscar producto: " + ex.getMessage());
        }
    }//GEN-LAST:event_buscarBtnActionPerformed

    private void listarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_listarBtnActionPerformed
        imprimirEDD();
    }//GEN-LAST:event_listarBtnActionPerformed

    private void imprimirEDD(){
        StringBuilder sb = new StringBuilder();
        sb.append("Lista ordenada: ")
                .append("\n").append(listaOrdenada.toString())
                .append("\n").append("Lista desordenada: ")
                .append("\n").append(listaDesordenada.toString())
                .append("\n").append("Pila: ")
                .append("\n").append(pila.toString())
                .append("\n").append("Cola: ")
                .append("\n").append(cola.toString());
        
        consola.setText(sb.toString());
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField bCodigoBarras;
    private javax.swing.JButton buscarBtn;
    private javax.swing.JTextPane consola;
    private javax.swing.JTextField eCodigoBarras;
    private javax.swing.JButton eliminarBtn;
    private javax.swing.JButton insertarBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton listarBtn;
    private javax.swing.JTextField tCategoria;
    private javax.swing.JTextField tCodigoBarras;
    private javax.swing.JTextField tFechaVencimiento;
    private javax.swing.JTextField tMarca;
    private javax.swing.JTextField tNombre;
    private javax.swing.JTextField tPrecio;
    private javax.swing.JTextField tStock;
    // End of variables declaration//GEN-END:variables
}
