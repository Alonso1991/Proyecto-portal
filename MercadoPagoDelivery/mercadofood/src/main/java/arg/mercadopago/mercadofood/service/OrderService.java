package arg.mercadopago.mercadofood.service;

import arg.mercadopago.mercadofood.constants.Constants;
import arg.mercadopago.mercadofood.dao.OrdenDao;
import arg.mercadopago.mercadofood.dto.ResponseDTO;
import arg.mercadopago.mercadofood.entity.OrderValidateEntity;
import arg.mercadopago.mercadofood.model.Notification;
import com.google.common.base.Throwables;
import org.apache.http.HttpHeaders;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.concurrent.CompletableFuture;

@EnableAsync
@Service
@Qualifier("orderService")
public class OrderService implements IOrder {

    @Value("${mp.url}")
    public String url;
    @Value("${mp.clientId}")
    public String clientId;
    @Value("${mp.clientSecret}")
    public String clientSecret;
    @Value("${mp.grantType}")
    public String grantType;
    @Value("${mp.access_token}")
    public String token;
    @Value("${mp.urlOrder}")
    public String urlOrder;
    @Value("${mp.urlStatusOrder}")
    public  String urlStatusOrder;

    private static final Logger log = LoggerFactory.getLogger(OrderService.class);
    private int reintentos;
    //Beans

    @Autowired
    @Qualifier("orderStatus")
    OrderStatus orderStatus;

    OrdenDao ordenDao;
    ResponseDTO responseDTO;

       //Desde la notificación del webhook se llama este metodo para buscar la orden

