package com.alsea.portal.portalmvc.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"dateUpsize"})
public class UpsizeResponseDTO {

    private String getDateUpsize;
    @JsonProperty("dateUpsize")
    private String dateUpsize;

    public UpsizeResponseDTO() {
    }

    public UpsizeResponseDTO(String dateUpsize) {
        this.dateUpsize = dateUpsize;

    }

    public String getFechaUpsize() {
        return dateUpsize;
    }

    public void setFechaUpsize(String dateUpsize) {
        this.dateUpsize = dateUpsize;
    }
}


