/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package datos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author javramfue
 */
public class Conexion {
    
    private static final String JDBC_URL ="jdbc:mysql://localhost:3306/bd_peticionsclients?useSSL=false&useTimezone=true&serverTimezone=UTC&allowPublicKeyRetrieval=true";
    private static final String JDBC_USER ="root";
    private static final String JDBC_PASSWORD ="serpis";

    public static Connection getConnection() throws SQLException{
      return DriverManager.getConnection  (JDBC_URL,JDBC_USER,JDBC_USER);
   }
    
    public static void close(ResultSet rs) throws SQLException{
        rs.close();
   }
    
    public static void close(Statement stmt) throws SQLException{
        stmt.close();
   }
    
    public static void close(Connection conn) throws SQLException{
        conn.close();
   }
}
