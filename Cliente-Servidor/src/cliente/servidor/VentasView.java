/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente.servidor;

import classes.Clientes;
import classes.Producto;
import classes.Venta;
import com.toedter.calendar.JTextFieldDateEditor;
import databases.DBVenta;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.sql.Connection;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.Date;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import utils.Utils;

/**
 *
 * @author Eduardo
 */
public class VentasView extends javax.swing.JFrame {

    /**
     * Creates new form Ventas
     */
    DefaultTableModel tablaVentas;
    public Connection connection;
    private DatagramSocket socket;
    private ArrayList<Producto> productosArray = new ArrayList<Producto>();
    private ArrayList<Clientes> clientesArray = new ArrayList<Clientes>();

    Utils utils = new Utils();
    Conexion conexion;
    String aux;

    public VentasView() {
        try {
            initComponents();
            this.setLocationRelativeTo(null);
            this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            tablaVentas = (DefaultTableModel) ticketTable.getModel();
            ticketTable.setAutoCreateRowSorter(true);
            ticketTable.getRowSorter().toggleSortOrder(0);
            conexion = new Conexion();
            socket = new DatagramSocket();
            clienteIdInput.setEnabled(false);
            productoIdInput.setEnabled(false);
            agregarButton.setEnabled(false);
            setEstado(false);
            getCatalogoProductos();
            getCatalogoClientes();
            updatePrices();
            Date date = new Date();
            jDateChooser1.setDate(date);
            jDateChooser1.setEnabled(false);

        } catch (SocketException ex) {
            Logger.getLogger(VentasView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void limpiarTexto() {
        precioInput.setText("");
        folioInput.setText("");
        cantidadInput.setText("");
        precioInput.setText("");
        totalInput1.setText("");
        ivaInput1.setText("");
        subtotalInput.setText("");
        clienteIdInput.setText("");
        productoIdInput.setText("");
        productoInput.setSelectedIndex(-1);
        clienteInput.setSelectedIndex(-1);
        tablaVentas.setRowCount(0);
    }

    private void getCatalogoProductos() {
        try {
//obtener mensaje del campo de texto y convertirlo en arreglo byte
            String mensaje = "producto" + " " + "listar" + " ";
            byte datos[] = mensaje.getBytes();
            DatagramPacket enviarPaquete = new DatagramPacket(datos,
                    datos.length, InetAddress.getLocalHost(), 5000);
            socket.send(enviarPaquete); //enviar paquete
        } catch (IOException exceptionES) {
            exceptionES.printStackTrace();
        }
        try {
            esperarPaquetes("productos");
            socket = new DatagramSocket();
        } catch (SocketException excepcionSocket) {
            excepcionSocket.printStackTrace();
            System.exit(1);
        }
    }

    private void setEstado(Boolean state) {
        clienteInput.setEnabled(state);
        productoInput.setEnabled(state);
        cantidadInput.setEnabled(state);
        quitarButton.setEnabled(state);
        finalizarrButton.setEnabled(state);
    }

    private void getCatalogoClientes() {
        try {
//obtener mensaje del campo de texto y convertirlo en arreglo byte
            String mensaje = "cliente" + " " + "listar" + " ";
            byte datos[] = mensaje.getBytes();
            DatagramPacket enviarPaquete = new DatagramPacket(datos,
                    datos.length, InetAddress.getLocalHost(), 5000);
            socket.send(enviarPaquete); //enviar paquete
        } catch (IOException exceptionES) {
            exceptionES.printStackTrace();
        }
        try {
            esperarPaquetes("clientes");
            socket = new DatagramSocket();
        } catch (SocketException excepcionSocket) {
            excepcionSocket.printStackTrace();
            System.exit(1);
        }
    }

    private void esperarPaquetes(String catalog) {
        try {
//establecer el paquete
            byte datos[] = new byte[20000];
            DatagramPacket recibirPaquete = new DatagramPacket(
                    datos, datos.length);
            socket.receive(recibirPaquete);//esperar un paquete
            if (recibirPaquete.getLength() == 0) {
                limpiarTexto();
                this.dispose();
            }
            String cad = (new String(recibirPaquete.getData(),
                    0, recibirPaquete.getLength()));
            if (cad.equals("No hay resultados")) {
                JOptionPane.showMessageDialog(null, "No hay resultados.");
                limpiarTexto();
                return;
            }
            if (cad.equals("Venta realizada con exito")) {
                JOptionPane.showMessageDialog(null, cad);
            }
            String[] variables;
            variables = cad.split(",");

            if (!catalog.equals("")) {
                if (catalog.equals("productos")) {
                    for (String s : variables) {
                        Producto p = new Producto(s);
                        productosArray.add(p);
                        productoInput.addItem(p.getDescripcion());
                    }
                }
                if (catalog.equals("clientes")) {
                    for (String s : variables) {
                        Clientes c = new Clientes(s);
                        clientesArray.add(c);
                        clienteInput.addItem(c.getNombre());
                    }
                }
            }
        } catch (IOException excepcion) {
            excepcion.printStackTrace();
        }
    }//fin

    private Boolean stockDisponible(int cantidad) {
        String producto = productoInput.getSelectedItem().toString();
        Producto p = encontrarProductos(producto);
        return Integer.parseInt(p.getStock()) >= cantidad;
    }

    private void updateRow(int index, int sumaCantidad) {
        String descripcion = tablaVentas.getValueAt(index, 1).toString();
        double precio = Double.parseDouble(tablaVentas.getValueAt(index, 2).toString());
        int cantidad = Integer.parseInt(tablaVentas.getValueAt(index, 3).toString()) + sumaCantidad;
        if (!stockDisponible(cantidad)) {
            JOptionPane.showMessageDialog(null, "No hay suficiente stock disponible.");
            return;
        }
        double importe = precio * cantidad;
        tablaVentas.removeRow(index);
        tablaVentas.insertRow(index, new Object[]{String.valueOf(index + 1), descripcion, String.valueOf(precio), String.valueOf(cantidad), String.valueOf(importe)});
    }

    private void addRow() {
        int index = tablaVentas.getRowCount() + 1;
        String descripcion = "";
        double precio = 0.0;
        int cantidad = Integer.parseInt(cantidadInput.getText());
        double importe = 0.0;
        for (Producto p : productosArray) {
            if (String.valueOf(p.getId()).equals(productoIdInput.getText())) {
                descripcion = p.getDescripcion();
                precio = Double.parseDouble(p.getPrecio());
            }
        }
        for (int count = 0; count < tablaVentas.getRowCount(); count++) {
            if (tablaVentas.getValueAt(count, 1).toString().equals(descripcion)) {
                updateRow(count, Integer.parseInt(cantidadInput.getText()));
                return;
            };
        }
        importe = cantidad * precio;
        tablaVentas.addRow(new Object[]{String.valueOf(index), descripcion, String.valueOf(precio), String.valueOf(cantidad), String.valueOf(importe)});
    }

    private void updatePrices() {
        double subtotal = 0;
        double iva = 0;
        double total = 0;
        DecimalFormat df = new DecimalFormat("0.00");
        for (int count = 0; count < tablaVentas.getRowCount(); count++) {
            subtotal += Double.parseDouble(tablaVentas.getValueAt(count, 4).toString());
        }
        iva = subtotal * 0.16;
        total = subtotal + iva;
        subtotalInput.setText(String.valueOf(df.format(subtotal)));
        ivaInput1.setText(String.valueOf(df.format(iva)));
        totalInput1.setText(String.valueOf(df.format(total)));
    }

    private Producto encontrarProductos(String value) {
        for (Producto p : productosArray) {
            if (p.getDescripcion().equals(value)) {
                return p;
            }
        }
        return new Producto();
    }

    private String generarListaProductos() {
        String lista = "";
        for (int count = 0; count < tablaVentas.getRowCount(); count++) {
            Producto p = encontrarProductos(tablaVentas.getValueAt(count, 1).toString());
            lista += p.getId() + "," + tablaVentas.getValueAt(count, 3).toString() + ",";
        }
        return lista;
    }

    private void vender() {
        // TODO add your handling code here:
        try {
            //obtener mensaje del campo de texto y convertirlo en arreglo byte
            String folio = folioInput.getText();
            String fecha = String.valueOf(jDateChooser1.getDate().getTime());
            String cliente = clienteIdInput.getText();
            String total = totalInput1.getText();
            String prod = generarListaProductos();
            String mensaje = "ventas" + " " + "vender" + " " + folio + " " + fecha + " " + cliente + " " + total + " " + prod;
            System.out.println(mensaje);
            byte datos[] = mensaje.getBytes();
            DatagramPacket enviarPaquete = new DatagramPacket(datos,
                    datos.length, InetAddress.getLocalHost(), 5000);
            socket.send(enviarPaquete); //enviar paquete
        } catch (IOException exceptionES) {
            exceptionES.printStackTrace();
        }
        try {
            esperarPaquetes("");
            socket = new DatagramSocket();
        } catch (SocketException excepcionSocket) {
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

        ivaInput = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        totalInput = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        ticketTable = new javax.swing.JTable();
        precioInput = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        clienteIdInput = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        clienteInput = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        folioInput = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        productoInput = new javax.swing.JComboBox<>();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        productoIdInput = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        cantidadInput = new javax.swing.JTextField();
        nuevoButton = new javax.swing.JButton();
        finalizarrButton = new javax.swing.JButton();
        subtotalInput = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        ivaInput1 = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        totalInput1 = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        agregarButton = new javax.swing.JButton();
        quitarButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        ivaInput.setEditable(false);

        jLabel18.setFont(new java.awt.Font("Bahnschrift", 1, 14)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("IVA");

        totalInput.setEditable(false);

        jLabel19.setFont(new java.awt.Font("Bahnschrift", 1, 14)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("Total");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ticketTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Index", "Descripción", "Precio", "Cantidad", "Importe"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(ticketTable);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 267, 780, 170));

        precioInput.setEditable(false);
        precioInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                precioInputActionPerformed(evt);
            }
        });
        getContentPane().add(precioInput, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 220, 270, 30));

        jLabel11.setFont(new java.awt.Font("Bahnschrift", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Precio");
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 200, -1, -1));

        clienteIdInput.setEditable(false);
        getContentPane().add(clienteIdInput, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 160, 110, 30));

        jLabel10.setFont(new java.awt.Font("Bahnschrift", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("ClienteID");
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 140, -1, 20));

        clienteInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clienteInputActionPerformed(evt);
            }
        });
        getContentPane().add(clienteInput, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 100, 270, 30));

        jLabel8.setFont(new java.awt.Font("Bahnschrift", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Seleccione cliente");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, -1, -1));

        folioInput.setEditable(false);
        getContentPane().add(folioInput, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, 270, 30));

        jLabel6.setFont(new java.awt.Font("Bahnschrift", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Folio");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, -1, -1));

        jLabel12.setFont(new java.awt.Font("Bahnschrift", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Fecha");
        getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 20, -1, -1));

        productoInput.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                productoInputItemStateChanged(evt);
            }
        });
        productoInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                productoInputActionPerformed(evt);
            }
        });
        productoInput.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                productoInputKeyPressed(evt);
            }
        });
        getContentPane().add(productoInput, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 100, 270, 30));

        jLabel14.setFont(new java.awt.Font("Bahnschrift", 1, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Seleccione Producto");
        getContentPane().add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 80, -1, -1));

        jLabel15.setFont(new java.awt.Font("Bahnschrift", 1, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("ProductoID");
        getContentPane().add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 140, -1, 20));

        productoIdInput.setEditable(false);
        getContentPane().add(productoIdInput, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 160, 110, 30));

        jLabel16.setFont(new java.awt.Font("Bahnschrift", 1, 14)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("Cantidad");
        getContentPane().add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 200, -1, -1));

        cantidadInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cantidadInputActionPerformed(evt);
            }
        });
        cantidadInput.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cantidadInputKeyReleased(evt);
            }
        });
        getContentPane().add(cantidadInput, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 220, 110, 30));

        nuevoButton.setFont(new java.awt.Font("Bahnschrift", 1, 11)); // NOI18N
        nuevoButton.setText("Nueva venta");
        nuevoButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nuevoButtonActionPerformed(evt);
            }
        });
        getContentPane().add(nuevoButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 460, 110, 40));

        finalizarrButton.setFont(new java.awt.Font("Bahnschrift", 1, 11)); // NOI18N
        finalizarrButton.setText("Finalizar venta");
        finalizarrButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                finalizarrButtonActionPerformed(evt);
            }
        });
        getContentPane().add(finalizarrButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 460, 140, 40));

        subtotalInput.setEditable(false);
        getContentPane().add(subtotalInput, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 470, 110, 30));

        jLabel17.setFont(new java.awt.Font("Bahnschrift", 1, 14)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("Subtotal");
        getContentPane().add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 450, -1, -1));

        ivaInput1.setEditable(false);
        getContentPane().add(ivaInput1, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 470, 110, 30));

        jLabel20.setFont(new java.awt.Font("Bahnschrift", 1, 14)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setText("IVA");
        getContentPane().add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 450, -1, -1));

        totalInput1.setEditable(false);
        getContentPane().add(totalInput1, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 470, 110, 30));

        jLabel21.setFont(new java.awt.Font("Bahnschrift", 1, 14)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setText("Total");
        getContentPane().add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 450, -1, -1));
        getContentPane().add(jDateChooser1, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 40, 270, 30));

        agregarButton.setFont(new java.awt.Font("Bahnschrift", 1, 11)); // NOI18N
        agregarButton.setText("Agregar");
        agregarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agregarButtonActionPerformed(evt);
            }
        });
        getContentPane().add(agregarButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 220, 80, 30));

        quitarButton.setFont(new java.awt.Font("Bahnschrift", 1, 11)); // NOI18N
        quitarButton.setText("Eliminar producto");
        quitarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                quitarButtonActionPerformed(evt);
            }
        });
        getContentPane().add(quitarButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 220, 150, 30));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/bg-dblue.jpg"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 850, 520));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public String getId(String s) {
        if (s.contains(" ")) {
            s = s.substring(0, s.indexOf(" "));
        }
        return s;
    }
    private void clienteInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clienteInputActionPerformed
        // TODO add your handling code here:
        for (Clientes c : clientesArray) {
            if (c.getNombre().equals((String) clienteInput.getSelectedItem())) {
                clienteIdInput.setText(String.valueOf(c.getId()));
            }
        }
    }//GEN-LAST:event_clienteInputActionPerformed

    private void productoInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_productoInputActionPerformed
