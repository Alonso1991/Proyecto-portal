package arg.mercadopago.mercadofood.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import java.util.List;

@Data
@JsonPropertyOrder({"prepaid","pending","methods"})
public class Payments {

    @JsonProperty("prepaid")
    private int prepaid;

    @JsonProperty("pending")
    private String pending;

    @JsonProperty("methods")
    private List<Methods> methods;


}
