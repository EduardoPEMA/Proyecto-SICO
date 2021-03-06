/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente.servidor;

import classes.Usuarios;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import utils.Utils;

/**
 *
 * @author isaac
 */
public class LoginView extends javax.swing.JFrame {

    private boolean usernameIsValid = false;
    private boolean passwordIsValid = false;
    Conexion con;
    public Connection conn;
    private DatagramSocket socket;

    /**
     * Creates new form Login
     */
    public LoginView() {
        try {
            initComponents();
            permitirAccion();
            con = new Conexion();
            socket = new DatagramSocket();
            this.setLocationRelativeTo(null);

        } catch (SocketException ex) {
            Logger.getLogger(LoginView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private boolean getEstados() {
        return usernameIsValid && passwordIsValid;
    }

    private void permitirAccion() {
        boolean permitir = getEstados();
        iniciarButton.setEnabled(permitir);
    }

    private void esperarPaquetes() {
        try {
            //establecer el paquete
            byte datos[] = new byte[100];
            DatagramPacket recibirPaquete = new DatagramPacket(
                    datos, datos.length);
            socket.receive(recibirPaquete); //esperar un paquete
            
            if (recibirPaquete.getLength() == 0) {
                return;
            }
            
            String cad = (new String(recibirPaquete.getData(),
                    0, recibirPaquete.getLength()));
            
            if(cad.equals("No hay resultados")) {
                JOptionPane.showMessageDialog(null, "Verifica las credenciales. "
                        + "Los datos ingresados no son correctos");
                return;
            }
            String[] variables;
            variables = cad.split(" ");
            
            Navigation nav = new Navigation();
            nav.setNavigation(variables[3]);
            nav.show();
            this.dispose();

        } catch (IOException excepcion) {
            excepcion.printStackTrace();
        }

    }//fin

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        iniciarButton = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        userInput = new javax.swing.JTextField();
        passwordInput = new javax.swing.JPasswordField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        iniciarButton.setFont(new java.awt.Font("Bahnschrift", 1, 11)); // NOI18N
        iniciarButton.setText("Iniciar Sesi??n");
        iniciarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                iniciarButtonActionPerformed(evt);
            }
        });
        getContentPane().add(iniciarButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 360, 160, 30));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/whiteLogo.png"))); // NOI18N
        jLabel2.setText("jLabel2");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 30, 160, 130));

        userInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                userInputActionPerformed(evt);
            }
        });
        userInput.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                userInputKeyReleased(evt);
            }
        });
        getContentPane().add(userInput, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 200, 270, 40));

        passwordInput.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                passwordInputKeyReleased(evt);
            }
        });
        getContentPane().add(passwordInput, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 270, 270, 40));

        jLabel3.setFont(new java.awt.Font("Bahnschrift", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Contrase??a");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 250, -1, -1));

        jLabel4.setFont(new java.awt.Font("Bahnschrift", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Usuario");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 180, -1, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/bg-dblue.jpg"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 820, 480));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void iniciarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_iniciarButtonActionPerformed
        // TODO add your handling code here:
        try {
            //obtener mensaje del campo de texto y convertirlo en arreglo byte
            String user = userInput.getText();
            String psw = passwordInput.getText();
            String mensaje = "usuario" + " " + "login" + " " + user + " " + psw;
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
    }//GEN-LAST:event_iniciarButtonActionPerformed

    private void userInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_userInputActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_userInputActionPerformed

    private void userInputKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_userInputKeyReleased
        // TODO add your handling code here:
        String aux = userInput.getText();
        usernameIsValid = !aux.equals("");
        permitirAccion();
    }//GEN-LAST:event_userInputKeyReleased

    private void passwordInputKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_passwordInputKeyReleased
        // TODO add your handling code here:
        String aux = passwordInput.getText();
        passwordIsValid = !aux.equals("");
        permitirAccion();
    }//GEN-LAST:event_passwordInputKeyReleased

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
            java.util.logging.Logger.getLogger(LoginView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LoginView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LoginView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LoginView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LoginView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton iniciarButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPasswordField passwordInput;
    private javax.swing.JTextField userInput;
    // End of variables declaration//GEN-END:variables
}
