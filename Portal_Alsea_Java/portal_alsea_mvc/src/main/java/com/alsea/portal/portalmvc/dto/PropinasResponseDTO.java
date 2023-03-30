package com.alsea.portal.portalmvc.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.Date;


@JsonPropertyOrder({"sumaPropinas","datePropinas", "cantPropinas"})
public class PropinasResponseDTO {

    @JsonProperty("datePropinas")
    private String datePropinas;
    @JsonProperty("sumaPropinas")
    private int sumaPropinas;
    @JsonProperty("cantPropinas")
    private int cantPropinas;

    public PropinasResponseDTO() {
    }

    public PropinasResponseDTO(String datePropinas, int sumaPropinas, int cantPropinas) {
        this.datePropinas = datePropinas;
        this.sumaPropinas = sumaPropinas;
        this.cantPropinas = cantPropinas;
    }

    public String getFechaPropina() {
        return datePropinas;
    }

    public void setFechaPropina(String datePropinas) {
        this.datePropinas = datePropinas;
    }

    public int getSumaPropinas() {
        return sumaPropinas;
    }

    public void setSumaPropinas(int sumaPropinas) {
        this.sumaPropinas = sumaPropinas;
    }

    public int getCantPropinas() {
        return cantPropinas;
    }

    public void setCantPropinas(int cantPropinas) {
        this.cantPropinas = cantPropinas;
    }
}
