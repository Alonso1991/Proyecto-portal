package com.alsea.portal.portalmvc.entity;

import org.springframework.beans.factory.annotation.Qualifier;

import javax.persistence.*;

@Entity(name = "employees")
@Table(name = "employees")
@Qualifier("employees")
public class EmployeesEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

        @Column(name = "nombre")
        private String nombre;

        @Column(name = "apellido")
        private String apellido;

        @Column(name = "email")
        private String email;

        @Column(name = "cargo")
        private String cargo;

        @Column(name = "escalamiento_nvl")
        private int escalamiento;


        @Column(name = "activo")
        private int activo;

        @Column(name = "telefono")
        private String telefono;

        @Column(name = "turno")
        private int turno;

    public int getTurno() {
        return turno;
    }

    public void setTurno(int turno) {
        this.turno = turno;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public int getEscalamiento() {
        return escalamiento;
    }

    public void setEscalamiento(int escalamiento) {
        this.escalamiento = escalamiento;
    }



    public int getActivo() {
        return activo;
    }

    public void setActivo(int activo) {
        this.activo = activo;
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

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public int getescalamiento() {
        return escalamiento;
    }

    public void setescalamiento(int escalamiento) {
        this.escalamiento = escalamiento;
    }



    public EmployeesEntity() {
    }

    public EmployeesEntity(String nombre, String apellido, String email, String cargo, int escalamiento,
                           String telefono, int turno, int activo) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.cargo = cargo;
        this.escalamiento = escalamiento;
        this.telefono=telefono;
        this.turno=turno;
        this.activo=activo;

    }
}
