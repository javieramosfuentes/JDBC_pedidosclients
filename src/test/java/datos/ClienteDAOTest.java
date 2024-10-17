/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package datos;

import domain.Cliente;
import domain.Pedido;
import java.util.Arrays;
import java.util.List;
import java.util.*;
import java.io.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;

/**
 *
 * @author javramfue
 */
public class ClienteDAOTest {
    
    public ClienteDAOTest() {
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
     * Test of listarPedidosConDescuentos method, of class ClienteDAO.
     */
    @Test
    public void testListarPedidosConDescuentos() {
        int idCliente = 1; 
        ClienteDAO clienteDAO = new ClienteDAO();
        int expResult = 2;
        List<Pedido> result = clienteDAO.listarPedidosConDescuentos(idCliente);
        assertEquals(expResult, result.size());
    }
    
        /**
     * Test of listarPedidosConDescuentos method, of class ClienteDAO.
     */
    @Test
    public void testListar0PedidosConDescuentos() {
        int idCliente = 2; 
        ClienteDAO clienteDAO = new ClienteDAO();
        int expResult = 0;
        List<Pedido> result = clienteDAO.listarPedidosConDescuentos(idCliente);
        assertEquals(expResult, result.size());
    }
    
}
