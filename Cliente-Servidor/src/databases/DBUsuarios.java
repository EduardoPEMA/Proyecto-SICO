/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databases;

import cliente.servidor.Conexion;
import cliente.servidor.Usuarios;
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

        stmt.setString(1, null);
        stmt.setString(2, us.getNombre());
        stmt.setString(3, us.getUsername());
        stmt.setString(4, us.getPassword());
        stmt.setString(5, us.getRol());
        stmt.executeUpdate();
    }

    public void editarUsuario(Usuarios us) throws SQLException {
        String sql = "UPDATE usuarios\n"
                + "SET nombre = ?,\n"
                + "username =    ?,\n"
                + "rol =    ?,\n"
                + "password = ?\n"
                + "WHERE nombre = ?\n";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, us.getNombre());
        stmt.setString(2, us.getUsername());
        stmt.setString(3, us.getRol());
        stmt.setString(4, us.getPassword());

        stmt.executeUpdate();
    }

    public void eliminarUsuario(Usuarios us) throws SQLException {
        String sql = "DELETE FROM usuarios\n"
                + " WHERE id = ?\n";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, String.valueOf(us.getId()));
        stmt.executeUpdate();
    }

    public String buscarUsuario(Usuarios us) throws SQLException {
        String result = "";
        String sql = "SELECT * FROM usuarios WHERE nombre = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, us.getNombre());
        rs = stmt.executeQuery();
        while (rs.next()) {
            result += rs.getString("nombre") + " " + rs.getString("username")
                    + " " + rs.getString("rol") + " " + rs.getString("password") + " \n";
        }
        return result;
    }

}
