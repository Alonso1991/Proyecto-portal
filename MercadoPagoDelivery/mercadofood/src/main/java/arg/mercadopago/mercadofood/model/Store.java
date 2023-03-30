package arg.mercadopago.mercadofood.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@Data
@JsonPropertyOrder({"id","externalCode"})
public class Store {


    @JsonProperty("id")
    private String id;
    @JsonProperty("externalCode")
    private String externalCode;


}
