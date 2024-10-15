package domain;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author javramfue
 */
public class DireccionEnvio {

    private int idDireccion;
    private String numero;
    private String calle;
    private String comuna;
    private String ciudad;

    public DireccionEnvio(String ciudad, String calle, String comuna, String numero) {
        this.numero = numero;
        this.calle = calle;
        this.comuna = comuna;
        this.ciudad = ciudad;
    }

    public int getId_direccion() {
        return idDireccion;
    }

    public DireccionEnvio(int id_direccion, String ciudad, String calle, String comuna, String numero) {
        this.idDireccion = id_direccion;
        this.numero = numero;
        this.calle = calle;
        this.comuna = comuna;
        this.ciudad = ciudad;
    }

    public DireccionEnvio() {
    }

    public DireccionEnvio(String ciudad, String calle, String numero) {
        this.numero = numero;
        this.calle = calle;
        this.ciudad = ciudad;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getComuna() {
        return comuna;
    }

    public void setComuna(String comuna) {
        this.comuna = comuna;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

}