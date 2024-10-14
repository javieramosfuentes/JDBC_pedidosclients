/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */
package com.mycompany.jdbc_pedidosclients;

import datos.*;
import domain.*;
import java.sql.SQLException;
import java.util.List;
import java.sql.SQLException;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.text.SimpleDateFormat;

/**
 *
 * @author javramfue
 */
public class JDBC_pedidosclients {

    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        try {
            //menu(new ClienteDAO());
            menu();
        } catch (SQLException exc) {

        }

    }

    /**
     * Método que muestra la estética del menú
     * @throws SQLException 
     */
    public static void menu() throws SQLException {
        System.out.println("");
        System.out.println("SELECCIONA UN ELEMENTO DEL MENÃš");
        System.out.println("-------------------------------");
        System.out.println("1. Seleccionar");
        System.out.println("2. Insertar");
        System.out.println("3. Borrar");
        System.out.println("4. Actualizar");
        System.out.println("Cualquier otra tecla (Salir)");
        System.out.println("-------------------------------");

        String accion;
        System.out.print("Opción: ");
        accion = sc.nextLine();
        switch (accion) {
            case "1":
                seleccionTabla(accion);
                break;

            case "2":
                seleccionTabla(accion);
                break;
            case "3":
                seleccionTabla(accion);
                break;
            case "4":
                seleccionTabla(accion);
                break;
            /**
             * case "3": Cliente personaABorrar = new Cliente();
             * System.out.print("ID de la persona a eliminar: "); int id =
             * Integer.parseInt(sc.nextLine()); System.out.print("Â¿Estas seguro
             * (Si/No)? "); String confirm = sc.nextLine();
             * if("Si".equals(confirm)){ personaDAO.eliminar(id); } else
             * if("No".equals(confirm)) System.out.print("Cancelado!"); else{
             * System.out.print("No se acepta esa opciÃ³n"); menu(personaDAO); }
             * break; case "4": System.out.print("ID de la persona a actualizar:
             * "); int id_persona = Integer.parseInt(sc.nextLine()); Cliente
             * personaAActualizar = new Cliente();
             * personaAActualizar.setIdPersona(id_persona);
             * System.out.print("Nombre "); String name_persona = sc.nextLine();
             * personaAActualizar.setNombre(name_persona);
             * System.out.print("Apellidos: "); String lastname_persona =
             * sc.nextLine(); personaAActualizar.setApellidos(lastname_persona);
             * System.out.print("Email: "); String email_persona =
             * sc.nextLine(); personaAActualizar.setEmail(email_persona);
             * System.out.print("Edad: "); String age_persona = sc.nextLine();
             * personaAActualizar.setEdad(Integer.parseInt(age_persona));
             * personaDAO.actualizar(personaAActualizar);
             * System.out.println("Persona actualizada con Ã©xito"); break;
                *
             */
            default:
                System.exit(0);
                break;
        }
        //menu(clienteDAO);
        menu();
    }

    /**
     * Método que muestra i carga la lógica del menú
     * @param accion 
     */
    public static void seleccionTabla(String accion) {
        try {
            System.out.print("Que desea seleccionar? (Clientes/Pedidos/Articulos/Fabricas/Direcciones) ");
            String tabla = sc.nextLine();
            switch (tabla) {
                case "Clientes":
                    DireccionClienteDAO direccionCliDAO = new DireccionClienteDAO();
                    ClienteDAO clienteDAO = new ClienteDAO();
                    switch (accion) {
                        case "1":
                            List<Cliente> clientes = clienteDAO.seleccionar();
                            clientes.forEach(cli -> {
                                //Seleccionar La direccion por su id
                                System.out.println("");
                                System.out.println("Id: " + cli.getId_cliente() + " - Saldo: " + cli.getSaldo() + " - Limite de Credito: " + cli.getLimiteCredito() + " - Descuento: " + cli.getDescuento()+ " - Direccion: " + cli.getDireccionEnvio());
                            });
                            break;
                        case "2":
                            Cliente cliente = new Cliente();
                            System.out.print("Saldo: ");
                            String saldo = sc.nextLine();
                            cliente.setSaldo(Float.parseFloat(saldo));
                            System.out.print("Limite de Credito: ");
                            String limiteCredito = sc.nextLine();
                            cliente.setLimiteCredito(Float.parseFloat(limiteCredito));
                            System.out.print("Descuento: ");
                            String descuento = sc.nextLine();
                            cliente.setDescuento(Float.parseFloat(descuento));
                            
                            DireccionEnvio direccionEnvio = new DireccionEnvio();
                            System.out.println("Desea utilizar una direcciÃ³n de enviÃ³ existente? s/n ");
                            String seleccionEnvio = sc.nextLine();
                            switch(seleccionEnvio){
                                case "s":
                                    DireccionClienteDAO dirCliDAO = new DireccionClienteDAO();
                                    List<DireccionEnvio> direccionesCliente = dirCliDAO.seleccionar();
                                    System.out.println("-- Direcciones  --");
                                    direccionesCliente.forEach(dir -> {
                                        System.out.println("");
                                        System.out.println("Id: " + dir.getId_direccion()+ " - Ciudad: " + dir.getCiudad()+ " - Calle: " + dir.getCalle() + " - Comuna: "+ dir.getComuna() + " - NÃºmero: "+dir.getNumero());
                                    });
                                    System.out.println(" ");
                                    System.out.print("--> Seleccione tu direccion: ");
                                    String direccion = sc.nextLine();
                                    System.out.println("");
                                    DireccionEnvio dirSeleccionada = direccionCliDAO.obtenerPorID(Integer.parseInt(direccion));
                                    cliente.setDireccionEnvio(dirSeleccionada.getId_direccion());
                                break;
                                case "n":
                                    System.out.print("Ciudad: ");
                                    String ciudad = sc.nextLine();
                                    System.out.print("Calle: ");
                                    String calle = sc.nextLine();
                                    System.out.print("Comuna: ");
                                    String comuna = sc.nextLine();
                                    System.out.print("NÃºmero: ");
                                    String numero = sc.nextLine();
                                    direccionEnvio = new DireccionEnvio(ciudad,calle,comuna,numero);
                                    direccionCliDAO.insertar(direccionEnvio);
                                    cliente.setDireccionEnvio(direccionCliDAO.obtener(direccionEnvio).getId_direccion());
                                break;
                                default:
                                    System.out.println("!! OperaciÃ³n cancelada !!");
                                    menu();
                                break;
                            }
                            clienteDAO.insertar(cliente);
                            System.out.println("-- Cliente añadido correctamente --");
                            break;
                        case "3":

                            break;
                        case "4":

                            break;
                    }
                    break;
                case "Pedidos":
                    DireccionPedidoDAO direccionPedDAO = new DireccionPedidoDAO();
                    PedidoDAO pedidoDAO = new PedidoDAO();
                    switch (accion) {
                        case "1":
                            List<Pedido> pedidos = pedidoDAO.seleccionar();
                            pedidos.forEach(ped -> {
                                //Seleccionar La direccion por su id
                                System.out.println("");
                                System.out.println("Id: " + ped.getId_pedido() + " - Fecha: " + ped.getFecha() + " - Direccion de Envio: " + ped.getDireccionEnvio() + " - Cliente: "+ped.getIdCliente());
                            });
                            break;
                        case "2":
                            Pedido pedido = new Pedido();
                            ClienteDAO cliDAO = new ClienteDAO();
                            Date fecha = new Date();
                            java.sql.Date fechaSql = new java.sql.Date(fecha.getTime());
                            pedido.setFecha(fechaSql);
                            System.out.print("ID del Cliente que hace el pedido: ");
                            String idCliente = sc.nextLine();
                            pedido.setIdCliente(Integer.parseInt(idCliente));
                            
                            DireccionEnvio direccionEnvio = new DireccionEnvio();
                            System.out.println("Desea utilizar una direcciÃ³n de enviÃ³ existente? s/n ");
                            String seleccionEnvio = sc.nextLine();
                            switch(seleccionEnvio){
                                case "s":
                                    DireccionClienteDAO dirCliDAO = new DireccionClienteDAO();
                                    List<DireccionEnvio> direccionesCliente = dirCliDAO.seleccionar();
                                    System.out.println("-- Direcciones  --");
                                    direccionesCliente.forEach(dir -> {
                                        System.out.println("");
                                        System.out.println("Id: " + dir.getId_direccion()+ " - Ciudad: " + dir.getCiudad()+ " - Calle: " + dir.getCalle() + " - Comuna: "+ dir.getComuna() + " - NÃºmero: "+dir.getNumero());
                                    });
                                    System.out.println(" ");
                                    System.out.println("-- Seleccione tu direccion --");
                                    String direccion = sc.nextLine();
                                    pedido.setDireccionEnvio(Integer.parseInt(direccion));
                                    pedidoDAO.insertar(pedido);
                                break;
                                case "n":
                                    System.out.println("-- Añade una dirección de EnvÃ­o -- ");
                                    System.out.print("Ciudad: ");
                                    String ciudad = sc.nextLine();
                                    direccionEnvio.setCiudad(ciudad);
                                    System.out.print("Calle: ");
                                    String calle = sc.nextLine();
                                    direccionEnvio.setCalle(calle);
                                    System.out.print("Comuna: ");
                                    String comuna = sc.nextLine();
                                    direccionEnvio.setComuna(comuna);
                                    System.out.print("NÃºmero: ");
                                    String numero = sc.nextLine();
                                    direccionEnvio.setNumero(numero);

                                    direccionPedDAO.insertar(direccionEnvio);
                                    pedido.setDireccionEnvio(direccionPedDAO.obtener(direccionEnvio).getId_direccion());
                                    pedidoDAO.insertar(pedido);
                                break;
                                default:
                                    System.out.println("!! OperaciÃ³n cancelada !!");
                                    menu();
                                break;
                            }
                            System.out.println("-- Introduce los artículos de tu pedido --");
                            for(String opcion = "s";opcion == "s";){
                                System.out.print("Desea añadir un artículo?(s/n):");
                                opcion = sc.nextLine();
                                if(opcion == "n")
                                    break;
                                System.out.print("Añade su descripción:");
                                String descripcion = sc.nextLine();
                                ArticuloDAO articuloDAO = new ArticuloDAO();
                                Articulo articulo = new Articulo(descripcion);
                                articuloDAO.insertar(articulo);
                                
                                System.out.print("Cantidad:");
                                String cantidad = sc.nextLine();
                                PedidoArticulo pedidoArt = new PedidoArticulo(Integer.parseInt(cantidad),pedidoDAO.obtener(pedido).getId_pedido(),articuloDAO.obtenerPorDesc(descripcion).getId_articulo());
                                PedidoArticuloDAO pedDAO = new PedidoArticuloDAO();
                                pedDAO.insertar(pedidoArt);
                            }
                            System.out.println("-- Pedido añadido correctamente --");
                            break;
                        case "3":
                            
                            break;
                        case "4":

                            break;
                    }
                    break;
                        //Seleccionar La direccion por su id
                case "Articulos":
                    switch (accion) {
                        case "1":
                            ArticuloDAO articuloDAO = new ArticuloDAO();
                            List<Articulo> articulos = articuloDAO.seleccionar();
                            articulos.forEach(art -> {
                                //Seleccionar La direccion por su id
                                System.out.println("");
                                System.out.println("Id: " + art.getId_articulo() + " - DescripciÃ³n: " + art.getDescripcion());
                            });
                        break;
                        case "2":
                            
                        break;
                        case "3":
                            
                        break;
                        case "4":
                            
                        break;
                    }
                    break;
                case "Fabricas":
                    switch (accion) {
                        case "1":
                            FabricaDAO fabricaDAO = new FabricaDAO();
                            List<Fabrica> fabricas = fabricaDAO.seleccionar();
                            fabricas.forEach(fab -> {
                                //Seleccionar La direccion por su id
                                System.out.println("");
                                System.out.println("Id: " + fab.getId_fabrica() + " - Telefono: " + fab.getTelefono()+ " - Articulos Provistos: " + fab.getArticulos_provistos());
                            });
                        break;
                        case "2":
                            FabricaDAO fabDAO = new FabricaDAO();
                            System.out.println("-- Añade una Fabrica -- ");
                            System.out.print("Teléfono: ");
                            String telefono = sc.nextLine();
                            System.out.print("Articulos Provistos: ");
                            String artProv = sc.nextLine();
                            Fabrica fabrica = new Fabrica(telefono,Integer.parseInt(artProv));
                            fabDAO.insertar(fabrica);
                        break;
                        case "3":
                            
                        break;
                        case "4":
                            
                        break;
                    }
                    break;
                case "Direcciones":
                    switch (accion) {
                        case "1":
                            //Direccoiones Pedidos
                            DireccionPedidoDAO direccionesPedDAO = new DireccionPedidoDAO();
                            List<DireccionEnvio> direccionesPed = direccionesPedDAO.seleccionar();
                            
                            //Direcciones Clientes
                            DireccionClienteDAO direccionesCliDAO = new DireccionClienteDAO();
                            List<DireccionEnvio> direccionesCli = direccionesCliDAO.seleccionar();
                            
                            System.out.println("");
                            System.out.println("-------- Direcciones de los Pedidos  --------");
                            direccionesPed.forEach(direccion -> {
                                System.out.println("");
                                System.out.println("Id: " + direccion.getId_direccion()+ " - Ciudad: " + direccion.getCiudad() + " - Calle: " + direccion.getCalle() + " - Comuna: " +direccion.getComuna() + " - NÃºmero: " + direccion.getNumero());
                            });
                            System.out.println("");
                            System.out.println("-------- Direcciones de los Clientes --------");
                            direccionesCli.forEach(direccion -> {
                                System.out.println("");
                                System.out.println("Id: " + direccion.getId_direccion()+ " - Ciudad: " + direccion.getCiudad() + " - Calle: " + direccion.getCalle() + " - Comuna: " +direccion.getComuna() + " - NÃºmero: " + direccion.getNumero());
                            });
                        break;
                        case "2":
                            DireccionClienteDAO direccionEnvioCliDAO = new DireccionClienteDAO();
                            DireccionEnvio nuevaDireccionEnvio = new DireccionEnvio();
                            System.out.println("-- Añade una dirección de Envío -- ");
                            System.out.print("Ciudad: ");
                            String ciudad = sc.nextLine();
                            nuevaDireccionEnvio.setCiudad(ciudad);
                            System.out.print("Calle: ");
                            String calle = sc.nextLine();
                            nuevaDireccionEnvio.setCalle(calle);
                            System.out.print("Comuna: ");
                            String comuna = sc.nextLine();
                            nuevaDireccionEnvio.setComuna(comuna);
                            System.out.print("NÃºmero: ");
                            String numero = sc.nextLine();
                            nuevaDireccionEnvio.setNumero(numero);

                            direccionEnvioCliDAO.insertar(nuevaDireccionEnvio);
                            
                            System.out.println(" ");
                            System.out.println("-- Dirección añadida correctamente -- ");
                            
                                
                        break;
                        case "3":
                            
                        break;
                        case "4":
                            
                        break;
                    }
                break;
            }
        } catch (SQLException exc) {

        }
    }
}