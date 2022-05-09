/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databases;

import cliente.servidor.Conexion;
import classes.Usuarios;
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
    PreparedStatement ps = null;

    public DBUsuarios(Connection conn) {
        this.conn = conn;
    }

    public void guardarUsuario(Usuarios us) throws SQLException {
        try {
            String sql = "INSERT INTO usuarios  (id, nombre, username, rol, password) VALUES (?, ?, ?, ?, ?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1, null);
            ps.setString(2, us.getNombre());
            ps.setString(3, us.getUsername());
            ps.setString(4, us.getRol());
            ps.setString(5, us.getPassword());
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

    public void editarUsuario(Usuarios us) throws SQLException {
        try {
            String sql = "UPDATE usuarios\n"
                + "SET nombre = ?,\n"
                + "username = ?,\n"
                + "rol = ?,\n"
                + "password = ?\n"
                + "WHERE id = ?\n";
            System.out.println(us.getRol());
            ps = conn.prepareStatement(sql);
            ps.setString(1, us.getNombre());
            ps.setString(2, us.getUsername());
            ps.setString(3, us.getRol());
            ps.setString(4, us.getPassword());
            ps.setString(5, String.valueOf(us.getId()));

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

    public void eliminarUsuario(Usuarios us) throws SQLException {
        String sql = "DELETE FROM usuarios\n"
                + " WHERE id = ?\n";

        ps = conn.prepareStatement(sql);
        ps.setString(1, String.valueOf(us.getId()));
        ps.executeUpdate();
    }

    public String buscarUsuario(Usuarios us) throws SQLException {
        String result = "";
        try {
            String sql = "SELECT * FROM usuarios WHERE nombre = ? LIMIT 1";
            ps = conn.prepareStatement(sql);
            ps.setString(1, us.getNombre());
            rs = ps.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getString("rol"));
                result += rs.getString("id") + " " + rs.getString("nombre") + " " + rs.getString("username")
                        + " " + rs.getString("rol") + " " + rs.getString("password") + " \n";
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
    
    public String login(Usuarios us) throws SQLException {
        String res = "";
        try {
        
            String sql = "select * from usuarios where username=? and password=?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, us.getUsername());
            ps.setString(2, us.getPassword());
            rs = ps.executeQuery();
            while (rs.next()) {
                res += rs.getString("id") + " " + rs.getString("nombre") + " " + rs.getString("username")
                        + " " + rs.getString("rol") + " " + rs.getString("password") + " \n";
            }
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
        return res;
    }

}
