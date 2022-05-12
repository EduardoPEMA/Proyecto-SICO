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
public class ClientesView extends javax.swing.JFrame {

    /**
     * Creates new form UsuariosView
     */
    private String nombre = "";
    private String RFC = "";
    private String telefono = "";
    private String email = "";
    private String id = "";
    private boolean nombreIsValid = false;
    private boolean RFCIsValid = false;
    private boolean telefonoIsValid = false;
    private boolean emailIsValid = false;
    private Utils utils = new Utils();
    public Connection connection;
    private DatagramSocket socket;

    Conexion conexion;

    public ClientesView() {
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
        rfcInput = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        idInput = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        nombreInput = new javax.swing.JTextField();
        buscarButton = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        telefonoInput = new javax.swing.JTextField();
        cancelarButton = new javax.swing.JButton();
        nuevoButton = new javax.swing.JButton();
        guardarButton = new javax.swing.JButton();
        editarButton = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        emailInput = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(buscarInput, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 40, 270, 40));

        jLabel4.setFont(new java.awt.Font("Bahnschrift", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Nombre de cliente");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 20, -1, -1));

        jLabel5.setFont(new java.awt.Font("Bahnschrift", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("RFC");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 160, -1, -1));

        rfcInput.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                rfcInputKeyReleased(evt);
            }
        });
        getContentPane().add(rfcInput, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 180, 270, 40));

        jLabel6.setFont(new java.awt.Font("Bahnschrift", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("ID");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, -1, -1));

        idInput.setEditable(false);
        idInput.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                idInputKeyReleased(evt);
            }
        });
        getContentPane().add(idInput, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, 110, 40));

        jLabel7.setFont(new java.awt.Font("Bahnschrift", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Usuario");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, -1, -1));

        nombreInput.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                nombreInputKeyReleased(evt);
            }
        });
        getContentPane().add(nombreInput, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, 270, 40));

        buscarButton.setFont(new java.awt.Font("Bahnschrift", 1, 11)); // NOI18N
        buscarButton.setText("Buscar");
        buscarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscarButtonActionPerformed(evt);
            }
        });
        getContentPane().add(buscarButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 40, 130, 40));

        jLabel8.setFont(new java.awt.Font("Bahnschrift", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Nombre cliente");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 90, -1, -1));

        jLabel9.setFont(new java.awt.Font("Bahnschrift", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Telefono");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 230, -1, -1));

        telefonoInput.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                telefonoInputKeyReleased(evt);
            }
        });
        getContentPane().add(telefonoInput, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 250, 270, 40));

        cancelarButton.setFont(new java.awt.Font("Bahnschrift", 1, 11)); // NOI18N
        cancelarButton.setText("Cancelar");
        cancelarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelarButtonActionPerformed(evt);
            }
        });
        getContentPane().add(cancelarButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 400, 110, 40));

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

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/whiteLogo.png"))); // NOI18N
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 160, 170, 160));

        emailInput.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                emailInputKeyReleased(evt);
            }
        });
        getContentPane().add(emailInput, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 320, 270, 40));

        jLabel10.setFont(new java.awt.Font("Bahnschrift", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Email");
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 300, -1, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/bg-dblue.jpg"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 830, 480));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void setEstado(Boolean state) {
        editarButton.setEnabled(state);
        guardarButton.setEnabled(state);
        nombreInput.setEnabled(state);
        rfcInput.setEnabled(state);
        telefonoInput.setEnabled(state);
        emailInput.setEnabled(state);
        limpiarTexto();
    }

    private boolean getEstados() {
        return nombreIsValid && RFCIsValid && telefonoIsValid && emailIsValid;
    }

    private void permitirAccion() {
        if (!idInput.getText().equals("")) {
            guardarButton.setEnabled(false);
            return;
        }
        boolean permitir = getEstados();
        guardarButton.setEnabled(permitir);
    }

    public void limpiarTexto() {
        nombreInput.setText("");
        rfcInput.setText("");
        telefonoInput.setText("");
        emailInput.setText("");
        buscarInput.setText("");
        idInput.setText("");
    }

    public void buscar(String busqueda) {
        try {
//obtener mensaje del campo de texto y convertirlo en arreglo byte
            String mensaje = "cliente" + " " + "buscar" + " " + busqueda;
            JOptionPane.showMessageDialog(null, "Buscar cliente...", "Advertencia",
                    JOptionPane.WARNING_MESSAGE);
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
            setEstado(true);
            String[] variables;
            variables = cad.split(" ");
            idInput.setText(variables[0]);
            nombreInput.setText(variables[1]);
            rfcInput.setText(variables[2]);
            telefonoInput.setText(variables[3]);
            emailInput.setText(variables[4]);

        } catch (IOException excepcion) {
            excepcion.printStackTrace();
        }
    }//fin

    private void buscarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscarButtonActionPerformed
        // TODO add your handling code here:
        buscar(buscarInput.getText());
    }//GEN-LAST:event_buscarButtonActionPerformed

    private void cancelarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelarButtonActionPerformed
        limpiarTexto();
        permitirAccion();
        setEstado(false);
    }//GEN-LAST:event_cancelarButtonActionPerformed

    private void nuevoButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nuevoButtonActionPerformed
        setEstado(true);
        if (idInput.getText().equals("")) {
            editarButton.setEnabled(false);
        }
    }//GEN-LAST:event_nuevoButtonActionPerformed

    private void guardarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guardarButtonActionPerformed
        // TODO add your handling code here:
        try {
            nombre = nombreInput.getText();
            RFC = rfcInput.getText();
            telefono = telefonoInput.getText();
            email = emailInput.getText();
            String mensaje = "cliente" + " " + "insertar" + " " + nombre + " " + RFC + " " + telefono + " " + email;
            byte datos[] = mensaje.getBytes();
            JOptionPane.showMessageDialog(null, "Guardar cliente...", "Advertencia",
                    JOptionPane.WARNING_MESSAGE);
            DatagramPacket enviarPaquete = new DatagramPacket(datos,
                    datos.length, InetAddress.getLocalHost(), 5000);
            socket.send(enviarPaquete);//enviar paquete
        } catch (IOException exceptionES) {
            exceptionES.printStackTrace();
        }
        try {
            socket = new DatagramSocket();
            buscar(nombreInput.getText());
            guardarButton.setEnabled(false);
        } catch (SocketException excepcionSocket) {
            excepcionSocket.printStackTrace();
            System.exit(1);
        }
    }//GEN-LAST:event_guardarButtonActionPerformed

    private void editarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editarButtonActionPerformed
        // TODO add your handling code here:
        // TODO add your handling code here:
        if (idInput.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "No se encontró el usuario a editar",
                    "Error :(", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            nombre = nombreInput.getText();
            RFC = rfcInput.getText();
            telefono = telefonoInput.getText();
            email = emailInput.getText();
            id = idInput.getText();
            String mensaje = "cliente" + " " + "editar" + " " + nombre + " " + RFC + " " + telefono + " " + email + " " + id;
            byte datos[] = mensaje.getBytes();
            JOptionPane.showMessageDialog(null, "Editar cliente...", "Advertencia",
                    JOptionPane.WARNING_MESSAGE);
//crear enviarPaquete
            DatagramPacket enviarPaquete = new DatagramPacket(datos,
                    datos.length, InetAddress.getLocalHost(), 5000);
            socket.send(enviarPaquete);//enviar paquete
        } catch (IOException exceptionES) {
            exceptionES.printStackTrace();
        }
        try {
            socket = new DatagramSocket();
        } catch (SocketException excepcionSocket) {
            excepcionSocket.printStackTrace();
            System.exit(1);
        }
    }//GEN-LAST:event_editarButtonActionPerformed


    private void nombreInputKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nombreInputKeyReleased
        // TODO add your handling code here:
        String aux = nombreInput.getText();
        nombreIsValid = utils.isStringOnlyAlphabet(aux);
        permitirAccion();

    }//GEN-LAST:event_nombreInputKeyReleased

    private void rfcInputKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_rfcInputKeyReleased
        // TODO add your handling code here:
        String aux = rfcInput.getText();
        RFCIsValid = !aux.equals("");
        permitirAccion();
    }//GEN-LAST:event_rfcInputKeyReleased

    private void telefonoInputKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_telefonoInputKeyReleased
        // TODO add your handling code here:
        String aux = telefonoInput.getText();
        telefonoIsValid = utils.isPhoneNumber(aux);
        permitirAccion();

    }//GEN-LAST:event_telefonoInputKeyReleased

    private void emailInputKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_emailInputKeyReleased
        // TODO add your handling code here:
        String aux = emailInput.getText();
        emailIsValid = !aux.equals("");
        permitirAccion();
    }//GEN-LAST:event_emailInputKeyReleased

    private void idInputKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_idInputKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_idInputKeyReleased

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
            java.util.logging.Logger.getLogger(ClientesView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ClientesView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ClientesView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ClientesView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ClientesView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buscarButton;
    private javax.swing.JTextField buscarInput;
    private javax.swing.JButton cancelarButton;
    private javax.swing.JButton editarButton;
    private javax.swing.JTextField emailInput;
    private javax.swing.JButton guardarButton;
    private javax.swing.JTextField idInput;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JTextField nombreInput;
    private javax.swing.JButton nuevoButton;
    private javax.swing.JTextField rfcInput;
    private javax.swing.JTextField telefonoInput;
    // End of variables declaration//GEN-END:variables
}
