/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

/**
 *
 * @author Eduardo
 */
public class Clientes {
    private int id;
    private String nombre;
    private String rfc;
    private String telefono;
    private String email;
    
     public Clientes() {
        
    }
    
    public Clientes(String p) {
        String[] arrayString = p.split(" ");
        this.id = Integer.parseInt(arrayString[0]);
        this.nombre = arrayString[1];
        this.rfc = arrayString[2];
        this.telefono = arrayString[3];
        this.email = arrayString[4];
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    } 

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    
}
