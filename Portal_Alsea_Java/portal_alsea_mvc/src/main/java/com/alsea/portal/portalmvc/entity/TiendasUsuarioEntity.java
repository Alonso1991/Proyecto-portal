package com.alsea.portal.portalmvc.entity;

import org.springframework.beans.factory.annotation.Qualifier;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity(name = "tiendas_usuario")
@Table(name = "tiendas_usuario")
@Qualifier("tiendas_usuario")
public class TiendasUsuarioEntity  implements Serializable {
    private static final long serialVersionUID = -4061666574682626317L;

    @Id
    @Column(name="id")
    private int id;
    @Column(name="id_tienda")
    private int idTienda;
    @Column(name="id_usuario")
    private int idUsuario;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdTienda() {
        return idTienda;
    }

    public void setIdTienda(int idTienda) {
        this.idTienda = idTienda;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
}
