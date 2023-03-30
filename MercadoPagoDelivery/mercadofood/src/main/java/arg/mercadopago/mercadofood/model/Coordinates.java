package arg.mercadopago.mercadofood.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@JsonPropertyOrder({""})
public class Coordinates {

    @JsonProperty("latitude")
    private Double latitude ;
    @JsonProperty("longitude")
    private Double longitude;

}
