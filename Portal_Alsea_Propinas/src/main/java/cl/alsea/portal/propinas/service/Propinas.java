package cl.alsea.portal.propinas.service;

import cl.alsea.portal.propinas.dto.PropinasResponseDTO;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public interface Propinas {

   List<PropinasResponseDTO> obtienePropinas(Date initDate, Date endDate) throws SQLException;
}
