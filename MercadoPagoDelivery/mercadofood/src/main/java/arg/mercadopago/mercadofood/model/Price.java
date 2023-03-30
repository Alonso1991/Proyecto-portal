package arg.mercadopago.mercadofood.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@Data
@JsonPropertyOrder({""})
public class Price {

    @JsonProperty("value")
    private int value;

    @JsonProperty("currency")
    private String currency;
}