// TODO add your handling code here:
        for (Producto p : productosArray) {
            if (p.getDescripcion().equals((String) productoInput.getSelectedItem())) {
                productoIdInput.setText(String.valueOf(p.getId()));
                precioInput.setText(p.getPrecio());
            }
        }
    }//GEN-LAST:event_productoInputActionPerformed

    private void nuevoButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nuevoButtonActionPerformed
        // TODO add your handling code here:
        UUID id = UUID.randomUUID();
        folioInput.setText(id.toString());
        setEstado(true);
        nuevoButton.setEnabled(false);

    }//GEN-LAST:event_nuevoButtonActionPerformed

    private void finalizarrButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_finalizarrButtonActionPerformed
        if (tablaVentas.getRowCount() <= 0) {
            JOptionPane.showMessageDialog(null, "Debes agregar al menos un producto para realizar la venta");
            return;
        }
        vender();
        limpiarTexto();
        setEstado(false);
        nuevoButton.setEnabled(true);
        finalizarrButton.setEnabled(false);
        agregarButton.setEnabled(false);
        quitarButton.setEnabled(false);
    }//GEN-LAST:event_finalizarrButtonActionPerformed

    private void productoInputItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_productoInputItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_productoInputItemStateChanged

    private void productoInputKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_productoInputKeyPressed

    }//GEN-LAST:event_productoInputKeyPressed

    private void agregarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agregarButtonActionPerformed
        // TODO add your handling code here:
        if (!stockDisponible(Integer.parseInt(cantidadInput.getText()))) {
            JOptionPane.showMessageDialog(null, "No hay suficiente stock disponible.");
            return;
        }
        addRow();
        updatePrices();
    }//GEN-LAST:event_agregarButtonActionPerformed

    private void quitarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_quitarButtonActionPerformed
        int numRows = 0;
        numRows = ticketTable.getSelectedRows().length;
        if (numRows == 0) {
            JOptionPane.showMessageDialog(null, "No se ha seleccionado ninguna fila.");
        }
        for (int i = 0; i < numRows; i++) {
            tablaVentas.removeRow(ticketTable.getSelectedRow());
        }
        updatePrices();
    }//GEN-LAST:event_quitarButtonActionPerformed

    private void cantidadInputKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cantidadInputKeyReleased
        // TODO add your handling code here:
        String aux = cantidadInput.getText();
        if (utils.isNumber(aux)) {
            agregarButton.setEnabled(true);
        } else {
            agregarButton.setEnabled(false);
        }
    }//GEN-LAST:event_cantidadInputKeyReleased

    private void precioInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_precioInputActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_precioInputActionPerformed

    private void cantidadInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cantidadInputActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cantidadInputActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(VentasView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VentasView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VentasView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentasView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VentasView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton agregarButton;
    private javax.swing.JTextField cantidadInput;
    private javax.swing.JTextField clienteIdInput;
    private javax.swing.JComboBox<String> clienteInput;
    private javax.swing.JButton finalizarrButton;
    private javax.swing.JTextField folioInput;
    private javax.swing.JTextField ivaInput;
    private javax.swing.JTextField ivaInput1;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton nuevoButton;
    private javax.swing.JTextField precioInput;
    private javax.swing.JTextField productoIdInput;
    private javax.swing.JComboBox<String> productoInput;
    private javax.swing.JButton quitarButton;
    private javax.swing.JTextField subtotalInput;
    private javax.swing.JTable ticketTable;
    private javax.swing.JTextField totalInput;
    private javax.swing.JTextField totalInput1;
    // End of variables declaration//GEN-END:variables
}
