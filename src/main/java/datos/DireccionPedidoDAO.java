/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package datos;

import static datos.Conexion.getConnection;
import domain.DireccionEnvio;
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
public class DireccionPedidoDAO {
    private static final String SQL_SELECT ="SELECT * FROM direccionpedido";    
    private static final String SQL_INSERT ="INSERT INTO direccionpedido (ciudad,calle,comuna,numero) VALUES (?,?,?,?)";
    private static final String SQL_UPDATE ="UPDATE direccionpedido SET ciudad = ?, calle = ?, comuna = ?, numero = ? WHERE idDireccion = ?; ";
    private static final String SQL_DELETE ="DELETE FROM direccionpedido WHERE idDireccion = ?";
    private static final String SQL_GET ="SELECT * FROM direccionpedido WHERE ciudad = ? && calle = ? && comuna = ? && numero = ?";
    private static final String SQL_GET_BY_ID ="SELECT * FROM direccionpedido WHERE idDireccion = ?";
        private static final String SQL_CHECK_EXISTENCE = "SELECT COUNT(*) FROM direccionpedido WHERE idDireccion = ?";

    
    public List<DireccionEnvio> seleccionar() throws SQLException{
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        DireccionEnvio direccion = null;
        List<DireccionEnvio> direcciones = new ArrayList<>();
        
        try {
            conn = getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            while(rs.next()){
                int idDireccion = rs.getInt("idDireccion");
                String numero = rs.getString("numero");
                String calle = rs.getString("calle");
                String ciudad = rs.getString("ciudad");
                String comuna = rs.getString("comuna");
                direccion = new DireccionEnvio(idDireccion,numero,calle,comuna,ciudad);
                direcciones.add(direccion);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        
        finally{
            Conexion.close(conn);
            Conexion.close(rs);
            Conexion.close(stmt);
        }
        return direcciones;
    }
    
    public int insertar(DireccionEnvio direccion) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int registros = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setString(1,direccion.getCiudad());
            stmt.setString(2,direccion.getCalle());
            stmt.setString(3,direccion.getComuna());
            stmt.setString(4,direccion.getNumero());
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
    
        public DireccionEnvio obtener(DireccionEnvio direccion) throws SQLException{
        Connection conn = null;
        PreparedStatement stmt = null;
        DireccionEnvio dirEnv = new DireccionEnvio();
        ResultSet rs = null;
        int registros = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_GET);
            stmt.setString(1,direccion.getCiudad());
            stmt.setString(2,direccion.getCalle());
            stmt.setString(3,direccion.getComuna());
            stmt.setString(4,direccion.getNumero());
            rs = stmt.executeQuery();
            while(rs.next()){
                int id_dir = rs.getInt("idDireccion");
                String ciudad = rs.getString("ciudad");
                String calle = rs.getString("calle");
                String comuna = rs.getString("comuna");
                String numero = rs.getString("numero");
                DireccionEnvio dir = new DireccionEnvio(id_dir,ciudad,calle,comuna,numero);
                dirEnv = dir;
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
        return dirEnv;
    }
        
    public DireccionEnvio obtenerPorID(int direccion) throws SQLException{
        Connection conn = null;
        PreparedStatement stmt = null;
        DireccionEnvio dirEnv = new DireccionEnvio();
        ResultSet rs = null;
        int registros = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_GET_BY_ID);
            stmt.setInt(1,direccion);
            rs = stmt.executeQuery();
            while(rs.next()){
                int id_dir = rs.getInt("idDireccion");
                String ciudad = rs.getString("ciudad");
                String calle = rs.getString("calle");
                String comuna = rs.getString("comuna");
                String numero = rs.getString("numero");
                DireccionEnvio dir = new DireccionEnvio(id_dir,ciudad,calle,comuna,numero);
                dirEnv = dir;
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
        return dirEnv;
    }
    public boolean actualizar(DireccionEnvio direccionEnvio) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int registros = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_UPDATE);
            stmt.setString(1,direccionEnvio.getCiudad());
            stmt.setString(2,direccionEnvio.getCalle());
            stmt.setString(3,direccionEnvio.getComuna());
            stmt.setString(4,direccionEnvio.getNumero());
            stmt.setInt(5,direccionEnvio.getId_direccion());
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
    public boolean comprobarExistencia(int idDireccion) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        boolean existe = false;

        try {
            conn = Conexion.getConnection();

            stmt = conn.prepareStatement(SQL_CHECK_EXISTENCE);
            stmt.setInt(1, idDireccion);
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