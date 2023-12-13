package cl.alsea.portal.propinas.service;

import cl.alsea.portal.propinas.dao.PropinasDAO;
import cl.alsea.portal.propinas.dto.PropinasResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;


@Service("ticketsService")
public class PropinasService implements Propinas{


    PropinasDAO propinasDao;

    @Override
    public List<PropinasResponseDTO> obtienePropinas(Date initDate, Date endDate) throws SQLException {
        propinasDao=new PropinasDAO();
        List<PropinasResponseDTO> response = propinasDao.getPropinas(initDate,endDate);


        return response;
    }
}
