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
    PreparedStatement ps = null;

    public DBCliente(Connection conn) {
        this.conn = conn;
    }

    public void guardarCliente(Clientes cl) throws SQLException {
        try {
            String sql = "INSERT INTO clientes  (id, nombre, rfc, telefono, email) VALUES (?, ?, ?, ?, ?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1, null);
            ps.setString(2, cl.getNombre());
            ps.setString(3, cl.getRfc());
            ps.setString(4, cl.getTelefono());
            ps.setString(5, cl.getEmail());
            ps.executeUpdate();
        } catch(SQLException exp){
            exp.printStackTrace();
        }
        finally{
            try{
                ps.close();
            }catch(SQLException exp){
                exp.printStackTrace();
            }
        }
        
    }

    public void editarCliente(Clientes cl) throws SQLException {
        try {
            String sql = "UPDATE clientes\n"
                + "SET nombre = ?,\n"
                + "rfc = ?,\n"
                + "telefono = ?,\n"
                + "email = ?\n"
                + "WHERE id = ?\n";
            ps = conn.prepareStatement(sql);
            ps.setString(1, cl.getNombre());
            ps.setString(2, cl.getRfc());
            ps.setString(3, cl.getTelefono());
            ps.setString(4, cl.getEmail());
            ps.setString(5, String.valueOf(cl.getId()));

            ps.executeUpdate();
        }catch(SQLException exp){
            exp.printStackTrace();
        }
        finally{
            try{
                ps.close();
            }catch(SQLException exp){
                exp.printStackTrace();
            }
        }
        
    }

    public String buscarCliente(Clientes cl) throws SQLException {
        String result = "";
        try {
            String sql = "SELECT * FROM clientes WHERE nombre = ? LIMIT 1";
            ps = conn.prepareStatement(sql);
            ps.setString(1, cl.getNombre());
            rs = ps.executeQuery();
            while (rs.next()) {
                result += rs.getString("id") + " " + rs.getString("nombre") + " " + rs.getString("rfc")
                        + " " + rs.getString("telefono") + " " + rs.getString("email") + " \n";
            }
        }catch(SQLException exp){
            exp.printStackTrace();
        }
        finally{
            try{
                ps.close();
            }catch(SQLException exp){
                exp.printStackTrace();
            }
        }
        
        return result;
    }
    
    public String listarClientes() throws SQLException {
        String result = "";
        String sql = "SELECT * FROM clientes";
        PreparedStatement stmt = conn.prepareStatement(sql);
        rs = stmt.executeQuery();
        while (rs.next()) {
            result +=  rs.getString("id") + " " + rs.getString("nombre") + "," + " \n";
        }
        System.out.println(result);
        return result;
    }

}
