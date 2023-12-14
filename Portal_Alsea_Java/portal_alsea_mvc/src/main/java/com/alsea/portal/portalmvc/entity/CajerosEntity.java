package com.alsea.portal.portalmvc.entity;

import org.springframework.beans.factory.annotation.Qualifier;

import javax.persistence.*;


@Entity(name = "cajeros")
@Table(name = "cajeros")
@Qualifier("cajeros")
public class CajerosEntity {


    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "cajero")
    private String nombre;

    @Column(name = "combos")
    private String combos;

    @Column(name = "upsizes")
    private int upsizes;

    @Column(name = "nuggets")
    private int nuggets;

    @Column(name = "cafe")
    private int cafe;

    @Column(name = "aros")
    private int aros;

    @Column(name = "adicional_postres")
    private int postres;

    @Column(name = "xl")
    private int xl;

    @Column(name = "adicional_tropicana")
    private int tropicana;

    @Column(name = "power")
    private int power;

    @Column(name = "papas_cheddar")
    private int cheddar;


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

    public String getCombos() {
        return combos;
    }

    public void setCombos(String combos) {
        this.combos = combos;
    }

    public int getUpsizes() {
        return upsizes;
    }

    public void setUpsizes(int upsizes) {
        this.upsizes = upsizes;
    }

    public int getNuggets() {
        return nuggets;
    }

    public void setNuggets(int nuggets) {
        this.nuggets = nuggets;
    }

    public int getCafe() {
        return cafe;
    }

    public void setCafe(int cafe) {
        this.cafe = cafe;
    }

    public int getAros() {
        return aros;
    }

    public void setAros(int aros) {
        this.aros = aros;
    }

    public int getPostres() {
        return postres;
    }

    public void setPostres(int postres) {
        this.postres = postres;
    }

    public int getXl() {
        return xl;
    }

    public void setXl(int xl) {
        this.xl = xl;
    }

    public int getTropicana() {
        return tropicana;
    }

    public void setTropicana(int tropicana) {
        this.tropicana = tropicana;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public int getCheddar() {
        return cheddar;
    }

    public void setCheddar(int cheddar) {
        this.cheddar = cheddar;
    }

    public CajerosEntity(){

    }
    public CajerosEntity(String nombre, String combos, int upsizes, int nuggets, int cafe,
                           int aros, int postres, int xl, int tropicana, int power, int cheddar) {

        this.nombre = nombre;
        this.combos = combos;
        this.upsizes = upsizes;
        this.nuggets = nuggets;
        this.cafe = cafe;
        this.aros=aros;
        this.postres=postres;
        this.xl=xl;
        this.tropicana = tropicana;
        this.power = power;
        this.cheddar= cheddar;

    }
}
