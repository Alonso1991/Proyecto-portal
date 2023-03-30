package com.alsea.portal.portalmvc.entity;

import org.springframework.beans.factory.annotation.Qualifier;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;


@Entity(name = "usuarios")
@Table(name = "usuarios")
@Qualifier("usuarios")
public class UsuarioEntity implements Serializable {
    private static final long serialVersionUID = -9080683619845628961L;

    @Id
    @Column(name="id")
    private int id;
    @Column(name="nombre")
    private String nombre;
    @Column(name="email")
    private String email;
    @Column(name="tipo")
    private String tipo;


    public String getNombre() {
        return nombre;
    }

    public String getEmail() {
        return email;
    }

    public String getTipo() {
        return tipo;
    }

    public int getId() {
        return id;
    }
}
