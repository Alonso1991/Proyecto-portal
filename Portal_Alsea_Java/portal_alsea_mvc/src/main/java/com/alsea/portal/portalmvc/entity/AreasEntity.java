package com.alsea.portal.portalmvc.entity;

import org.springframework.beans.factory.annotation.Qualifier;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.awt.geom.Area;
import java.util.List;

@Entity(name = "areas")
@Table(name = "areas")
@Qualifier("areas")
public class AreasEntity {

    public static List<Area> getAllAreas;
    @Id
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "activo")
    private int activo;

    public AreasEntity(int id, String nombre, int activo) {
        this.id = id;
        this.nombre = nombre;
        this.activo = activo;
    }

    public AreasEntity() {
    }

    public int getActivo() {
        return activo;
    }

    public void setActivo(int activo) {
        this.activo = activo;
    }




    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
