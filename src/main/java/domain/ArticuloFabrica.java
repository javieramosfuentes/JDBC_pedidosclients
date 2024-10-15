/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

/**
 *
 * @author javramfue
 */
public class ArticuloFabrica {
    private int existencias;
    private int idArticulo;
    private int idFabrica;
    private int precio;

    public ArticuloFabrica(int precio,int existencias, int idArticulo, int idFabrica) {
        this.existencias = existencias;
        this.idArticulo = idArticulo;
        this.idFabrica = idFabrica;
        this.precio = precio;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public ArticuloFabrica() {
    }

    public int getExistencias() {
        return existencias;
    }

    public void setExistencias(int existencias) {
        this.existencias = existencias;
    }

    public int getIdArticulo() {
        return idArticulo;
    }

    public int getIdFabrica() {
        return idFabrica;
    }
    
    
}