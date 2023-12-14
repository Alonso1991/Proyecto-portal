package cl.alsea.portal.propinas.controller;


import cl.alsea.portal.propinas.dto.PropinasResponseDTO;
import cl.alsea.portal.propinas.service.Propinas;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@Api(tags = "propinas")
public class PropinasController {


    @Autowired
    Propinas obtienePropinas;

    HttpHeaders httpHeaders;


    @ApiOperation("Metodo recupera suma de propinas por fecha")
    @GetMapping(value = "/propinas/obtener", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JSONObject> obtienePropinas(               @RequestParam("initDate") Date dateInit,
                                                                     @RequestParam("endDate") Date dateFin) {                                                                     {

        JSONObject objPropinas=new JSONObject();
        List<PropinasResponseDTO> propinasResponseDTO = new ArrayList<>();


        try {
            propinasResponseDTO = obtienePropinas.obtienePropinas(dateInit,dateFin);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        objPropinas.put("propinas", propinasResponseDTO);
        return new ResponseEntity<>(objPropinas, httpHeaders, HttpStatus.ACCEPTED);
    }
    }
}
