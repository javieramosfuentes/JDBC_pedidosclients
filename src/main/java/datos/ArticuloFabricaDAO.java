/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package datos;

import static datos.Conexion.getConnection;
import domain.ArticuloFabrica;
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
public class ArticuloFabricaDAO {
    private static final String SQL_SELECT ="SELECT * FROM articulofabrica";    
    private static final String SQL_INSERT ="INSERT INTO articulofabrica (idArticulo,idFabrica,precio,existencias) VALUES (?,?,?,?)";
    private static final String SQL_UPDATE ="UPDATE articulofabrica SET precio = ?,existencias = ? WHERE idArticulo = ? && idFabrica = ?; ";
    private static final String SQL_DELETE ="DELETE FROM articulofabrica WHERE idArticulo = ? && idFabrica = ?";
    private static final String SQL_CALC_ART_X_ANYO ="SELECT SUM(ap.cantidad) AS total_articulos FROM articulopedido ap JOIN pedido p ON ap.idPedido = p.idPedido WHERE YEAR(p.fecha) = ?";

    
    public List<ArticuloFabrica> seleccionar() throws SQLException{
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArticuloFabrica articulo = null;
        List<ArticuloFabrica> articulosFabrica = new ArrayList<>();
        
        try {
            conn = getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            while(rs.next()){
                int idArticulo = rs.getInt("idArticulo");
                int idFabrica = rs.getInt("idFabrica");
                int existencias = rs.getInt("existencias");
                int precio = rs.getInt("precio");
                articulo = new ArticuloFabrica(precio, existencias,idArticulo,idFabrica);
                articulosFabrica.add(articulo);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        
        finally{
            Conexion.close(conn);
            Conexion.close(rs);
            Conexion.close(stmt);
        }
        return articulosFabrica;
    }
    
    public int insertar(ArticuloFabrica articulolFabrica) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int registros = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setInt(1,articulolFabrica.getIdArticulo());
            stmt.setInt(2,articulolFabrica.getIdFabrica());
            stmt.setInt(3,articulolFabrica.getPrecio());
            stmt.setInt(4,articulolFabrica.getExistencias());
            
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
    
    
    public boolean actualizar(ArticuloFabrica articuloFabrica) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int registros = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_UPDATE);
            stmt.setInt(1,articuloFabrica.getPrecio());
            stmt.setInt(2,articuloFabrica.getExistencias());
            stmt.setInt(3,articuloFabrica.getIdArticulo());
            stmt.setInt(4,articuloFabrica.getIdFabrica());
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
    
    public int calcularCantidadArticulosPorAnyo(String anyo) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        int registros = 0;
        int totalArticulos = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_CALC_ART_X_ANYO);
            stmt.setString(1,anyo);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                totalArticulos = rs.getInt("total_articulos");
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
        return totalArticulos;
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