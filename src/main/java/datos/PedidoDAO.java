/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package datos;

import static datos.Conexion.getConnection;
import domain.DireccionEnvio;
import domain.Pedido;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

/**
 *
 * @author javramfue
 */
public class PedidoDAO {
    private static final String SQL_SELECT ="SELECT * FROM pedido";    
    private static final String SQL_INSERT ="INSERT INTO pedido (idDireccion,fecha,idCliente) VALUES (?,?,?)";
    private static final String SQL_UPDATE ="UPDATE pedido SET idDireccion = ?, fecha = ?,idCliente = ? WHERE idPedido = ?; ";
    private static final String SQL_GET ="SELECT * FROM pedido WHERE idDireccion = ? && fecha = ? && idCliente = ?";
    private static final String SQL_GET_BY_ID ="SELECT * FROM pedido WHERE idPedido = ?";
    private static final String SQL_DELETE ="DELETE FROM pedido WHERE idPedido = ?";

    
    public List<Pedido> seleccionar() throws SQLException{
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Pedido pedido = null;
        List<Pedido> pedidos = new ArrayList<>();
        
        try {
            conn = getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            while(rs.next()){
                int idPedido = rs.getInt("idPedido");
                Date fecha = rs.getDate("fecha");
                int direccionEnvio = rs.getInt("idDireccion");
                int cliente = rs.getInt("idCliente");
                pedido = new Pedido(idPedido, fecha,direccionEnvio,cliente);
                pedidos.add(pedido);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        
        finally{
            Conexion.close(conn);
            Conexion.close(rs);
            Conexion.close(stmt);
        }
        return pedidos;
    }
    
    public int insertar(Pedido pedido) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int registros = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setInt(1,pedido.getDireccionEnvio());
            stmt.setDate(2, (java.sql.Date) pedido.getFecha());
            stmt.setInt(3,pedido.getIdCliente());
            registros = stmt.executeUpdate();
            
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        
        finally{
            try {
                Conexion.close(stmt);
            } catch (SQLException ex) {
            }
            try {
                Conexion.close(conn);
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
        }
        return registros;
    }
    
    public boolean actualizar(Pedido pedido) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int registros = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_UPDATE);
            java.sql.Date fechaSql = new java.sql.Date(pedido.getFecha().getTime());
            stmt.setInt(1,pedido.getDireccionEnvio());
            stmt.setDate(2,fechaSql);
            stmt.setInt(3,pedido.getIdCliente());
            stmt.setInt(4,pedido.getId_pedido());
            registros = stmt.executeUpdate();
            return true;
            
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        
        finally{
            try {
                Conexion.close(stmt);
            } catch (SQLException ex) {
            }
            try {
                Conexion.close(conn);
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
        }
        return false;
    }
    
    public Pedido obtener(Pedido pedido) throws SQLException{
        Connection conn = null;
        PreparedStatement stmt = null;
        Pedido pedidoNuevo = new Pedido();
        ResultSet rs = null;
        int registros = 0;
        try {
            conn = Conexion.getConnection();
            java.sql.Date fechaSql = new java.sql.Date(pedido.getFecha().getTime());
            stmt = conn.prepareStatement(SQL_GET);
            stmt.setInt(1,pedido.getDireccionEnvio());
            stmt.setDate(2,fechaSql);
            stmt.setInt(3,pedido.getIdCliente());
            rs = stmt.executeQuery();
            while(rs.next()){
                int id_pedido = rs.getInt("idPedido");
                Date fecha = rs.getDate("fecha");
                int dirEnvio = rs.getInt("idDireccion");
                int cliente = rs.getInt("idCliente");
                Pedido ped = new Pedido(id_pedido,fecha,dirEnvio,cliente);
                pedidoNuevo = ped;
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        
        finally{
            try {
                Conexion.close(stmt);
            } catch (SQLException ex) {
            }
            try {
                Conexion.close(conn);
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
        }
        return pedidoNuevo;
    }
    
    public Pedido obtenerPoId(int id) throws SQLException{
        Connection conn = null;
        PreparedStatement stmt = null;
        Pedido pedidoNuevo = new Pedido();
        ResultSet rs = null;
        int registros = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_GET_BY_ID);
            stmt.setInt(1,id);
            rs = stmt.executeQuery();
            while(rs.next()){
                int id_pedido = rs.getInt("idPedido");
                Date fecha = rs.getDate("fecha");
                int dirEnvio = rs.getInt("idDireccion");
                int cliente = rs.getInt("idCliente");
                Pedido ped = new Pedido(id_pedido,fecha,dirEnvio,cliente);
                pedidoNuevo = ped;
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        
        finally{
            try {
                Conexion.close(stmt);
            } catch (SQLException ex) {
            }
            try {
                Conexion.close(conn);
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
        }
        return pedidoNuevo;
    }
        
    public boolean eliminar(int id) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int registros = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setInt(1,id);
            registros = stmt.executeUpdate();
            System.out.println("Eliminado correctamente");
            return true;
            
        } catch (SQLException ex) {
            System.out.println("Se ha producido un error!");
            ex.printStackTrace(System.out);
        }
        
        finally{
            try {
                Conexion.close(stmt);
            } catch (SQLException ex) {
            }
            try {
                Conexion.close(conn);
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
        }
        return false;
    }

}