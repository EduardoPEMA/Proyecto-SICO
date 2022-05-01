/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente.servidor;

import java.sql.DriverManager;
import java.sql.Connection;
import javax.swing.JOptionPane;


public class Conexion {

    Connection conn;

    public Connection Conexion() {
        try {
            DriverManager.registerDriver(new org.gjt.mm.mysql.Driver());
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost/storage", "root", "");
            this.conn = con;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error en la Conexion");
        }
        return this.conn;
    }
}
