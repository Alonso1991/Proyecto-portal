package arg.mercadopago.mercadofood.service;

import arg.mercadopago.mercadofood.util.PropertiesVar;
import org.apache.http.HttpHeaders;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

@Service
@Qualifier("orderStatus")
public class OrderStatusService implements OrderStatus {

    private final PropertiesVar prop;

    public OrderStatusService(PropertiesVar prop) {
        this.prop = prop;
    }

    //Metodo que notifica aceptación de orden
    @Override
    public void acceptOrderMP(String orderId) {
        String statusBody="";

        String urlOrder = prop.urlStatusOrder.replace("{shipment_id}", orderId) + "accept";

                statusBody = "{ \"status\": \"accepted\" }";

        //De acuerdo a lo notificado por el webhook, vamos a buscar la orden a MP
        CloseableHttpClient httpclient = HttpClients.createDefault();
        try {
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

            System.out.println("RESPONSE ACCEPTED ORDER: " + response.getEntity());

            httpclient.close();

        } catch (IOException e) {
            e.printStackTrace();

        }


    }

    @Override
    public void statusOrderCancelMP(String orderId) {
        String statusBody="";

        String urlOrder = prop.urlStatusOrder.replace("{shipment_id}", orderId) + "cancel";
        statusBody = "{\"status\": \"cancelled\"}";


        //De acuerdo a lo notificado por el webhook, vamos a buscar la orden a MP
        CloseableHttpClient httpclient = HttpClients.createDefault();
        try {
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

        } catch (IOException e) {
            e.printStackTrace();

        }


    }
}
