/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente.servidor;

import classes.Clientes;
import classes.Producto;
import classes.Usuarios;
import databases.DBCliente;
import databases.DBProducto;
import databases.DBUsuarios;
import java.io.*;
import java.net.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import javax.swing.*;

public class Servidor extends javax.swing.JFrame {

    /**
     * Creates new form Servidor
     */
    private JTextArea areaPantalla;
    private DatagramSocket socket;
    public Connection conn;
    Conexion con;
    String mensaje = "";
    String mensajeAlerta = "";
    String result = "";
    ResultSet rs;

    PreparedStatement statement;

    public Servidor() {
        super("Servidor");
        areaPantalla = new JTextArea();
        getContentPane().add(new JScrollPane(areaPantalla),
                BorderLayout.CENTER);
        setSize(400, 300);
        setVisible(true);
        this.setLocationRelativeTo(null);
//crear objeto DatagramaSocket para enviar y recibir paquetes
        try {
            socket = new DatagramSocket(5000);
            con = new Conexion();
        } //procesar los problemas que puedan ocurrir al crear el objeto DatagramSocket
        catch (SocketException excepcionSocket) {
            excepcionSocket.printStackTrace();
            System.exit(1);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    private void esperarPaquetes() throws SQLException {
        while (true) {//iterar infinitamente
            try {
                // establecer el paquete
                byte datos[] = new byte[100];
                DatagramPacket recibirPaquete
                        = new DatagramPacket(datos, datos.length);
                socket.receive(recibirPaquete);//espera al paquete
                if (recibirPaquete.getLength() != 0) {
                    //JOptionPane.showMessageDialog(this, "Solicitud de operación");
                    String cad = (new String(recibirPaquete.getData(),
                            0, recibirPaquete.getLength()));
                    String[] variables;
                    variables = cad.split(" ");
                    Usuarios us = new Usuarios();
                    Clientes cli = new Clientes();
                    Producto pro = new Producto();
                    System.out.println("Enviar-cliente " + recibirPaquete);
                    conn = con.Conexion();
                    mensaje = variables[0];
                    switch (variables[0]) {
                        case "usuario": {
                            switch (variables[1]) {
                                case "insertar": {
                                    mensajeAlerta = "insertado";

                                    us.setNombre(variables[2]);
                                    us.setUsername(variables[3]);
                                    us.setRol(variables[4]);
                                    us.setPassword(variables[5]);

                                    DBUsuarios db = new DBUsuarios(conn);
                                    db.guardarUsuario(us);
                                    break;
                                }
                                case "buscar": {
                                    result = "";
                                    mensajeAlerta = "encontrado";
                                    us.setNombre(variables[2]);
                                    DBUsuarios db = new DBUsuarios(conn);
                                    mensaje = db.buscarUsuario(us);
                                    if (mensaje.length() == 0) {
                                        mensaje = "No hay resultados";
                                        mensajeAlerta = "No hay resultados";
                                        mostrarMensaje("Sin resultados");
                                    }
                                    break;
                                }
                                case "editar": {
                                    System.out.println(variables[3]);
                                    System.out.println(variables[4]);
                                    System.out.println(variables[5]);
                                    System.out.println(variables[6]);
                                    mensajeAlerta = "editado";
                                    us.setNombre(variables[2]);
                                    us.setUsername(variables[3]);
                                    us.setPassword(variables[4]);
                                    us.setRol(variables[5]);
                                    us.setId(Integer.parseInt(variables[6]));
                                    DBUsuarios db = new DBUsuarios(conn);
                                    db.editarUsuario(us);
                                    break;
                                }
                                case "eliminar": {
                                    mensajeAlerta = "eliminar";
                                    us.setId(Integer.parseInt(variables[2]));

                                    DBUsuarios db = new DBUsuarios(conn);
                                    db.eliminarUsuario(us);
                                    break;
                                }
                                case "login": {
                                    mensajeAlerta = "login";
                                    us.setUsername(variables[2]);
                                    us.setPassword(variables[3]);
                                    DBUsuarios db = new DBUsuarios(conn);
                                    mensaje = db.login(us);

                                    if (mensaje.length() == 0) {
                                        mensaje = "No hay resultados";
                                        mensajeAlerta = "No hay resultados";
                                        mostrarMensaje("Sin resultados");
                                    }
                                    break;
                                }
                            }
                            break;
                        }
                        case "cliente": {
                            switch (variables[1]) {
                                case "insertar": {
                                    mensajeAlerta = "insertado";
                                    cli.setNombre(variables[2]);
                                    cli.setRfc(variables[3]);
                                    cli.setTelefono(variables[4]);
                                    cli.setEmail(variables[5]);
                                    DBCliente db = new DBCliente(conn);
                                    db.guardarCliente(cli);
                                    break;
                                }
                                case "buscar": {
                                    result = "";
                                    mensajeAlerta = "encontrado";
                                    cli.setNombre(variables[2]);
                                    DBCliente db = new DBCliente(conn);
                                    mensaje = db.buscarCliente(cli);
                                    if (mensaje.length() == 0) {
                                        mensaje = "No hay resultados";
                                        mensajeAlerta = "No hay resultados";
                                        mostrarMensaje("Sin resultados");
                                    }
                                    break;
                                }
                                case "editar": {
                                    mensajeAlerta = "editado";
                                    cli.setNombre(variables[2]);
                                    cli.setRfc(variables[3]);
                                    cli.setTelefono(variables[4]);
                                    cli.setEmail(variables[5]);
                                    cli.setId(Integer.parseInt(variables[6]));
                                    DBCliente db = new DBCliente(conn);
                                    db.editarCliente(cli);
                                    break;
                                }
                            }
                            break;
                        }
                        case "producto": {
                            switch (variables[1]) {
                                case "insertar": {
                                    mensajeAlerta = "insertado";

                                    pro.setDescripcion(variables[2]);
                                    pro.setStock(variables[3]);
                                    pro.setPrecio(variables[4]);
                                    DBProducto db = new DBProducto(conn);
                                    db.guardarProducto(pro);
                                    break;
                                }
                                case "buscar": {
                                    result = "";
                                    mensajeAlerta = "encontrado";
                                    pro.setDescripcion(variables[2]);
                                    DBProducto db = new DBProducto(conn);
                                    mensaje = db.buscarProducto(pro);

                                    if (mensaje.length() == 0) {
                                        mensaje = "No hay resultados";
                                        mensajeAlerta = "No hay resultados";
                                        mostrarMensaje("Sin resultados");
                                    }
                                    break;
                                }
                                case "editar": {
                                    mensajeAlerta = "editado";
                                    pro.setDescripcion(variables[2]);
                                    pro.setStock(variables[3]);
                                    pro.setPrecio(variables[4]);
                                    pro.setId(Integer.parseInt(variables[5]));
                                    DBProducto db = new DBProducto(conn);
                                    db.editarProducto(pro);
                                    break;
                                }
                                case "eliminar": {
                                    mensajeAlerta = "eliminar";
                                    pro.setId(Integer.parseInt(variables[2]));
                                    DBProducto db = new DBProducto(conn);
                                    db.eliminarProducto(pro);
                                    break;
                                }
                            }
                            break;
                        }
                    }
                }
                //mostrar la informacion del paquete recibido
                if (mensajeAlerta.length() > 0) {
                    mostrarMensaje("\nRegistro " + mensajeAlerta + ":"
                            + "\nDel host:" + recibirPaquete.getPort()
                            + "\nInformacion almacenada:" + new String(recibirPaquete.getData(),
                                    0, recibirPaquete.getLength()) + datos
                    );
                    enviarPaqueteACliente(recibirPaquete);//envida paquete al cliente
                }

            } //procesar los problemas que pu edan ocurrir al manipular el paquete
            catch (IOException excepcionES) {
                mostrarMensaje(excepcionES.toString() + "\n");
                excepcionES.printStackTrace();
            }

        }
    }

    //repetir el paquete al cliente
    private void enviarPaqueteACliente(DatagramPacket recibirPaquete) throws IOException {
        mostrarMensaje("\n\nRepitiendo datos al cliente...");
        byte datos[] = new byte[100];
        System.out.println("Mensaje: " + mensaje);

        datos = mensaje.getBytes();
//crea paquete a enviarrecibirPaquete
        DatagramPacket enviarPaquete = new DatagramPacket(
                datos, datos.length,
                recibirPaquete.getAddress(), recibirPaquete.getPort());
        socket.send(enviarPaquete);//enviar el paquete
        mostrarMensaje("Paquete enviado\n");
    }// fin del metodo enviarPaqueteACliente
//metodo utilitario que es llamado desde otros subprocesos para manipular a
//area pantalla en el subproceso despachador de eventos

    private void mostrarMensaje(final String mensajeAMostrar) {
//mostrar el mensaje del subproceso de ejecucion despachador de eventos
        SwingUtilities.invokeLater(
                new Runnable() {//clase interna para asegurar que la giu se actualice apropiadamente
            public void run()//actualiza areaPantalla
            {
                areaPantalla.append(mensajeAMostrar);
                areaPantalla.setCaretPosition(
                        areaPantalla.getText().length());
            }
        }//fin de la clase interna
        );//fin de la llamada a SwingUtilities.invokeLater
    }//fin del metodo mostrarMensaje

    public static void main(String args[]) throws SQLException {
        Servidor aplicacion = new Servidor();
        aplicacion.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        aplicacion.esperarPaquetes();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
