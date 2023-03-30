package arg.mercadopago.mercadofood.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import java.util.Date;

@Data
@JsonPropertyOrder({""})
public class Delivery {

    @JsonProperty("deliveredBy")
    private String deliveredBy;
    @JsonProperty("deliveryAddress")
    private DeliveryAddress deliveryAddress;
    @JsonProperty("estimatedDeliveryDateTime")
    private String estimatedDeliveryDateTime;
    @JsonProperty("deliveryDateTime")
    private String deliveryDateTime;





}
