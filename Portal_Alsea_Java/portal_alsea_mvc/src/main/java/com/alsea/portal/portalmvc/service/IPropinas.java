package com.alsea.portal.portalmvc.service;

import com.alsea.portal.portalmvc.dto.PropinasResponseDTO;

import java.util.Date;
import java.util.List;

public interface IPropinas {

    List<PropinasResponseDTO> getPropinas(String fecIni, String fecFin, String tienda);
    String getPropinasRest(String fecIni, String fecFin, String ip);
}