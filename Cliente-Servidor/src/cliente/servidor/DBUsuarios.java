/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente.servidor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 */
public class DBUsuarios {

    Conexion con;
    Connection conn;
    ResultSet rs;

    public DBUsuarios(Connection conn) {
        this.conn = conn;
    }

    public void guardarUsuario(Usuarios us) throws SQLException {
        String sql = "INSERT INTO usuarios VALUES (?, ?, ?, ?)";
        PreparedStatement stmt = conn.prepareStatement(sql);
        System.out.println(stmt);
        System.out.println(us.getNombre_usuario());
        System.out.println(us.getPassword());
        System.out.println(us.getNombre_perfil());
        stmt.setString(1, us.getNombre_usuario());
        stmt.setString(2, us.getPassword());
        stmt.setString(3, us.getNombre_perfil());
        stmt.setString(4, null);
        stmt.executeUpdate();
    }

    public void editarUsuario(Usuarios us) throws SQLException {
        String sql = "UPDATE usuarios\n"
                + "SET perfil = ?,\n"
                + "password = ?\n"
                + "WHERE nombre_usuario = ?\n";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, us.getNombre_perfil());
        stmt.setString(2, us.getPassword());
        stmt.setString(3, us.getNombre_usuario());

        stmt.executeUpdate();
    }

    public void eliminarUsuario(Usuarios us) throws SQLException {
        String sql = "DELETE FROM usuarios\n"
                + " WHERE nombre_usuario = ?\n";

        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, us.getNombre_usuario());
        stmt.executeUpdate();
    }

    public String buscarUsuario(Usuarios us) throws SQLException {
        String result = "";
        String sql = "SELECT * FROM usuarios WHERE nombre_usuario = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, us.getNombre());
        rs = stmt.executeQuery();
        while (rs.next()) {
            result += rs.getString("nombre_usuario") + " " + rs.getString("perfil")
                    + " " + rs.getString("password") + " \n";
        }
        return result;
    }

}
