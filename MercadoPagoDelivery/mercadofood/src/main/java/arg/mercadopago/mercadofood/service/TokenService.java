package arg.mercadopago.mercadofood.service;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

import java.net.URI;

@Service
public class TokenService implements Token{

    public String getToken() throws Exception{
        CloseableHttpClient httpclient = HttpClients.createDefault();
        try {
            HttpUriRequest httppost = RequestBuilder.post()
                    .setUri(new URI("https://postman-echo.com/post"))
                    .addParameter("foo1", "bar1")
                    .addParameter("foo2", "bar2")
                    .build();

            CloseableHttpResponse response = httpclient.execute(httppost);
            try {
                System.out.println(EntityUtils.toString(response.getEntity()));
            } finally {
                response.close();
            }
        } finally {
            httpclient.close();
        }


        return null;
    }
}

