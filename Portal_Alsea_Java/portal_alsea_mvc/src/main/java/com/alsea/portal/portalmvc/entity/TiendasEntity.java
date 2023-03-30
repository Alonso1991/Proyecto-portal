package com.alsea.portal.portalmvc.entity;

import lombok.Data;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;


@Entity(name = "tiendas")
@Table(name = "tiendas")
@Qualifier("tiendas")
public class TiendasEntity implements Serializable {
    private static final long serialVersionUID = -5826565839103687170L;

    @Id
    @Column(name = "id")
    private int id;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "ip")
    private String ip;



    public String getTiendas() {
        return nombre;
    }

    public String getIp() {
        return ip;
    }

    public int getId() {
        return id;
    }
}
