package arg.mercadopago.mercadofood.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@Data
@JsonPropertyOrder({"value","currency","type","method"})
public class Methods {


    @JsonProperty("value")
    private int value;
    @JsonProperty("currency")
    private String currency;
    @JsonProperty("type")
    private String type;
    @JsonProperty("method")
    private String method;


}
