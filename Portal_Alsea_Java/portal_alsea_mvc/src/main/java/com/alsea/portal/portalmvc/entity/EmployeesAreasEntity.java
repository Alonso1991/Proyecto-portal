package com.alsea.portal.portalmvc.entity;

import org.springframework.beans.factory.annotation.Qualifier;

import javax.persistence.*;

@Entity(name = "employee_area")
@Table(name = "employee_area")
@Qualifier("employee_area")
public class EmployeesAreasEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;


    @Column(name = "id_employee", nullable = false)
    private int idEmployee;

    @Column(name = "id_area", nullable = false)
    private int idArea;

    public EmployeesAreasEntity() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdEmployee() {
        return idEmployee;
    }

    public void setIdEmployee(int idEmployee) {
        this.idEmployee = idEmployee;
    }

    public int getIdArea() {
        return idArea;
    }

    public void setIdArea(int idArea) {
        this.idArea = idArea;
    }

    public EmployeesAreasEntity(int idArea, int idEmployee) {
        this.idEmployee = idEmployee;
        this.idArea = idArea;
    }
}
