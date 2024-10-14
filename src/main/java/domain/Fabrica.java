/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

/**
 *
 * @author javramfue
 */
public class Fabrica {
    private int idFabrica;
    private String telefono;
    private int articulos_provistos;

    public Fabrica(int id_fabrica, String telefono, int articulos_provistos) {
        this.idFabrica = id_fabrica;
        this.telefono = telefono;
        this.articulos_provistos = articulos_provistos;
    }

    public int getId_fabrica() {
        return idFabrica;
    }

    public int getIdFabrica() {
        return idFabrica;
    }

    public int getArticulos_provistos() {
        return articulos_provistos;
    }

    public void setArticulos_provistos(int articulos_provistos) {
        this.articulos_provistos = articulos_provistos;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Fabrica(int id_fabrica, String telefono) {
        this.idFabrica = id_fabrica;
        this.telefono = telefono;
    }

    public Fabrica() {
    }

    public Fabrica(String telefono, int articulos_provistos) {
        this.telefono = telefono;
        this.articulos_provistos = articulos_provistos;
    }
    
    
}