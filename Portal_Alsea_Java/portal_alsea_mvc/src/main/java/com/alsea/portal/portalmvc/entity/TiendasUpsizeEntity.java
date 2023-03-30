package com.alsea.portal.portalmvc.entity;

import org.springframework.beans.factory.annotation.Qualifier;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "tiendas")
@Table(name = "tiendas")
@Qualifier("tiendas")
public class TiendasUpsizeEntity {


    @Id
    @Column(name = "id")
    private int id;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "ip")
    private static String ip;

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

    public static String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
}
