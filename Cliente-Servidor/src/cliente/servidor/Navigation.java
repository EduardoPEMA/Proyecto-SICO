/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente.servidor;

/**
 *
 * @author isaac
 */
public class Navigation extends javax.swing.JFrame {

    /**
     * Creates new form UsuariosView
     */
    public Navigation() {
        initComponents();
        this.setLocationRelativeTo(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        nuevoButton = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        nuevoButton1 = new javax.swing.JButton();
        nuevoButton2 = new javax.swing.JButton();
        nuevoButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        nuevoButton.setFont(new java.awt.Font("Bahnschrift", 1, 11)); // NOI18N
        nuevoButton.setText("Nuevo");
        nuevoButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nuevoButtonActionPerformed(evt);
            }
        });
        getContentPane().add(nuevoButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 390, 210, 50));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/whiteLogo.png"))); // NOI18N
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 20, 170, 160));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/bg-dblue.jpg"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(-720, 360, 830, 480));

        nuevoButton1.setFont(new java.awt.Font("Bahnschrift", 1, 11)); // NOI18N
        nuevoButton1.setText("Clientes");
        nuevoButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nuevoButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(nuevoButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 310, 210, 50));

        nuevoButton2.setFont(new java.awt.Font("Bahnschrift", 1, 11)); // NOI18N
        nuevoButton2.setText("Clientes");
        nuevoButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nuevoButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(nuevoButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 310, 210, 50));

        nuevoButton3.setFont(new java.awt.Font("Bahnschrift", 1, 11)); // NOI18N
        nuevoButton3.setText("Nuevo");
        nuevoButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nuevoButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(nuevoButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 390, 210, 50));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void nuevoButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nuevoButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nuevoButtonActionPerformed

    private void nuevoButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nuevoButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nuevoButton1ActionPerformed

    private void nuevoButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nuevoButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nuevoButton2ActionPerformed

    private void nuevoButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nuevoButton3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nuevoButton3ActionPerformed

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
            java.util.logging.Logger.getLogger(Navigation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Navigation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Navigation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Navigation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Navigation().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JButton nuevoButton;
    private javax.swing.JButton nuevoButton1;
    private javax.swing.JButton nuevoButton2;
    private javax.swing.JButton nuevoButton3;
    // End of variables declaration//GEN-END:variables
}
