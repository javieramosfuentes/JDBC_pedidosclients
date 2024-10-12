package domain;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.util.List;

/**
 *
 * @author javramfue
 */
public class Articulo {
    private int id_articulo;
    private String descripcion;

    public Articulo(int id_articulo, String descripcion) {
        this.id_articulo = id_articulo;
        this.descripcion = descripcion;
    }

    public Articulo(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getId_articulo() {
        return id_articulo;
    }

    public Articulo() {
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    
}
