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
        int idCliente = 1; // assuming a valid client ID
        ClienteDAO clienteDAO = new ClienteDAO();
        LocalDate fecha1 = LocalDate.parse("2024-10-10");
        java.sql.Date sqlFecha1 = java.sql.Date.valueOf(fecha1);
        
        LocalDate fecha2 = LocalDate.parse("2024-10-14");
        java.sql.Date sqlFecha2 = java.sql.Date.valueOf(fecha2);
        
        LocalDate fecha3 = LocalDate.parse("2024-10-16");
        java.sql.Date sqlFecha3 = java.sql.Date.valueOf(fecha3);
        System.out.println(fecha1);
        List<Pedido> expResult = Arrays.asList(
                new Pedido(4,sqlFecha1,0,1),
                new Pedido(5,sqlFecha1,0,1),
                new Pedido(6,sqlFecha1,1,1),
                new Pedido(7,sqlFecha2,1,1),
                new Pedido(8,sqlFecha2,2,1),
                new Pedido(12,sqlFecha2,1,1),
                new Pedido(19,sqlFecha3,2,1)
        );
        List<Pedido> result = clienteDAO.listarPedidosConDescuentos(idCliente);
        assertIterableEquals(result, result);
    }
    
}
