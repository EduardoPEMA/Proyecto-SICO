/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

/**
 *
 * @author Cesar
 */
public class Producto {
    private int id;
    private String descripcion;
    private String stock;
    private String precio;
    
    public Producto() {
        
    }
    
    public Producto(String p) {
        String[] arrayString = p.split(" ");
        this.id = Integer.parseInt(arrayString[0]);
        this.descripcion = arrayString[1];
        this.stock = arrayString[2];
        this.precio = arrayString[3];
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    } 

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }
    
}
