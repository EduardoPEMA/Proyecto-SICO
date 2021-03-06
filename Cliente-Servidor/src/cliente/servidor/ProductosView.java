/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente.servidor;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import utils.Utils;

/**
 *
 * @author isaac
 */
public class ProductosView extends javax.swing.JFrame {

    /**
     * Creates new form UsuariosView
     */
    private String descripcion = "";
    private String stock = "";
    private String precio = "";
    private String id = "";
    private boolean descripcionIsValid = true;
    private boolean stockIsValid = false;
    private boolean precioIsValid = false;
    private Utils utils = new Utils();
    
    public Connection connection;
    private DatagramSocket socket;
    
    Conexion conexion;
    
    public ProductosView() {
        try {
            initComponents();
            this.setLocationRelativeTo(null);
            this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            setEstado(false);
            permitirAccion();
            conexion = new Conexion();
            socket = new DatagramSocket();
            idInput.setEnabled(false);
            
        } catch (SocketException ex) {
            Logger.getLogger(ClientesView.class.getName()).log(Level.SEVERE, null, ex);
            
        }
    }
    
    private void setEstado(Boolean state) {
        editarButton.setEnabled(state);
        eliminarButton.setEnabled(state);
        guardarButton.setEnabled(state);
        stockInput.setEnabled(state);
        descripcionInput.setEnabled(state);
        precioInput.setEnabled(state);
        limpiarTexto();
    }
    
    public void buscar(String busqueda) {
        try {
//obtener mensaje del campo de texto y convertirlo en arreglo byte
            String mensaje = "producto" + " " + "buscar" + " " + busqueda;
            byte datos[] = mensaje.getBytes();
            DatagramPacket enviarPaquete = new DatagramPacket(datos,
                    datos.length, InetAddress.getLocalHost(), 5000);
            socket.send(enviarPaquete); //enviar paquete
        } catch (IOException exceptionES) {
            exceptionES.printStackTrace();
        }
        try {
            esperarPaquetes();
            socket = new DatagramSocket();
        } catch (SocketException excepcionSocket) {
            excepcionSocket.printStackTrace();
            System.exit(1);
        }
    }
    
    private void esperarPaquetes() {
        try {
//establecer el paquete
            byte datos[] = new byte[100];
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
            if (cad.equals("Ya existe un producto con esa descripcion")) {
                JOptionPane.showMessageDialog(null, cad);
                return;
            }
            if (cad.equals("El producto ha sido registrado con exito")) {
                buscar(descripcionInput.getText());
                guardarButton.setEnabled(false);
                JOptionPane.showMessageDialog(null, cad);
                return;
            }
            if (cad.equals("Producto editado con exito")) {
                JOptionPane.showMessageDialog(null, cad);
                return;
            }
            if (cad.equals("Producto eliminado con exito")) {
                JOptionPane.showMessageDialog(null, cad);
                return;
            }
            setEstado(true);
            String[] variables;
            variables = cad.split(" ");
            idInput.setText(variables[0]);
            descripcionInput.setText(variables[1]);
            stockInput.setText(variables[2]);
            precioInput.setText(variables[3]);
            guardarButton.setEnabled(false);
            
        } catch (IOException excepcion) {
            excepcion.printStackTrace();
        }
    }//fin

    public void limpiarTexto() {
        stockInput.setText("");
        descripcionInput.setText("");
        precioInput.setText("");
        buscarInput.setText("");
        idInput.setText("");
        buscarButton.setEnabled(false);
    }
    
    private boolean getEstados() {
        return descripcionIsValid && stockIsValid && precioIsValid;
    }
    
