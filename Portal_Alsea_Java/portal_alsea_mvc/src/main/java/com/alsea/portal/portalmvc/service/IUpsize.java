package com.alsea.portal.portalmvc.service;


import com.alsea.portal.portalmvc.dto.UpsizeResponseDTO;

import java.util.List;

public interface IUpsize {


    List<UpsizeResponseDTO> getUpsize(String fecIni, String fecFin, String tienda);

    String getUpsizeRest(String fecIni, String fecFin, String ip);
}
