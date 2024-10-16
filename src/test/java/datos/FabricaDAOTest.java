/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package datos;

import domain.Fabrica;
import datos.Conexion;
import static datos.Conexion.getConnection;
import domain.PedidoArticulo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.sql.SQLException;

/**
 *
 * @author javramfue
 */
public class FabricaDAOTest {
    
    
    
    public FabricaDAOTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    @Test
    public void testBorrarFabricasSinPedidos_SuccesfulDeletion() throws SQLException {
        /* Arrange
        Connection conn = getConnection();
        ResultSet rs = null;
        PreparedStatement stmt = conn.prepareStatement("");
        

        // Act
        boolean result = borrarFabricasSinPedidos();

        // Assert
        assertTrue(result);
        verify(stmt, times(1)).executeUpdate();
        verify(conn, times(1)).close();
*/
    }

    /**
     * Test of borrarFabricasSinPedidos method, of class FabricaDAO.
     */
    @Test
    public void testBorrarFabricasSinPedidos() {
        System.out.println("borrarFabricasSinPedidos");
        FabricaDAO instance = new FabricaDAO();
        boolean expResult = false;
        boolean result = instance.borrarFabricasSinPedidos();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
