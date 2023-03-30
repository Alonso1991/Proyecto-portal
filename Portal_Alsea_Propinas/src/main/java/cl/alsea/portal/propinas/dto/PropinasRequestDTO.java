package cl.alsea.portal.propinas.dto;

import java.util.Date;

public class PropinasRequestDTO {

    private Date initDate;
    private Date endDate;
    private String ip;

    public String getIp() {
        return ip;
    }

    public PropinasRequestDTO() {
    }

    public Date getInitDate() {
        return initDate;
    }

    public Date getEndDate() {
        return endDate;
    }


}
