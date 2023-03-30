package arg.mercadopago.mercadofood.util;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class PropertiesVar {

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



}
