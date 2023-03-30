package arg.mercadopago.mercadofood.controller;


import arg.mercadopago.mercadofood.dto.ResponseDTO;
import arg.mercadopago.mercadofood.dto.TokenMP;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api("TOKEN")
@RestController
@RequestMapping("oauth")
public class OAuthController {

    HttpHeaders httpHeaders;

    @ApiOperation("Api de obtencion de token")
    @PostMapping(value = "/token", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO> recibeToken(
            @RequestBody TokenMP requestDTO) {

        ResponseDTO response = new ResponseDTO();
        System.out.println("Token ingresado: "+requestDTO);
        response.setStatus("Exito");

        return new ResponseEntity<>(response, httpHeaders, HttpStatus.OK);
    }


    @ApiOperation("Api de obtencion de code y random id")
    @PostMapping(value = "/code", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO> recibeCode(
            @RequestParam String code,
            @RequestParam String state) {

        ResponseDTO response = new ResponseDTO();

        System.out.println("Code ingresado: "+code);
        System.out.println("RandomID ingresado: "+state);
        response.setStatus("Exito");

        return new ResponseEntity<>(response, httpHeaders, HttpStatus.OK);
    }


}
