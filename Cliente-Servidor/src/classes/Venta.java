/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * @author Cesar
 */
public class Venta {

    private int id;
    private String folio;
    private String fecha;
    private String total;
    private String cliente_id;

    private static AtomicInteger nextFolio = new AtomicInteger(0);
    private static ArrayList<Integer> ID_VALIDOS = new ArrayList<>();

    public Venta() {
        do {
            folio = "CE-00" + nextFolio.incrementAndGet();
        } while (ID_VALIDOS.contains(id));
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFolio() {
        return folio;
    }

    public void setFolio(String folio) {
        this.folio = folio;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getCliente() {
        return cliente_id;
    }

    public void setCliente(String cliente_id) {
        this.cliente_id = cliente_id;
    }

}
