package arg.mercadopago.mercadofood.service;

import arg.mercadopago.mercadofood.dao.OrdenDao;
import arg.mercadopago.mercadofood.util.PropertiesVar;
import com.google.common.base.Throwables;
import org.apache.http.HttpHeaders;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
@Service
public class SearchCancelToMP extends Thread{

    OrdenDao ordenDao;



    private static final Logger log = LoggerFactory.getLogger(SearchCancelToMP.class);

    private Thread worker;
    private final AtomicBoolean running = new AtomicBoolean(false);
    private int interval;


private final PropertiesVar prop;


    public SearchCancelToMP(PropertiesVar prop) {
        this.prop = prop;

        interval = 30000;
    }

    public void start() {
        worker = new Thread(this);
        worker.start();
    }

    public void run() {
        running.set(true);
        while (running.get()) {
            try {
                try {
                     ordenDao=new OrdenDao();

                    List<String> orders=ordenDao.recuperaOrdenesCanceladas();
                    if(orders.size()>0) {
                        statusOrderCancelMP(orders);
                    }

                }catch(NullPointerException e){
                    log.info("INFO: No existen ordenes a cancelar" );
                    Thread.sleep(interval);
                    Thread.currentThread().interrupt();
                }catch(Exception e) {
                    log.info("ERROR: Fallo proceso de obtencion de cancelaciones..." + Throwables.getStackTraceAsString(e));
                    Thread.sleep(interval);
                    Thread.currentThread().interrupt();
                }
                Thread.sleep(interval);
            } catch (InterruptedException e){
                System.out.println(
                        "Thread was interrupted, Failed to complete operation");
            }
            // do something here
        }
    }

    public void statusOrderCancelMP(List<String> orders) {
        String statusBody="";
        ordenDao=new OrdenDao();
        for(int i=0;i < orders.size();i++){
        String orderId = orders.get(i);
        String urlOrder = prop.urlStatusOrder.replace("{shipment_id}", orderId) + "cancel";
        statusBody = "{\"status\": \"cancelled\"}";


        //De acuerdo a lo notificado por el webhook, vamos a buscar la orden a MP
        CloseableHttpClient httpclient = HttpClients.createDefault();
        try {
            log.info("Enviando Cancelacion orden: "+ orderId);
            HttpUriRequest httppost = null;
            try {
                httppost = RequestBuilder.put()
                        .setUri(new URI(urlOrder))
                        .setHeader(HttpHeaders.AUTHORIZATION, prop.token)
                        .setHeader(HttpHeaders.CONTENT_TYPE, "application/json")
                        .setHeader(HttpHeaders.CONNECTION, "keep-alive")
                        .setEntity(new StringEntity(statusBody, ContentType.APPLICATION_JSON))
                        .build();

            } catch (URISyntaxException e) {
                e.printStackTrace();
                httpclient.close();
            }

            CloseableHttpResponse response = httpclient.execute(httppost);

            System.out.println("RESPONSE CANCEL ORDER: " + response.getEntity());

            httpclient.close();
            ordenDao.updateStatusOrden(orderId, "CANCELADAMP");

        } catch (IOException e) {
            e.printStackTrace();

        }


    }


}}
