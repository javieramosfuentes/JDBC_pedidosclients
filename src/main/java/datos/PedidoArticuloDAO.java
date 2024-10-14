/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package datos;

import static datos.Conexion.getConnection;
import domain.PedidoArticulo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author javramfue
 */
public class PedidoArticuloDAO {
    private static final String SQL_SELECT ="SELECT * FROM articulopedido";    
    private static final String SQL_INSERT ="INSERT INTO articulopedido (cantidad,idPedido,idArticulo) VALUES (?,?,?)";
    private static final String SQL_UPDATE ="UPDATE articulopedido SET cantidad = ? WHERE idPedido = ? && idArticulo = ? ; ";
    private static final String SQL_DELETE ="DELETE FROM articulopedido WHERE idPedido = ? && idArticulo = ?";

    
    public List<PedidoArticulo> seleccionar() throws SQLException{
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        PedidoArticulo articulo = null;
        List<PedidoArticulo> articulos = new ArrayList<>();
        
        try {
            conn = getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            while(rs.next()){
                int idArticulo = rs.getInt("idArticulo");
                int idPedido = rs.getInt("idPedido");
                int cantidad = rs.getInt("cantidad");
                articulo = new PedidoArticulo(cantidad,idPedido,idArticulo);
                articulos.add(articulo);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        
        finally{
            Conexion.close(conn);
            Conexion.close(rs);
            Conexion.close(stmt);
        }
        return articulos;
    }
    
    public int insertar(PedidoArticulo pedArt) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int registros = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setInt(1,pedArt.getCantidad());
            stmt.setInt(2,pedArt.getIdPedido());
            stmt.setInt(3,pedArt.getIdArticulo());
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
    
    
    public boolean actualizar(PedidoArticulo pedArt) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int registros = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_UPDATE);
            stmt.setInt(1,pedArt.getCantidad());
            stmt.setInt(2,pedArt.getIdPedido());
            stmt.setInt(3,pedArt.getIdArticulo());
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