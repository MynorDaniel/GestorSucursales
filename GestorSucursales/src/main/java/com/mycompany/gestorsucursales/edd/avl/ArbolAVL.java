/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestorsucursales.edd.avl;

import com.mycompany.gestorsucursales.modelos.Producto;

/**
 *
 * @author mynordma
 */
public class ArbolAVL {
    
    private NodoAVL raiz;
    
    public void insertar(Producto p){
        
    }
    
    public void eliminar(Producto p){
        
    }
    
    public Producto buscar(Producto p){
        return p;
    }
    
    private NodoAVL rotarII(NodoAVL n, NodoAVL n1){
        n.setIzquierdo(n1.getDerecho());
        n1.setDerecho(n);
        
        if(n1.getFe() == -1){
            n1.setFe(0);
            n.setFe(0);
        }else{
            n.setFe(-1);
            n.setFe(1);
        }
        
        return n1;
    }
    
    private NodoAVL rotarDD(NodoAVL n, NodoAVL n1){
        return n;
    }
    
    private NodoAVL rotarID(NodoAVL n, NodoAVL n1){
        return n;
    }
    
    private NodoAVL rotarDI(NodoAVL n, NodoAVL n1){
        return n;
    }
}