    private void permitirAccion() {
        boolean permitir = getEstados();
        guardarButton.setEnabled(permitir);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buscarInput = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        stockInput = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        idInput = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        descripcionInput = new javax.swing.JTextField();
        buscarButton = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        precioInput = new javax.swing.JTextField();
        cancelarButton = new javax.swing.JButton();
        nuevoButton = new javax.swing.JButton();
        guardarButton = new javax.swing.JButton();
        editarButton = new javax.swing.JButton();
        eliminarButton = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        buscarInput.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                buscarInputKeyReleased(evt);
            }
        });
        getContentPane().add(buscarInput, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 40, 270, 40));

        jLabel4.setFont(new java.awt.Font("Bahnschrift", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Nombre descripci??n del producto");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 20, -1, -1));

        jLabel5.setFont(new java.awt.Font("Bahnschrift", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Stock");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 160, -1, -1));

        stockInput.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                stockInputKeyReleased(evt);
            }
        });
        getContentPane().add(stockInput, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 180, 110, 40));

        jLabel6.setFont(new java.awt.Font("Bahnschrift", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("ID");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, -1, -1));

        idInput.setEditable(false);
        getContentPane().add(idInput, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, 110, 40));

        jLabel7.setFont(new java.awt.Font("Bahnschrift", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Usuario");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, -1, -1));

        descripcionInput.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                descripcionInputKeyReleased(evt);
            }
        });
        getContentPane().add(descripcionInput, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, 270, 40));

        buscarButton.setFont(new java.awt.Font("Bahnschrift", 1, 11)); // NOI18N
        buscarButton.setText("Buscar");
        buscarButton.setEnabled(false);
        buscarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscarButtonActionPerformed(evt);
            }
        });
        buscarButton.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                buscarButtonKeyReleased(evt);
            }
        });
        getContentPane().add(buscarButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 40, 130, 40));

        jLabel8.setFont(new java.awt.Font("Bahnschrift", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Descripci??n");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 90, -1, -1));

        jLabel9.setFont(new java.awt.Font("Bahnschrift", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Precio");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 230, -1, -1));

        precioInput.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                precioInputKeyReleased(evt);
            }
        });
        getContentPane().add(precioInput, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 250, 110, 40));

        cancelarButton.setFont(new java.awt.Font("Bahnschrift", 1, 11)); // NOI18N
        cancelarButton.setText("Cancelar");
        cancelarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelarButtonActionPerformed(evt);
            }
        });
        getContentPane().add(cancelarButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 400, 110, 40));

        nuevoButton.setFont(new java.awt.Font("Bahnschrift", 1, 11)); // NOI18N
        nuevoButton.setText("Nuevo");
        nuevoButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nuevoButtonActionPerformed(evt);
            }
        });
        getContentPane().add(nuevoButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 400, 110, 40));

        guardarButton.setFont(new java.awt.Font("Bahnschrift", 1, 11)); // NOI18N
        guardarButton.setText("Guardar");
        guardarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guardarButtonActionPerformed(evt);
            }
        });
        getContentPane().add(guardarButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 400, 110, 40));

        editarButton.setFont(new java.awt.Font("Bahnschrift", 1, 11)); // NOI18N
        editarButton.setText("Editar");
        editarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editarButtonActionPerformed(evt);
            }
        });
        getContentPane().add(editarButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 400, 110, 40));

        eliminarButton.setFont(new java.awt.Font("Bahnschrift", 1, 11)); // NOI18N
        eliminarButton.setText("Eliminar");
        eliminarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eliminarButtonActionPerformed(evt);
            }
        });
        getContentPane().add(eliminarButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 400, 110, 40));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/whiteLogo.png"))); // NOI18N
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 160, 170, 160));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/bg-dblue.jpg"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 830, 480));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buscarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscarButtonActionPerformed
        // TODO add your handling code here:
        buscar(buscarInput.getText());
    }//GEN-LAST:event_buscarButtonActionPerformed

    private void cancelarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelarButtonActionPerformed
        // TODO add your handling code here:
        limpiarTexto();
        permitirAccion();
        setEstado(false);
    }//GEN-LAST:event_cancelarButtonActionPerformed

    private void nuevoButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nuevoButtonActionPerformed
        // TODO add your handling code here:
        setEstado(true);
        editarButton.setEnabled(false);
        eliminarButton.setEnabled(false);

    }//GEN-LAST:event_nuevoButtonActionPerformed

    private void guardarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guardarButtonActionPerformed
        // TODO add your handling code here:
        try {
            descripcion = descripcionInput.getText();
            stock = stockInput.getText();
            precio = precioInput.getText();
            String mensaje = "producto" + " " + "insertar" + " " + descripcion + " " + stock + " " + precio;
            byte datos[] = mensaje.getBytes();
            DatagramPacket enviarPaquete = new DatagramPacket(datos,
                    datos.length, InetAddress.getLocalHost(), 5000);
            socket.send(enviarPaquete);//enviar paquete
        } catch (IOException exceptionES) {
            exceptionES.printStackTrace();
        }
        try {
            esperarPaquetes();
            socket = new DatagramSocket();
        } catch (SocketException excepcionSocket) {
            excepcionSocket.printStackTrace();
            System.exit(1);
        }
    }//GEN-LAST:event_guardarButtonActionPerformed

    private void editarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editarButtonActionPerformed
        // TODO add your handling code here:
        if (idInput.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "No se encontr?? el producto a editar",
                    "Error :(", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            descripcion = descripcionInput.getText();
            stock = stockInput.getText();
            precio = precioInput.getText();
            id = idInput.getText();
            String mensaje = "producto" + " " + "editar" + " " + descripcion + " " + stock + " " + precio + " " + id;
            byte datos[] = mensaje.getBytes();
            DatagramPacket enviarPaquete = new DatagramPacket(datos,
                    datos.length, InetAddress.getLocalHost(), 5000);
            socket.send(enviarPaquete);//enviar paquete
        } catch (IOException exceptionES) {
            exceptionES.printStackTrace();
        }
        try {
            esperarPaquetes();
            socket = new DatagramSocket();
        } catch (SocketException excepcionSocket) {
            excepcionSocket.printStackTrace();
            System.exit(1);
        }
    }//GEN-LAST:event_editarButtonActionPerformed

    private void eliminarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminarButtonActionPerformed
        // TODO add your handling code here:
        if (idInput.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "No se encontr?? el producto a editar",
                    "Error :(", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            id = idInput.getText();
            String mensaje = "producto" + " " + "eliminar" + " " + id;
            byte datos[] = mensaje.getBytes();
            DatagramPacket enviarPaquete = new DatagramPacket(datos,
                    datos.length, InetAddress.getLocalHost(), 5000);
            socket.send(enviarPaquete);//enviar paquete
            limpiarTexto();
        } catch (IOException exceptionES) {
            exceptionES.printStackTrace();
        }
        try {
            esperarPaquetes();
            socket = new DatagramSocket();
        } catch (SocketException excepcionSocket) {
            excepcionSocket.printStackTrace();
            System.exit(1);
        }
    }//GEN-LAST:event_eliminarButtonActionPerformed

    private void descripcionInputKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_descripcionInputKeyReleased
        String aux = descripcionInput.getText();
        permitirAccion();
    }//GEN-LAST:event_descripcionInputKeyReleased

    private void stockInputKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_stockInputKeyReleased
        String aux = stockInput.getText();
        stockIsValid = utils.isNumber(aux);
        permitirAccion();
    }//GEN-LAST:event_stockInputKeyReleased

    private void precioInputKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_precioInputKeyReleased
        // TODO add your handling code here:
        String aux = precioInput.getText();
        precioIsValid = utils.isNumber(aux);
        permitirAccion();
    }//GEN-LAST:event_precioInputKeyReleased

    private void buscarButtonKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_buscarButtonKeyReleased
        

    }//GEN-LAST:event_buscarButtonKeyReleased

    private void buscarInputKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_buscarInputKeyReleased
// TODO add your handling code here:
        String aux = buscarInput.getText();
        if (!aux.equals("")) {
            buscarButton.setEnabled(true);
        } else {
            buscarButton.setEnabled(false);
        }
    }//GEN-LAST:event_buscarInputKeyReleased

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
            java.util.logging.Logger.getLogger(ProductosView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ProductosView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ProductosView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ProductosView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ProductosView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buscarButton;
    private javax.swing.JTextField buscarInput;
    private javax.swing.JButton cancelarButton;
    private javax.swing.JTextField descripcionInput;
    private javax.swing.JButton editarButton;
    private javax.swing.JButton eliminarButton;
    private javax.swing.JButton guardarButton;
    private javax.swing.JTextField idInput;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JButton nuevoButton;
    private javax.swing.JTextField precioInput;
    private javax.swing.JTextField stockInput;
    // End of variables declaration//GEN-END:variables
}
