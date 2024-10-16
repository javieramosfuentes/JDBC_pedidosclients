/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package datos;

import domain.ArticuloFabrica;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author javramfue
 */
public class ArticuloFabricaDAOTest {
    
    public ArticuloFabricaDAOTest() {
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


    /**
     * Test of calcularCantidadArticulosPorAnyo method, of class ArticuloFabricaDAO.
     */
    @Test
    public void testCalcularCantidadArticulosPorAnyo() throws Exception {
        String anyo = "2024";
        ArticuloFabricaDAO instance = new ArticuloFabricaDAO();
        int expResult = 50;
        int result = instance.calcularCantidadArticulosPorAnyo(anyo);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of calcularCantidadArticulosPorAnyo method, of class ArticuloFabricaDAO.
     */
    @Test
    public void testCalcularCantidadArticulosPorAnyo0() throws Exception {
        String anyo = "0";
        ArticuloFabricaDAO instance = new ArticuloFabricaDAO();
        int expResult = 0; 
        int result = instance.calcularCantidadArticulosPorAnyo(anyo);
        assertEquals(expResult, result);
    }
    
}
