package cl.alsea.portal.propinas.controller;

import cl.alsea.portal.propinas.dto.PropinasRequestDTO;
import cl.alsea.portal.propinas.dto.PropinasResponseDTO;
import cl.alsea.portal.propinas.service.Propinas;
import com.fasterxml.jackson.databind.util.JSONPObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@Api(tags = "propinas")
public class PropinasController {


    @Autowired
    Propinas obtienePropinas;

    HttpHeaders httpHeaders;


    @ApiOperation("Metodo recupera suma de propinas por fecha")
    @GetMapping(value = "/propinas/obtener", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JSONObject> obtienePropinas(@RequestHeader("X-User-Agent") String dispositivo,
                                                                     @RequestParam("initDate") Date dateInit,
                                                                     @RequestParam("endDate") Date dateFin,
                                                                     @RequestParam("ip") String ip) throws SQLException {

        JSONObject objPropinas=new JSONObject();
        List<PropinasResponseDTO> propinasResponseDTO = new ArrayList<>();


        propinasResponseDTO = obtienePropinas.obtienePropinas(dateInit,dateFin,ip);
        objPropinas.put("propinas", propinasResponseDTO);
        return new ResponseEntity<>(objPropinas, httpHeaders, HttpStatus.ACCEPTED);
    }

}
