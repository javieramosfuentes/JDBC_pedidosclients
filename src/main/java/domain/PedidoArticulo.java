package domain;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author javramfue
 */
public class PedidoArticulo {
    private int cantidad;
    private int idPedido;
    private int idArticulo;

    public PedidoArticulo(int cantidad, int idPedido, int idArticulo) {
        this.cantidad = cantidad;
        this.idPedido = idPedido;
        this.idArticulo = idArticulo;
    }

    public PedidoArticulo() {
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getIdPedido() {
        return idPedido;
    }

    public int getIdArticulo() {
        return idArticulo;
    }
    
    
}