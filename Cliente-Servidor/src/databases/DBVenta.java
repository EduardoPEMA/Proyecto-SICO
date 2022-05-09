/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databases;

import classes.Clientes;
import classes.Producto;
import classes.Venta;
import cliente.servidor.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 */
public class DBVenta {

    Conexion con;
    Connection conn;
    ResultSet rs;

    public DBVenta(Connection conn) {
        this.conn = conn;
    }

    public void guardarVenta(Venta venta) throws SQLException {
        String sql = "INSERT INTO ventas VALUES (?, ?, ?, ?)";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, venta.getFolio());
        stmt.setString(2, venta.getFecha());
        stmt.setString(3, venta.getTotal());
        stmt.setString(4, venta.getCliente());
        stmt.executeUpdate();
    }

    public void editarProducto(Venta venta) throws SQLException {
        String sql = "UPDATE ventas\n"
                + "SET folio = ?,\n"
                + "fecha = ?,\n"
                + "total = ?,\n"
                + "cliente_id = ?,\n"
                + "WHERE id = ?\n";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, venta.getFolio());
        stmt.setString(2, venta.getFecha());
        stmt.setString(3, venta.getTotal());
        stmt.setString(4, venta.getCliente());
        stmt.setString(5, String.valueOf(venta.getId()));

        stmt.executeUpdate();
    }

    public void eliminarCliente(Venta venta) throws SQLException {
        String sql = "DELETE FROM ventas\n"
                + " WHERE id = ?\n";

        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, String.valueOf(venta.getId()));
        stmt.executeUpdate();
    }

    public String buscarCliente(Venta venta) throws SQLException {
        String result = "";
        String sql = "SELECT * FROM ventas WHERE id = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, String.valueOf(venta.getId()));
        rs = stmt.executeQuery();
        while (rs.next()) {
            result += rs.getString("id") + " " + rs.getString("folio") + " " + rs.getString("fecha")
                    + " " + rs.getString("total") + " " + rs.getString("cliente_id") + " \n";
        }
        return result;
    }
    
    
    public String vender(Venta venta, Producto pro) throws SQLException {
        String res = "";
        String sql = "";
        return "";
    }

}
