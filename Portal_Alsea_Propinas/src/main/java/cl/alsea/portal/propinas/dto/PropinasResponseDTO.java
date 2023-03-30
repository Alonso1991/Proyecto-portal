package cl.alsea.portal.propinas.dto;

import java.util.Date;


public class PropinasResponseDTO {

    private int sumaPropinas;
    private Date datePropinas;
    private int cantPropinas;

    public int getCantPropinas() {
        return cantPropinas;
    }


    public PropinasResponseDTO(int sumaPropinas, Date datePropinas, int cantPropinas) {
        this.sumaPropinas = sumaPropinas;
        this.datePropinas = datePropinas;
        this.cantPropinas = cantPropinas;
    }

    public void setCantPropinas(int cantPropinas) {
        this.cantPropinas = cantPropinas;
    }

    public int getSumaPropinas() {
        return sumaPropinas;
    }

    public void setSumaPropinas(int sumaPropinas) {
        this.sumaPropinas = sumaPropinas;
    }

    public Date getDatePropinas() {
        return datePropinas;
    }

    public void setDatePropinas(Date datePropinas) {
        this.datePropinas = datePropinas;
    }
}
