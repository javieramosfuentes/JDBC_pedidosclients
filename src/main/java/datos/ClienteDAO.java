/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package datos;

import static datos.Conexion.getConnection;
import java.util.*;
import domain.Cliente;
import domain.Pedido;
import java.sql.*;

/**
 *
 * @author javramfue
 */
public class ClienteDAO {
    private static final String SQL_SELECT ="SELECT * FROM cliente";    
    private static final String SQL_INSERT ="INSERT INTO cliente (saldo,limiteCredito,descuento,idDireccion) VALUES (?,?,?,?)";
    private static final String SQL_UPDATE ="UPDATE cliente SET saldo = ?, limiteCredito = ?, descuento = ?, idDireccion = ? WHERE idCliente = ?; ";
    private static final String SQL_DELETE ="DELETE FROM cliente WHERE idCliente = ?";
    private static final String SQL_DELETE_PEDIDOS ="DELETE FROM pedido WHERE idCliente = ?";
    private static final String SQL_GET ="SELECT * FROM cliente WHERE idCliente = ?";
    private static final String SQL_GET_PED_WITH_DISCOUNT ="SELECT p.*, c.descuento FROM pedido p JOIN cliente c ON p.idCliente = c.idCliente WHERE p.idCliente = ?";
    private static final String SQL_CHECK_EXISTENCE = "SELECT COUNT(*) FROM cliente WHERE idCliente = ?";
    

    
    public List<Cliente> seleccionar() throws SQLException{
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Cliente cli = null;
        List<Cliente> clientes = new ArrayList<>();
        
        try {
            conn = getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            while(rs.next()){
                int idCliente = rs.getInt("idCliente");
                float saldo = rs.getFloat("saldo");
                float limiteCredito = rs.getFloat("limiteCredito");
                float descuento = rs.getFloat("descuento");
                int direccionEnvio = rs.getInt("idDireccion");
                cli = new Cliente(idCliente, saldo, limiteCredito, descuento,direccionEnvio);
                clientes.add(cli);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        
        finally{
            Conexion.close(conn);
            Conexion.close(rs);
            Conexion.close(stmt);
        }
        return clientes;
    }
    
    public int insertar(Cliente cliente) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int registros = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setFloat(1,cliente.getSaldo());
            stmt.setFloat(2,cliente.getLimiteCredito());
            stmt.setFloat(3,cliente.getDescuento());
            stmt.setInt(4,cliente.getDireccionEnvio());
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

    public boolean actualizar(Cliente cliente,int id) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int registros = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_UPDATE);
            stmt.setFloat(1,cliente.getSaldo());
            stmt.setFloat(2,cliente.getLimiteCredito());
            stmt.setFloat(3,cliente.getDescuento());
            stmt.setInt(4,cliente.getDireccionEnvio());
            stmt.setInt(5,id);
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
        
    public boolean eliminar(int id) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int registros = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setInt(1,id);
            registros = stmt.executeUpdate();
            System.out.println("-- Eliminado correctamente --");
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
    
    public boolean eliminarPedidos(int id) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int registros = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_DELETE_PEDIDOS);
            stmt.setInt(1,id);
            registros = stmt.executeUpdate();
            System.out.println("-- Eliminado correctamente --");
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
  
    
    public Cliente obtener(int idCliente) throws SQLException{
        Connection conn = null;
        PreparedStatement stmt = null;
        Cliente cliente = new Cliente();
        ResultSet rs = null;
        int registros = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_GET);
            stmt.setInt(1,idCliente);
            rs = stmt.executeQuery();
            while(rs.next()){
                int id_cliente = rs.getInt("idCliente");
                float saldo = rs.getFloat("saldo");
                float limiteCredito = rs.getFloat("limiteCredito");
                float descuento = rs.getFloat("descuento");
                int direccionEnvio = rs.getInt("idDireccion");
                Cliente cli = new Cliente(id_cliente, saldo, limiteCredito, descuento,direccionEnvio);
                cliente = cli;
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
        return cliente;
    }
    
    public List<Pedido> listarPedidosConDescuentos(int idCliente) {
        Connection conn = null;
        PreparedStatement stmt = null;
        List<Pedido> pedidos = new ArrayList<>();
        float totalDescuentos = 0;
        
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_GET_PED_WITH_DISCOUNT);
            stmt.setInt(1,idCliente);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int idPedido = rs.getInt("idPedido");
                java.sql.Date fecha = new java.sql.Date(rs.getDate("fecha").getTime());
                float descuento = rs.getFloat("descuento");
                int idDireccion = rs.getInt("idDireccion");
                totalDescuentos += descuento;
                pedidos.add(new Pedido(idPedido, fecha, idDireccion,idCliente));
            }
            System.out.println("");
            System.out.println("Total Descuentos: " + totalDescuentos);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pedidos;
    }
    
    public boolean comprobarExistencia(int idCliente) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        boolean existe = false;

        try {
            conn = Conexion.getConnection();

            stmt = conn.prepareStatement(SQL_CHECK_EXISTENCE);
            stmt.setInt(1, idCliente);
            rs = stmt.executeQuery();

            if (rs.next()) {
                int count = rs.getInt(1);
                existe = (count > 0);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            try {
                Conexion.close(rs);
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
            try {
                Conexion.close(stmt);
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
            try {
                Conexion.close(conn);
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
        }
        return existe;
    }
}