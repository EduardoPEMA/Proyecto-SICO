/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databases;

import classes.Clientes;
import classes.Producto;
import cliente.servidor.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 */
public class DBProducto {

    Conexion con;
    Connection conn;
    ResultSet rs;

    public DBProducto(Connection conn) {
        this.conn = conn;
    }

    public void guardarProducto(Producto pro) throws SQLException {
        String sql = "INSERT INTO productos VALUES (?, ?, ?)";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, pro.getDescripcion());
        stmt.setString(2, pro.getStock());
        stmt.setString(3, pro.getPrecio());
        stmt.executeUpdate();
    }

    public void editarProducto(Producto pro) throws SQLException {
        String sql = "UPDATE productos\n"
                + "SET descripcion = ?,\n"
                + "stock = ?,\n"
                + "precio = ?,\n"
                + "WHERE id = ?\n";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, pro.getDescripcion());
        stmt.setString(2, pro.getStock());
        stmt.setString(3, pro.getPrecio());
        stmt.setString(4, String.valueOf(pro.getId()));

        stmt.executeUpdate();
    }

    public void eliminarProducto(Producto pro) throws SQLException {
        String sql = "DELETE FROM productos\n"
                + " WHERE id = ?\n";

        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, String.valueOf(pro.getId()));
        stmt.executeUpdate();
    }

    public String buscarProducto(Producto pro) throws SQLException {
        String result = "";
        String sql = "SELECT * FROM productos WHERE id = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, String.valueOf(pro.getId()));
        rs = stmt.executeQuery();
        while (rs.next()) {
            result += rs.getString("id") + " " + rs.getString("descripcion") + " " + rs.getString("stock")
                    + " " + rs.getString("precio") + " \n";
        }
        return result;
    }

}
