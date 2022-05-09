/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databases;

import classes.Clientes;
import cliente.servidor.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 */
public class DBCliente {

    Conexion con;
    Connection conn;
    ResultSet rs;

    public DBCliente(Connection conn) {
        this.conn = conn;
    }

    public void guardarCliente(Clientes cl) throws SQLException {
        String sql = "INSERT INTO clientes VALUES (?, ?, ?, ?, ?)";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, cl.getNombre());
        stmt.setString(2, cl.getRfc());
        stmt.setString(3, cl.getTelefono());
        stmt.setString(4, cl.getEmail());
        stmt.setString(5, null);
        stmt.executeUpdate();
    }

    public void editarCliente(Clientes cl) throws SQLException {
        String sql = "UPDATE clientes\n"
                + "SET nombre = ?,\n"
                + "RFC = ?,\n"
                + "telefono = ?,\n"
                + "email = ?\n"
                + "WHERE id = ?\n";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, cl.getNombre());
        stmt.setString(2, cl.getRfc());
        stmt.setString(3, cl.getTelefono());
        stmt.setString(4, cl.getEmail());
        stmt.setString(5, String.valueOf(cl.getId()));

        stmt.executeUpdate();
    }

    public void eliminarCliente(Clientes cl) throws SQLException {
        String sql = "DELETE FROM clientes\n"
                + " WHERE id = ?\n";

        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, String.valueOf(cl.getId()));
        stmt.executeUpdate();
    }

    public String buscarCliente(Clientes cl) throws SQLException {
        String result = "";
        String sql = "SELECT * FROM clientes WHERE id = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, String.valueOf(cl.getId()));
        rs = stmt.executeQuery();
        while (rs.next()) {
            result += rs.getString("id") + " " + rs.getString("nombre") + " " + rs.getString("rfc")
                    + " " + rs.getString("telefono") + " " + rs.getString("email") + " \n";
        }
        return result;
    }

}
