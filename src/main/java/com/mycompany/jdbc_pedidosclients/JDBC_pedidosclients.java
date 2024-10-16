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
import java.util.logging.Level;
import java.util.logging.Logger;

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
        System.out.println("-------------------------------------------------------------------");
        System.out.println("1. Seleccionar");
        System.out.println("2. Insertar");
        System.out.println("3. Borrar");
        System.out.println("4. Actualizar");
        System.out.println("5. Ver pedidos de un cliente");
        System.out.println("6. Borrar fábricas que no tienen artículos en un pedido");
        System.out.println("7. Calcular artículos incluidos en pedidos en un año determinado");
        System.out.println("Tecla Q (Salir)");
        System.out.println("-------------------------------------------------------------------");

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
            case "5":
                ClienteDAO cliDAO = new ClienteDAO();
                System.out.print("Introduce el cliente del cual quiere ver sus comandas:");
                String id = sc.nextLine();
                System.out.println("----------------------------");
                System.out.println("|     Cliente: "+id+"        |");
                System.out.println("----------------------------");
                cliDAO.listarPedidosConDescuentos(Integer.parseInt(id)).forEach(ped -> {
                    //Seleccionar La direccion por su id
                    System.out.println("");
                    System.out.println("Id: " + ped.getId_pedido()+ " - Fecha: " + ped.getFecha()+ " - Dirección de envío: " + ped.getDireccionEnvio());
                });
            break;
            case "6":
                //Pendiente
                FabricaDAO fabDAO = new FabricaDAO();
                if(fabDAO.borrarFabricasSinPedidos())
                    System.out.println("-- Eliminado correctamente --");
                else
                    System.out.println("¡Error, no se ha podido eliminar!");
            break;
            case "7":
                System.out.print("Introduce el año que desees:");
                String anyo = sc.nextLine();
                ArticuloFabricaDAO artFabDAO = new ArticuloFabricaDAO();
                int totalArticulos = artFabDAO.calcularCantidadArticulosPorAnyo("2024");
                System.out.println("Total de artículos dentro de pedidos en "+anyo+":"+totalArticulos);
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
            case "Q":
                System.exit(0);
                break;
            default:
                System.out.println("Opción invalida, prueba otra vez");
                menu();
                break;
        }
        //menu(clienteDAO);
        menu();
    }

    /**
     * Método que muestra i carga la lógica del menú
     * @param accion 
     */
    public static void seleccionTabla(String accion) throws SQLException{
        try {
            System.out.print("Que desea seleccionar? (Clientes/Pedidos/Articulos/Fabricas/Direcciones) ");
            String tabla = sc.nextLine();
            switch (tabla) {
                case "Clientes":
                    DireccionClienteDAO direccionCliDAO = new DireccionClienteDAO();
                    ClienteDAO clienteDAO = new ClienteDAO();
                    switch (accion) {
                        case "1":
                            muestraClientes(clienteDAO);
                            break;
                        case "2":
                            Cliente cliente = new Cliente();
                            System.out.print("Saldo: ");
                            String saldo = sc.nextLine();
                            cliente.setSaldo(Float.parseFloat(saldo));
                            System.out.print("Limite de credito (Max: 18000): ");
                            String limiteCred = sc.nextLine();
                            float limiteCreditoParsed = Float.parseFloat(limiteCred);
                            if(limiteCreditoParsed <= 18000){
                                cliente.setLimiteCredito(limiteCreditoParsed);
                            }else{
                                do{
                                    System.out.println("¡Error: Limite de credito invalido! Vuelve a intentarlo ");
                                    System.out.print("");
                                    System.out.print("Limite de credito (Max: 18000): ");
                                    limiteCred = sc.nextLine();
                                }while(Float.parseFloat(limiteCred) > 18000);
                                cliente.setLimiteCredito(Float.parseFloat(limiteCred));
                            }
                            System.out.print("Descuento: ");
                            String descuento = sc.nextLine();
                            cliente.setDescuento(Float.parseFloat(descuento));
                            
                            DireccionEnvio direccionEnvio = new DireccionEnvio();
                            System.out.println("Desea utilizar una direcciÃ³n de enviÃ³ existente? s/n ");
                            String seleccionEnvio = sc.nextLine();
                            switch(seleccionEnvio){
                                case "s":
                                    muestraDireccionesClientes(direccionCliDAO);
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
                            muestraClientes(clienteDAO);
                            System.out.print("");
                            System.out.println("¿Que cliente desea eliminar? (Introduce su id)");
                            System.out.print("-> ");
                            String id = sc.nextLine();
                            clienteDAO.eliminar(Integer.parseInt(id));
                            break;
                        case "4":
                            muestraClientes(clienteDAO);
                            System.out.println("");
                            System.out.println("Que cliente desea actualizar? (Ingrese su id)");
                            System.out.print("-> ");
                            id = sc.nextLine();
                            cliente = new Cliente();
                            System.out.print("Saldo: ");
                            saldo = sc.nextLine();
                            cliente.setSaldo(Float.parseFloat(saldo));
                            System.out.print("Limite de credito (Max: 18000): ");
                            limiteCred = sc.nextLine();
                            limiteCreditoParsed = Float.parseFloat(limiteCred);
                            if(limiteCreditoParsed <= 18000){
                                cliente.setLimiteCredito(limiteCreditoParsed);
                            }else{
                                do{
                                    System.out.println("¡Error: Limite de credito invalido! Vuelve a intentarlo ");
                                    System.out.print("");
                                    System.out.print("Limite de credito (Max: 18000): ");
                                    limiteCred = sc.nextLine();
                                }while(Float.parseFloat(limiteCred) > 18000);
                                cliente.setLimiteCredito(Float.parseFloat(limiteCred));
                            }
                            System.out.print("Descuento: ");
                            descuento = sc.nextLine();
                            cliente.setDescuento(Float.parseFloat(descuento));
                            System.out.println("-- Introduce la direccion --");
                            System.out.println("");
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
                            clienteDAO.actualizar(cliente,Integer.parseInt(id));
                            System.out.println("-- Cliente actualizado correctamente --");
                        break;
                    }
                    break;
                case "Pedidos":
                    DireccionPedidoDAO direccionPedDAO = new DireccionPedidoDAO();
                    PedidoDAO pedidoDAO = new PedidoDAO();
                    ArticuloDAO artDAO = new ArticuloDAO();
                    ClienteDAO cliDAO = new ClienteDAO();
                    switch (accion) {
                        case "1":
                            muestraPedidos(pedidoDAO);
                            break;
                        case "2":
                            Pedido pedido = new Pedido();
                            Date fecha = new Date();
                            java.sql.Date fechaSql = new java.sql.Date(fecha.getTime());
                            pedido.setFecha(fechaSql);
                            muestraClientes(cliDAO);
                            System.out.println("--------------------------------------");
                            System.out.print("");
                            System.out.print("ID del Cliente que hace el pedido: ");
                            String idCliente = sc.nextLine();
                            pedido.setIdCliente(Integer.parseInt(idCliente));
                            
                            DireccionEnvio direccionEnvio = new DireccionEnvio();
                            System.out.println("Desea utilizar una direcciÃ³n de enviÃ³ existente? s/n ");
                            String seleccionEnvio = sc.nextLine();
                            switch(seleccionEnvio){
                                case "s":
                                    DireccionClienteDAO dirCliDAO = new DireccionClienteDAO();
                                    muestraDireccionesClientes(dirCliDAO);
                                    System.out.println(" ");
                                    System.out.println("-- Seleccione tu direccion --");
                                    System.out.print("->");
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
                            //Bucle que se repetirá si el usuario quiere seguir añadiendo articulos
                            for(boolean masArticulos = true;masArticulos;){
                                System.out.print("¿Desea añadir un artículo?(si/no):");
                                String opcion = sc.nextLine();
                                if("no".equals(opcion)){
                                    break;
                                }else if("si".equals(opcion)){
                                    muestraArticulos(artDAO);
                                    System.out.println("-----------------------------------------------");
                                    System.out.println("");
                                    System.out.print("¿Que articulo desea añadir?(Introduce la id):");
                                    String articulo = sc.nextLine();
                                    
                                    System.out.print("Cantidad:");
                                    String cantidad = sc.nextLine();
                                    PedidoArticulo pedidoArt = new PedidoArticulo(Integer.parseInt(cantidad),pedidoDAO.obtener(pedido).getId_pedido(),Integer.parseInt(articulo));
                                    PedidoArticuloDAO pedDAO = new PedidoArticuloDAO();
                                    pedDAO.insertar(pedidoArt);
                                }
                            }
                            System.out.println("");
                            System.out.println("-- Pedido añadido correctamente --");
                            break;
                        case "3":
                            muestraPedidos(pedidoDAO);
                            System.out.println("-----------------------------------------------");
                            System.out.println("");
                            System.out.println("¿Que pedido desea eliminar? (Introduce su id)");
                            System.out.print("-> ");
                            String id = sc.nextLine();
                            System.out.println("");
                            pedidoDAO.eliminar(Integer.parseInt(id));
                            break;
                        case "4":
                            muestraPedidos(pedidoDAO);
                            System.out.println("¿Que pedido desea actualizar? (Introduce su id)");
                            System.out.print("-> ");
                            String id_pedido = sc.nextLine();
                            System.out.println("");
                            Pedido updatePedido = pedidoDAO.obtenerPoId(Integer.parseInt(id_pedido));
                            fecha = new Date();
                            fechaSql = new java.sql.Date(fecha.getTime());
                            updatePedido.setFecha(fechaSql);
                            muestraClientes(cliDAO);
                            System.out.print("");
                            System.out.print("ID del Cliente que hace el pedido: ");
                            idCliente = sc.nextLine();
                            updatePedido.setIdCliente(Integer.parseInt(idCliente));
                            System.out.println("--------------------------------------");
                            System.out.print("");
                            System.out.print("¿Desea modificar la direccion?(si/no):");
                            String dirOpc = sc.nextLine();
                            if("si".equals(dirOpc)){
                                direccionEnvio = new DireccionEnvio();
                                System.out.println("-- Desea utilizar una direcciÃ³n de enviÃ³ existente? s/n  -- ");
                                seleccionEnvio = sc.nextLine();
                                switch(seleccionEnvio){
                                    case "s":
                                        DireccionClienteDAO dirCliDAO = new DireccionClienteDAO();
                                        muestraDireccionesClientes(dirCliDAO);
                                        System.out.println(" ");
                                        System.out.println("-- Seleccione tu direccion --");
                                        System.out.print("->");
                                        String direccion = sc.nextLine();
                                        updatePedido.setDireccionEnvio(Integer.parseInt(direccion));
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
                                        updatePedido.setDireccionEnvio(direccionPedDAO.obtener(direccionEnvio).getId_direccion());
                                    break;
                                    default:
                                        System.out.println("!! OperaciÃ³n cancelada !!");
                                        menu();
                                    break;
                                }
                            }
                            pedidoDAO.actualizar(updatePedido);
                            System.out.println("-- Articulos de tu pedido --");
                            fecha = new Date();
                            List<Articulo> arts = artDAO.obtenerDeUnPedido(updatePedido);
                            System.out.println("");
                            arts.forEach(direccion -> {
                                System.out.println("");
                                System.out.println("Id: " + direccion.getId_articulo()+ " - Descripción: " + direccion.getDescripcion());
                            });
                            System.out.println("");
                            //Bucle que se repetirá si el usuario quiere seguir añadiendo articulos
                            for(boolean masArticulos = true;masArticulos;){
                                System.out.print("¿Desea añadir un artículo?(si/no):");
                                String opcion = sc.nextLine();
                                if("no".equals(opcion)){
                                    break;
                                }else if("si".equals(opcion)){
                                    muestraArticulos(artDAO);
                                    System.out.println("-----------------------------------------------");
                                    System.out.println("");
                                    System.out.print("¿Que articulo desea añadir?(Introduce la id):");
                                    String articulo = sc.nextLine();
                                    
                                    System.out.print("Cantidad:");
                                    String cantidad = sc.nextLine();
                                    PedidoArticulo pedidoArt = new PedidoArticulo(Integer.parseInt(cantidad),updatePedido.getId_pedido(),Integer.parseInt(articulo));
                                    PedidoArticuloDAO pedArtDAO = new PedidoArticuloDAO();
                                    pedArtDAO.actualizar(pedidoArt);
                                }
                            }
                            System.out.println("");
                            System.out.println("-- Pedido actualizado correctamente --");
                            break;
                    }
                    break;
                case "Articulos":
                     ArticuloDAO articuloDAO = new ArticuloDAO();
                    switch (accion) {
                        case "1":
                            muestraArticulos(articuloDAO);
                        break;
                        case "2":
                            System.out.print("Añade su descripción:");
                            String descripcion = sc.nextLine();
                            Articulo articulo = new Articulo(descripcion);
                            articuloDAO.insertar(articulo);
                            System.out.print("Precio:");
                            String precio = sc.nextLine();
                            System.out.print("Existencias del artículo:");
                            String existencias = sc.nextLine();
                            FabricaDAO fabricaDAO = new FabricaDAO();
                            muestraFabricas(fabricaDAO);
                            System.out.print("Id de la fábrica:");
                            String fabrica = sc.nextLine();
                            ArticuloFabrica articuloFabrica = new ArticuloFabrica(Integer.parseInt(precio),Integer.parseInt(existencias),articuloDAO.obtenerPorDesc(descripcion).getId_articulo(),Integer.parseInt(fabrica));
                            ArticuloFabricaDAO artFabDAO = new ArticuloFabricaDAO();
                            artFabDAO.insertar(articuloFabrica);
                        break;
                        case "3":
                            muestraArticulos(articuloDAO);
                            System.out.println("-----------------------------------------------");
                            System.out.println("");
                            System.out.println("¿Que articulo desea eliminar? (Introduce su id)");
                            System.out.print("-> ");
                            String id = sc.nextLine();
                            System.out.println("-> ");
                            articuloDAO.eliminar(Integer.parseInt(id));
                        break;
                        case "4":
                            muestraArticulos(articuloDAO);
                            System.out.println("");
                            System.out.println("¿Que Artículo desea modificar? (Introduce su id)");
                            System.out.print("-> ");
                            id = sc.nextLine();
                            System.out.println("");
                            System.out.print("Descripción:");
                            descripcion = sc.nextLine();
                            articulo = new Articulo(Integer.parseInt(id),descripcion);
                            articuloDAO.actualizar(articulo);
                            System.out.print("Precio:");
                            precio = sc.nextLine();
                            System.out.print("Existencias del artículo:");
                            existencias = sc.nextLine();
                            fabricaDAO = new FabricaDAO();
                            muestraFabricas(fabricaDAO);
                            System.out.println("");
                            System.out.print("Id de la fábrica:");
                            fabrica = sc.nextLine();
                            articuloFabrica = new ArticuloFabrica(Integer.parseInt(precio),Integer.parseInt(existencias),articulo.getId_articulo(),Integer.parseInt(fabrica));
                            artFabDAO = new ArticuloFabricaDAO();
                            artFabDAO.actualizar(articuloFabrica);
                            
                            System.out.println("-- Artículo actualizado correctamente --");
                        break;
                    }
                    break;
                case "Fabricas":
                    FabricaDAO fabricaDAO = new FabricaDAO();
                    switch (accion) {
                        case "1":
                            muestraFabricas(fabricaDAO);
                        break;
                        case "2":
                            System.out.println("-- Añade una Fabrica -- ");
                            System.out.print("Teléfono: ");
                            String telefono = sc.nextLine();
                            if(telefono.length() > 9){
                                System.out.println("¡Largaria del teléfono excesiva!, Vuelve a Intentarlo");
                                break;
                            }
                            System.out.print("Articulos Provistos: ");
                            String artProv = sc.nextLine();
                            Fabrica fabrica = new Fabrica(telefono,Integer.parseInt(artProv));
                            fabricaDAO.insertar(fabrica);
                        break;
                        case "3":
                            muestraFabricas(fabricaDAO);
                            System.out.println("-----------------------------------------------");
                            System.out.println("");
                            System.out.println("¿Que fabrica desea eliminar? (Introduce su id)");
                            System.out.print("-> ");
                            String id = sc.nextLine();
                            System.out.println("");
                            fabricaDAO.eliminar(Integer.parseInt(id));
                        break;
                        case "4":
                            muestraFabricas(fabricaDAO);
                            System.out.println("");
                            System.out.println("¿Que Fabrica desea modificar? (Introduce su id)");
                            System.out.print("-> ");
                            id = sc.nextLine();
                            System.out.println("");
                            System.out.print("Teléfono: ");
                            telefono = sc.nextLine();
                            if(telefono.length() > 9){
                                System.out.println("¡Largaria del teléfono excesiva!, Vuelve a Intentarlo");
                                break;
                            }
                            System.out.print("Articulos Provistos: ");
                            artProv = sc.nextLine();
                            fabrica = new Fabrica(Integer.parseInt(id),telefono,Integer.parseInt(artProv));
                            fabricaDAO.actualizar(fabrica);
                            System.out.println("-- Fabrica actualizada correctamente --");
                        break;
                    }
                    break;
                case "Direcciones":
                    DireccionClienteDAO direccionEnvioCliDAO = new DireccionClienteDAO();
                    DireccionPedidoDAO direccionesPedDAO = new DireccionPedidoDAO();
                    switch (accion) {
                        case "1":
                            //Direccoiones Pedidos
                            muestraDireccionesPedidos(direccionesPedDAO);
                            //Direcciones Clientes
                            muestraDireccionesClientes(direccionEnvioCliDAO);
                        break;
                        case "2":
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
                            muestraDireccionesClientes(direccionEnvioCliDAO);
                            System.out.println("-----------------------------------------------");
                            System.out.println("");
                            System.out.println("¿Que dirección desea eliminar? (Introduce su id)");
                            System.out.print("-> ");
                            String id = sc.nextLine();
                            direccionEnvioCliDAO.eliminar(Integer.parseInt(id));
                        break;
                        case "4":
                            muestraDireccionesClientes(direccionEnvioCliDAO);
                            System.out.println("");
                            System.out.println("¿Que Direccion desea modificar? (Introduce su id)");
                            System.out.print("-> ");
                            id = sc.nextLine();
                            System.out.println("");
                            System.out.println("-- Actualiza la dirección de Envío -- ");
                            System.out.print("Ciudad: ");
                            ciudad = sc.nextLine();
                            System.out.print("Calle: ");
                            calle = sc.nextLine();
                            System.out.print("Comuna: ");
                            comuna = sc.nextLine();
                            System.out.print("NÃºmero: ");
                            numero = sc.nextLine();
                            nuevaDireccionEnvio = new DireccionEnvio(Integer.parseInt(id),ciudad,calle,comuna,numero);
                            direccionEnvioCliDAO.actualizar(nuevaDireccionEnvio);
                            
                            System.out.println(" ");
                            System.out.println("-- Dirección modificada correctamente --");
                            
                        break;
                    }
                break;
            }
        } catch (SQLException exc) {

        }
    }
    
    public static void muestraClientes(ClienteDAO clienteDAO)throws SQLException{
        System.out.println("-- Clientes  --");
        List<Cliente> clientes = clienteDAO.seleccionar();
        clientes.forEach(cli -> {
            System.out.println("");
            System.out.println("Id: " + cli.getId_cliente() + " - Saldo: " + cli.getSaldo() + " - Limite de Credito: " + cli.getLimiteCredito() + " - Descuento: " + cli.getDescuento()+ " - Direccion: " + cli.getDireccionEnvio());
        });
    }
    
    public static void muestraArticulos(ArticuloDAO articuloDAO)throws SQLException{
        ArticuloFabricaDAO artFabDAO = new ArticuloFabricaDAO();
        System.out.print("");
        System.out.println("-- Articulos --");
        List<Articulo> articulos = articuloDAO.seleccionar();
        articulos.forEach(art -> {
            //Seleccionar La direccion por su id
            System.out.println("");
            System.out.println("Id: " + art.getId_articulo() + " - DescripciÃ³n: " + art.getDescripcion());
        });
    }
       
    public static void muestraPedidos(PedidoDAO pedidoDAO)throws SQLException{
        System.out.println("-- Pedidos  --");
        List<Pedido> pedidos = pedidoDAO.seleccionar();
        pedidos.forEach(ped -> {
            System.out.println("");
            System.out.println("Id: " + ped.getId_pedido() + " - Fecha: " + ped.getFecha() + " - Direccion de Envio: " + ped.getDireccionEnvio() + " - Cliente: "+ped.getIdCliente());
        });
    }
    
    public static void muestraArticulosDePedido(PedidoDAO pedidoDAO,ArticuloDAO artDAO)throws SQLException{
    System.out.println("-- Pedidos  --");
    List<Pedido> pedidos = pedidoDAO.seleccionar();
    //
    //Falta Mejorar (Que muestre cada articulo de un pedido)
    //
    pedidos.forEach(ped -> {
        try {
            List<Articulo> articulos = artDAO.obtenerDeUnPedido(ped);
            //Seleccionar La direccion por su id
            System.out.println("");
            System.out.println("Id: " + ped.getId_pedido() + " - Fecha: " + ped.getFecha() + " - Direccion de Envio: " + ped.getDireccionEnvio() + " - Cliente: "+ped.getIdCliente());
            System.out.println("--------------------- Artículos ---------------------");
            articulos.forEach(art -> {
                System.out.println("    Id: " + art.getId_articulo()+ " - Descripción: " + art.getDescripcion());
                System.out.println("    --------------------------------------------");
            });
        } catch (SQLException ex) {
            Logger.getLogger(JDBC_pedidosclients.class.getName()).log(Level.SEVERE, null, ex);
        }
        });
    }
    
    public static void muestraFabricas(FabricaDAO fabricaDAO)throws SQLException{
        System.out.println("-- Fabricas --");
        List<Fabrica> fabricas = fabricaDAO.seleccionar();
        fabricas.forEach(fab -> {
            //Seleccionar La direccion por su id
            System.out.println("");
            System.out.println("Id: " + fab.getId_fabrica() + " - Telefono: " + fab.getTelefono()+ " - Articulos Provistos: " + fab.getArticulos_provistos());
        });
    }
     
    public static void muestraDireccionesClientes(DireccionClienteDAO dirCliDAO)throws SQLException{
        System.out.println("-- Direcciones de Clientes --");
        List<DireccionEnvio> direccionesCliente = dirCliDAO.seleccionar();
        direccionesCliente.forEach(dir -> {
            System.out.println("");
            System.out.println("Id: " + dir.getId_direccion()+ " - Ciudad: " + dir.getCiudad()+ " - Calle: " + dir.getCalle() + " - Comuna: "+ dir.getComuna() + " - NÃºmero: "+dir.getNumero());
        });
    }
    
    public static void muestraDireccionesPedidos(DireccionPedidoDAO direccionesPedDAO)throws SQLException{
        System.out.println("-- Direcciones de Pedidos --");
        List<DireccionEnvio> direccionesPed = direccionesPedDAO.seleccionar();
        direccionesPed.forEach(direccion -> {
            System.out.println("");
            System.out.println("Id: " + direccion.getId_direccion()+ " - Ciudad: " + direccion.getCiudad() + " - Calle: " + direccion.getCalle() + " - Comuna: " +direccion.getComuna() + " - NÃºmero: " + direccion.getNumero());
        });
    }
}