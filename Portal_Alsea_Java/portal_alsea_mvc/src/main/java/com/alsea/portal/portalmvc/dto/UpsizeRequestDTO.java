package com.alsea.portal.portalmvc.dto;

import java.util.Date;

public class UpsizeRequestDTO {


    private Date initDate;
    private Date endDate;
    private String ip;

    public UpsizeRequestDTO(Date initDate, Date endDate, String ip) {
        this.initDate = initDate;
        this.endDate = endDate;
        this.ip = ip;
    }

}