    @Override
    public void obtieneOrden(Notification notify) throws Exception {

        //La notificación entra con /shipments/$NroOrden en parametro resource
        //Concatena Url Mercado Pago con URL de obtencion de Orden la cual reemplaza la raiz de shipments por la urlOrden

        String resource= notify.getResource();
        resource= resource.replace("shipments", urlOrder);
        //Parseamos intentos a 0
        reintentos = 0;
        String urlOrden = url + resource;

        log.info("URL Orden: " + urlOrden);
        String json="";
        //De acuerdo a lo notificado por el webhook, vamos a buscar la orden a MP
        try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
            //Se configura el request con sus cabeceras
            HttpUriRequest httppost = RequestBuilder.get()
                    .setUri(new URI(urlOrden))
                    .setHeader(HttpHeaders.AUTHORIZATION, token)
                    .setHeader(HttpHeaders.CONTENT_TYPE, "application/json")
                    .setHeader(HttpHeaders.CONNECTION, "keep-alive")
                    .setHeader("x-time-zone", "GMT-3")
                    .build();
            //Se envía la http post a MFood
         try (CloseableHttpResponse response = httpclient.execute(httppost)) {
                json = EntityUtils.toString(response.getEntity());
                log.info("Json entrada " + json);
                //Validamos SKU
                responseDTO = validateNotificationType(json);

            }
        } catch (Exception e) {
           log.error("ERROR: Ha la obtención de orden " + e.getMessage());

        }
    }


    //Funcion que valida el estado de la notificación desde el webhook
    public ResponseDTO validateNotificationType(String pedido) throws InterruptedException {

        ordenDao = new OrdenDao();
        Boolean existePedido =false;
        JSONObject unPedido = new JSONObject(pedido);
        String idPedido =unPedido.getString("id");
        existePedido = ordenDao.validaOrdenIngresada(idPedido);

        JSONObject objExtension = unPedido.getJSONObject("extension");
        String status ="";
        String subStatus="";
        try {
             status = objExtension.getString("status");
             subStatus = objExtension.getString("subStatus");

        }catch (Exception e){
            log.error("ERROR: No se encontro status o substatus en Json de entrada pedido: " + idPedido);
        }
        //Se cancela orden por instrucción de MFood
        if (status.equals("cancelled")) {
            try {
                    ordenDao.updateStatusOrden(pedido,"CANCELADA");
                    log.info("INFO: Orden cancelada " +idPedido);

            } catch (Exception e) {
                log.error("MENSAJE: ERROR AL INTENTAR INGRESAR O ACTUALIZAR ESTADO CANCELLED ORDEN:" + idPedido + " EXCEPTION:" + Throwables.getStackTraceAsString(e));
                return new ResponseDTO("Error", "Error interno: no se pudo ingresar orden");

            }
        }
        //ready_to_ship significa que en este estado MP espera 5 minutos respuesta de lo contrario cancela el pedido
        //Y el sub estado ready_to_print es el unico estado en donde se ingresa la orden y acepta a MP
        else if (status.equals("ready_to_ship") && !existePedido) {
                try {
                    //Se parametrizan los campos de ingreso, solucion solo para BK
                    boolean validate = validaSKU(pedido);
                    if (validate){
                        ordenDao.registrarOrden(orderValidateEntityCreate(pedido,"INGRESADA"));
                        log.info("INFO: ORDEN INGRESADA CORRECTAMENTE "+ idPedido);
                        //Se notifica a MP que la orden fue aceptada
                        log.info("INFO: PREPARANDO ACCEPTACION A MP, ORDEN: "+ idPedido);
                        orderStatus.acceptOrderMP(idPedido);
                    }
                    else {
                        ordenDao.registrarOrden(orderValidateEntityCreate(pedido,"CANCELARMP"));
                       return new ResponseDTO("Error en ingreso de orden", "Items sin SKU");
                    }
                    //Se guarda la orden


                } catch (NullPointerException e){

                    log.error("MENSAJE: ERROR NULLPOINTER AL INTENTAR INGRESAR ORDEN:" + idPedido + " EXCEPTION:" + Throwables.getStackTraceAsString(e));
                    //Si falla vamos a reintentar hasta 3 veces
                    if (reintentos < Constants.MAX_REINTENTOS) {
                        Thread.sleep(3000);
                        log.info("MENSAJE: REINTENTANDO INGRESO DE LA ORDEN LUEGO DE 3 SEGUNDOS, ORDEN:" + idPedido);
                        validateNotificationType(pedido);

                    }
                    return new ResponseDTO("Error","Error interno: no se pudo ingresar orden");

                }
                catch (Exception e) {
                    log.error("MENSAJE: ERROR AL INTENTAR INGRESAR ORDEN:" + idPedido + " EXCEPTION:" + Throwables.getStackTraceAsString(e));
                    //Si falla vamos a reintentar hasta 3 veces
                    if (reintentos < Constants.MAX_REINTENTOS) {
                        Thread.sleep(3000);
                        log.info("MENSAJE: REINTENTANDO INGRESO DE LA ORDEN LUEGO DE 3 SEGUNDOS, ORDEN:" + idPedido);
                        validateNotificationType(pedido);
                    }
                    return new ResponseDTO("Error","Error interno: no se pudo ingresar orden");

                }

            } else if (existePedido && (subStatus.equals("on_route_to_pickup")|| subStatus.equals("picking_up"))) {
                try {
                    ordenDao.updateStatusOrden(idPedido,"ACEPTADA");
                } catch (Exception e) {
                    log.error("MENSAJE: ERROR AL INTENTAR ACTUALIZAR ESTADO ACEPTADA, ORDEN:" + idPedido + " EXCEPTION:" + Throwables.getStackTraceAsString(e));
                    if (reintentos < Constants.MAX_REINTENTOS) {
                        Thread.sleep(3000);
                        log.info("MENSAJE: REINTENTANDO ACTUALIZACION DE ESTADO DE LA ORDEN LUEGO DE 3 SEGUNDOS, ORDEN:" + idPedido);
                        validateNotificationType(pedido);
                    }
                    return new ResponseDTO("Error","Error interno: no se pudo ingresar orden");
                }
            }
        else {
            log.warn("MENSAJE: EXISTE UNA NOTIFICACION CON STATUS NO MAPEADO,ORDEN: " + idPedido + ", ESTATUS: " + status + ", SUBSTATUS: "+subStatus);
        }
        return new ResponseDTO("EXITO","Orden ingresada");
    }






    public Boolean validaSKU(String pedido){
        boolean retorna=true;
        JSONObject obj = new JSONObject(pedido);
        JSONArray arr = obj.getJSONArray("items");

        String pedidoId= obj.getString("id");
        for (int i = 0; i < arr.length(); i++) {
            try {
                String externalCode = arr.getJSONObject(i).getString("externalCode");
                JSONArray arrItem = null;
            try {
                arrItem = new JSONArray(arr.getJSONObject(i).getJSONArray("options"));

                if (arrItem != null) {
                    for (int j = 0; j < arrItem.length(); j++) {
                        try {
                            String externalCodeItem = arrItem.getJSONObject(j).getString("externalCode");

                        } catch (Exception e) {
                            log.error("ERROR: No se encontro external code para pedido->" + pedidoId + " item -> " + arr.getJSONObject(i) + " Option -> " + arrItem.getJSONObject(i));
                            retorna= false;
                        }
                    }
                }
            } catch (Exception e) {
                log.info("options de un combo controlado no encontrado");
            }
            } catch (Exception e) {
                log.error("ERROR: No se encontro external code para pedido->" + pedidoId + " producto -> " + arr.getJSONObject(i).getString("id"));
                retorna= false;


            }


        }
      return retorna;
    }

    public OrderValidateEntity orderValidateEntityCreate(String pedido, String status) {

        try
{
        SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        JSONObject obj = new JSONObject(pedido);
        JSONObject objExtension = obj.getJSONObject("extension");
        JSONObject objStore = objExtension.getJSONObject("store");
        return new OrderValidateEntity(2L,
                pedido,
                formatter.parse(obj.getString("createdAt")),
                formatter.parse(obj.getString("createdAt")),
                obj.getString("id"),
                status,
                "BK",
                objStore.getString("id")
                );
}catch (Exception e ){
    log.error("Error: No se pudo parsear fecha o " + Throwables.getStackTraceAsString(e));
    return null;
}

    }

   /* @Async
    @Override
    public CompletableFuture test() throws InterruptedException {

        Thread.sleep(15000);
        System.out.println("Termine de dormir");
        return CompletableFuture.completedFuture("fin");
    }*/

    }




