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
    PreparedStatement ps = null;

    public DBProducto(Connection conn) {
        this.conn = conn;
    }

    public void guardarProducto(Producto pro) throws SQLException {
        try {
            String sql = "INSERT INTO productos VALUES (?, ?, ?, ?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1, null);
            ps.setString(2, pro.getDescripcion());
            ps.setString(3, pro.getStock());
            ps.setString(4, pro.getPrecio());
            ps.executeUpdate();
        } catch (SQLException exp) {
            exp.printStackTrace();
        } finally {
            try {
                ps.close();
            } catch (SQLException exp) {
                exp.printStackTrace();
            }
        }
    }

    public void editarProducto(Producto pro) throws SQLException {

        try {
            String sql = "UPDATE productos\n"
                    + "SET descripcion = ?,\n"
                    + "stock = ?,\n"
                    + "precio = ?\n"
                    + "WHERE id = ?\n";
            ps = conn.prepareStatement(sql);
            ps.setString(1, pro.getDescripcion());
            ps.setString(2, pro.getStock());
            ps.setString(3, pro.getPrecio());
            ps.setString(4, String.valueOf(pro.getId()));

            ps.executeUpdate();
        } catch (SQLException exp) {
            exp.printStackTrace();
        } finally {
            try {
                ps.close();
            } catch (SQLException exp) {
                exp.printStackTrace();
            }
        }
    }

    public void eliminarProducto(Producto pro) throws SQLException {
        String sql = "DELETE FROM productos\n"
                + " WHERE id = ?\n";

        ps = conn.prepareStatement(sql);
        ps.setString(1, String.valueOf(pro.getId()));
        ps.executeUpdate();
    }

    public String buscarProducto(Producto pro) throws SQLException {
        String result = "";
        String sql = "SELECT * FROM productos WHERE descripcion = ? LIMIT 1";
        ps = conn.prepareStatement(sql);
        ps.setString(1,pro.getDescripcion());
        rs = ps.executeQuery();
        while (rs.next()) {
            result += rs.getString("id") + " " + rs.getString("descripcion") + " " + rs.getString("stock")
                    + " " + rs.getString("precio") + " \n";
        }
        return result;
    }

}
