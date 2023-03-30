package com.alsea.portal.portalmvc.entity;


import org.springframework.beans.factory.annotation.Qualifier;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "turnos")
@Table(name = "turnos")
@Qualifier("turnos")
public class TurnosEntity {
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Id
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "detalle_turno")
    private String detalle;

    @Column(name = "id_trabajador")
    private int trabajador;

    @Column(name = "turno_inicio")
    private Date turnoInicio;

    @Column(name = "turno_fin")
    private Date turnoFin;


    public TurnosEntity() {
    }

    public TurnosEntity(String detalle, int trabajador, Date turnoInicio, Date turnoFin) {
        this.detalle = detalle;
        this.trabajador = trabajador;
        this.turnoInicio = turnoInicio;
        this.turnoFin = turnoFin;
    }


}
