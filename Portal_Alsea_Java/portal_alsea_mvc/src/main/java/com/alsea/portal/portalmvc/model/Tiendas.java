package com.alsea.portal.portalmvc.model;

import lombok.Data;

@Data
public class Tiendas {


    private int id;
    private String Tiendas;
    private String ip;

    public Tiendas(int id, String tiendas, String ip) {
        this.id = id;
        Tiendas = tiendas;
        this.ip = ip;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTiendas() {
        return Tiendas;
    }

    public void setTiendas(String tiendas) {
        Tiendas = tiendas;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
}
