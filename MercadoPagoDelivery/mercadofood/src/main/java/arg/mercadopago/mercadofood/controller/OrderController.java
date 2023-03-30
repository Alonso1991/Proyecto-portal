package arg.mercadopago.mercadofood.controller;

import arg.mercadopago.mercadofood.AsyncTask;
import arg.mercadopago.mercadofood.dao.OrdenDao;
import arg.mercadopago.mercadofood.dto.ResponseDTO;
import arg.mercadopago.mercadofood.model.Notification;
import arg.mercadopago.mercadofood.service.OrderService;
import arg.mercadopago.mercadofood.service.OrderStatusService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@Api
@RestController
@RequestMapping("/order")
@Qualifier("orderController")
public class OrderController {

    HttpHeaders httpHeaders;
    ResponseDTO response;

    private static final Logger log = LoggerFactory.getLogger(OrderController.class);

    //Beans
    private final OrderService orderService;
    private final OrderStatusService orderStatusService;

    final
    AsyncTask asyncTask;

    public OrderController(@Qualifier("orderService") OrderService orderService, @Qualifier("orderStatus") OrderStatusService orderStatusService,
                           @Qualifier("asyncTask") AsyncTask asyncTask) {
        this.orderService = orderService;
        this.orderStatusService = orderStatusService;
        this.asyncTask = asyncTask;
    }

    @ApiOperation("Api de obtencion de orden")
    @PostMapping(value = "/notification", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO> recibeOrden(@RequestBody Notification notify) throws Exception {

        response = new ResponseDTO("exito","notificación recibida");
        log.info("Notificacion ingresada: " + notify);
        //Obtiene orden desde MP de forma asyncrona
        asyncTask.obtieneOrden(notify);

        return new ResponseEntity<>(response, httpHeaders, HttpStatus.OK);
    }

    @ApiOperation("Api de obtencion de orden")
    @PostMapping(value = "/test", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO> recibeTest(@RequestBody String notify) throws Exception {

        Notification notification = new Notification();
        notification.setResource("/shipments/41973808305");
        asyncTask.obtieneOrden(notification);
        //Ingresar aqui metodos a probar mientras no implementemos JUNIT

        return new ResponseEntity<>(response, httpHeaders, HttpStatus.OK);
    }



    @ApiOperation("Api de obtencion de orden")
    @PostMapping(value = "/cancel/{idOrder}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO> cancelOrderMP(
            @PathVariable("idOrder") String idOrder,
            @PathVariable("idStatus") String idStatus) throws Exception {

        log.info("Cambiando estado a cancelada desde JDelivery IdOrder: " + idOrder);
        response = new ResponseDTO();
        orderStatusService.statusOrderCancelMP(idOrder);
        response.setStatus("OK");

        return new ResponseEntity<>(response, httpHeaders, HttpStatus.OK);
    }
}
