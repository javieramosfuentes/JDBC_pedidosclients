/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;
import java.util.Date;
import java.util.List;
/**
 *
 * @author javramfue
 */
public class Pedido {
    
    private int idPedido;
    private Date fecha;
    private int direccionEnvio;
    private int idCliente;
    
    //private int articulos;

    public Pedido(int id_pedido, Date fecha, int direccionEnvio, int idCliente) {
        this.idPedido = id_pedido;
        this.fecha = fecha;
        this.direccionEnvio = direccionEnvio;
        this.idCliente = idCliente;
        //this.articulos = articulos;
    }
    
        public Pedido(int id_pedido) {
        this.idPedido = id_pedido;
    }
    
    public Pedido(int id_pedido, java.sql.Date fecha, int direccionEnvio, int idCliente) {
        this.idPedido = id_pedido;
        this.fecha = fecha;
        this.direccionEnvio = direccionEnvio;
        this.idCliente = idCliente;
        //this.articulos = articulos;
    }


    public Pedido() {
    }

    public int getId_pedido() {
        return idPedido;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getDireccionEnvio() {
        return direccionEnvio;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public void setDireccionEnvio(int direccionEnvio) {
        this.direccionEnvio = direccionEnvio;
    }
/*
    public int getArticulos() {
        return articulos;
    }

    public void setArticulos(int articulos) {
        this.articulos = articulos;
    }
*/
    
    
}