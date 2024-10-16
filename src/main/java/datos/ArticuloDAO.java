package datos;

import static datos.Conexion.getConnection;
import domain.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author javramfue
 */
public class ArticuloDAO {
    private static final String SQL_SELECT ="SELECT * FROM articulo";    
    private static final String SQL_INSERT ="INSERT INTO articulo (descripcion) VALUES (?)";
    private static final String SQL_UPDATE ="UPDATE articulo SET descripcion = ? WHERE idArticulo = ?; ";
    private static final String SQL_DELETE ="DELETE FROM articulo WHERE idArticulo = ?";
    private static final String SQL_GET ="SELECT * FROM articulo WHERE idArticulo = ?";
    private static final String SQL_GET_BY_DESC ="SELECT * FROM articulo WHERE descripcion = ?";
    private static final String SQL_GET_ARTS_IN_PED = "SELECT DISTINCT articulo.* FROM articulo, articulopedido,pedido WHERE articulo.idArticulo = articulopedido.idArticulo && articulopedido.idPedido = ?";
    private static final String SQL_GET_ARTS_IN_FAB = "SELECT DISTINCT articulo.* FROM articulo, articulofabrica,fabrica WHERE articulo.idArticulo = articulofabrica.idArticulo && articulofabrica.idFabrica = ?";

    
    public List<Articulo> seleccionar() throws SQLException{
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Articulo articulo = null;
        List<Articulo> articulos = new ArrayList<>();
        
        try {
            conn = getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            while(rs.next()){
                int idArticulo = rs.getInt("idArticulo");
                String descripcion = rs.getString("descripcion");
                articulo = new Articulo(idArticulo,descripcion);
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
    
    public int insertar(Articulo articulo) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int registros = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setString(1,articulo.getDescripcion());
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
    
    
    public boolean actualizar(Articulo articulo) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int registros = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_UPDATE);
            stmt.setString(1,articulo.getDescripcion());
            stmt.setInt(2,articulo.getId_articulo());
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
    
    public Articulo obtener(Articulo articulo) throws SQLException{
        Connection conn = null;
        PreparedStatement stmt = null;
        Articulo nuevoArticulo = new Articulo();
        ResultSet rs = null;
        int registros = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_GET);
            stmt.setInt(1,articulo.getId_articulo());
            rs = stmt.executeQuery();
            while(rs.next()){
                int id_art = rs.getInt("idArticulo");
                String descripcion = rs.getString("descripcion");
                Articulo art = new Articulo(id_art,descripcion);
                nuevoArticulo = art;
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
        return nuevoArticulo;
    }
    
    public List<Articulo> obtenerDeUnPedido(Pedido pedido) throws SQLException{
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Articulo articulo = null;
        List<Articulo> articulos = new ArrayList<>();
        
        try {
            conn = getConnection();
            stmt = conn.prepareStatement(SQL_GET_ARTS_IN_PED);
            stmt.setInt(1,pedido.getId_pedido());
            rs = stmt.executeQuery();
            while(rs.next()){
                int idArticulo = rs.getInt("idArticulo");
                String descripcion = rs.getString("descripcion");
                articulo = new Articulo(idArticulo,descripcion);
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
    
    public Articulo obtenerPorDesc(String desc) throws SQLException{
        Connection conn = null;
        PreparedStatement stmt = null;
        Articulo articulo = new Articulo();
        ResultSet rs = null;
        int registros = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_GET_BY_DESC);
            stmt.setString(1,desc);
            rs = stmt.executeQuery();
            while(rs.next()){
                int id_dir = rs.getInt("idArticulo");
                String descripcion = rs.getString("descripcion");
                Articulo art = new Articulo(id_dir,descripcion);
                articulo = art;
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
        return articulo;
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