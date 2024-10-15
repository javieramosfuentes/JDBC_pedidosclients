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
        System.out.println("Cualquier otra tecla (Salir)");
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
                            System.out.println("¿Que cliente desea eliminar? (Introduce su id)");
                            System.out.print("-> ");
                            String id = sc.nextLine();
                            clienteDAO.eliminar(Integer.parseInt(id));
                            break;
                        case "4":
                            System.out.println("-- Clientes --");
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
                    switch (accion) {
                        case "1":
                            muestraPedidos(pedidoDAO,artDAO);
                            break;
                        case "2":
                            Pedido pedido = new Pedido();
                            ClienteDAO cliDAO = new ClienteDAO();
                            Date fecha = new Date();
                            java.sql.Date fechaSql = new java.sql.Date(fecha.getTime());
                            pedido.setFecha(fechaSql);
                            List<Cliente> clientes = cliDAO.seleccionar();
                            System.out.println("-- Direcciones  --");
                            clientes.forEach(cli -> {
                                System.out.println("");
                                System.out.println("Id: " + cli.getId_cliente() + " - Saldo: " + cli.getSaldo() + " - Limite de Credito: " + cli.getLimiteCredito() + " - Descuento: " + cli.getDescuento()+ " - Direccion: " + cli.getDireccionEnvio());
                            });
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
                                    List<DireccionEnvio> direccionesCliente = dirCliDAO.seleccionar();
                                    System.out.println("-- Direcciones  --");
                                    direccionesCliente.forEach(dir -> {
                                        System.out.println("");
                                        System.out.println("Id: " + dir.getId_direccion()+ " - Ciudad: " + dir.getCiudad()+ " - Calle: " + dir.getCalle() + " - Comuna: "+ dir.getComuna() + " - NÃºmero: "+dir.getNumero());
                                    });
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
                                    List<Articulo> articulos = artDAO.seleccionar();
                                    System.out.println("-- Artículos  --");
                                    articulos.forEach(art -> {
                                        System.out.println("");
                                        System.out.println("Id: " + art.getId_articulo()+ " - Descripción: " + art.getDescripcion());
                                    });
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
                            System.out.println("-- Pedidos --");
                            muestraPedidos(pedidoDAO, artDAO);
                            System.out.println("-----------------------------------------------");
                            System.out.println("");
                            System.out.println("¿Que pedido desea eliminar? (Introduce su id)");
                            System.out.print("-> ");
                            String id = sc.nextLine();
                            pedidoDAO.eliminar(Integer.parseInt(id));
                            break;
                        case "4":
                            //Falta de implementar update
                            break;
                    }
                    break;
                        //Seleccionar La direccion por su id
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
                            
                            
                            System.out.print("Id de la fábrica:");
                            String fabrica = sc.nextLine();
                            ArticuloFabrica articuloFabrica = new ArticuloFabrica(Integer.parseInt(precio),Integer.parseInt(existencias),articuloDAO.obtenerPorDesc(descripcion).getId_articulo(),Integer.parseInt(fabrica));
                            ArticuloFabricaDAO artFabDAO = new ArticuloFabricaDAO();
                            artFabDAO.insertar(articuloFabrica);
                        break;
                        case "3":
                            System.out.println("-- Articulos --");
                            muestraArticulos(articuloDAO);
                            System.out.println("-----------------------------------------------");
                            System.out.println("");
                            System.out.println("¿Que articulo desea eliminar? (Introduce su id)");
                            System.out.print("-> ");
                            String id = sc.nextLine();
                            articuloDAO.eliminar(Integer.parseInt(id));
                        break;
                        case "4":
                            //Falta de implementar update
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
                            System.out.print("Articulos Provistos: ");
                            String artProv = sc.nextLine();
                            Fabrica fabrica = new Fabrica(telefono,Integer.parseInt(artProv));
                            fabricaDAO.insertar(fabrica);
                        break;
                        case "3":
                            System.out.println("-- Fabricas --");
                            muestraFabricas(fabricaDAO);
                            System.out.println("-----------------------------------------------");
                            System.out.println("");
                            System.out.println("¿Que fabrica desea eliminar? (Introduce su id)");
                            System.out.print("-> ");
                            String id = sc.nextLine();
                            fabricaDAO.eliminar(Integer.parseInt(id));
                        break;
                        case "4":
                            //Falta de implementar update
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
                            System.out.println("-- Direcciones de los Clientes --");
                            muestraDireccionesClientes(direccionEnvioCliDAO);
                            System.out.println("-----------------------------------------------");
                            System.out.println("");
                            System.out.println("¿Que dirección desea eliminar? (Introduce su id)");
                            System.out.print("-> ");
                            String id = sc.nextLine();
                            direccionEnvioCliDAO.eliminar(Integer.parseInt(id));
                        break;
                        case "4":
                            //Falta de implementar update
                        break;
                    }
                break;
            }
        } catch (SQLException exc) {

        }
    }
    
    public static void muestraClientes(ClienteDAO clienteDAO)throws SQLException{
        List<Cliente> clientes = clienteDAO.seleccionar();
        clientes.forEach(cli -> {
            System.out.println("");
            System.out.println("Id: " + cli.getId_cliente() + " - Saldo: " + cli.getSaldo() + " - Limite de Credito: " + cli.getLimiteCredito() + " - Descuento: " + cli.getDescuento()+ " - Direccion: " + cli.getDireccionEnvio());
        });
    }
    
    public static void muestraArticulos(ArticuloDAO articuloDAO)throws SQLException{
        List<Articulo> articulos = articuloDAO.seleccionar();
        articulos.forEach(art -> {
            //Seleccionar La direccion por su id
            System.out.println("");
            System.out.println("Id: " + art.getId_articulo() + " - DescripciÃ³n: " + art.getDescripcion());
        });
    }
       
    public static void muestraPedidos(PedidoDAO pedidoDAO,ArticuloDAO artDAO)throws SQLException{
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
        List<Fabrica> fabricas = fabricaDAO.seleccionar();
        fabricas.forEach(fab -> {
            //Seleccionar La direccion por su id
            System.out.println("");
            System.out.println("Id: " + fab.getId_fabrica() + " - Telefono: " + fab.getTelefono()+ " - Articulos Provistos: " + fab.getArticulos_provistos());
        });
    }
     
    public static void muestraDireccionesClientes(DireccionClienteDAO dirCliDAO)throws SQLException{
        List<DireccionEnvio> direccionesCliente = dirCliDAO.seleccionar();
        System.out.println("-- Direcciones  --");
        direccionesCliente.forEach(dir -> {
            System.out.println("");
            System.out.println("Id: " + dir.getId_direccion()+ " - Ciudad: " + dir.getCiudad()+ " - Calle: " + dir.getCalle() + " - Comuna: "+ dir.getComuna() + " - NÃºmero: "+dir.getNumero());
        });
    }
    
    public static void muestraDireccionesPedidos(DireccionPedidoDAO direccionesPedDAO)throws SQLException{
        List<DireccionEnvio> direccionesPed = direccionesPedDAO.seleccionar();
        System.out.println("");
        System.out.println("-------- Direcciones de los Pedidos  --------");
        direccionesPed.forEach(direccion -> {
            System.out.println("");
            System.out.println("Id: " + direccion.getId_direccion()+ " - Ciudad: " + direccion.getCiudad() + " - Calle: " + direccion.getCalle() + " - Comuna: " +direccion.getComuna() + " - NÃºmero: " + direccion.getNumero());
        });
    }
}