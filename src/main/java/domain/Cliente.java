/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;
import java.util.List;

/**
 *
 * @author javramfue
 */
public class Cliente {
    private int idCliente;
    private float saldo;
    private float limiteCredito;
    private float descuento;
    private int direccionEnvio;

    public Cliente(int id_cliente, float saldo, float limiteCredito, float descuento, int direccionEnvio) {
        this.idCliente = id_cliente;
        this.saldo = saldo;
        this.limiteCredito = limiteCredito;
        this.descuento = descuento;
        this.direccionEnvio = direccionEnvio;
    }

    public Cliente() {
    }

    public float getSaldo() {
        return saldo;
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }

    public float getLimiteCredito() {
        return limiteCredito;
    }

    public void setLimiteCredito(float limiteCredito) {
        this.limiteCredito = limiteCredito;
    }

    public float getDescuento() {
        return descuento;
    }

    public void setDescuento(float descuento) {
        this.descuento = descuento;
    }

    public int getDireccionEnvio() {
        return direccionEnvio;
    }

    public void setDireccionEnvio(int direccionEnvio) {
        this.direccionEnvio = direccionEnvio;
    }

    public int getId_cliente() {
        return idCliente;
    }

    
    
    
}