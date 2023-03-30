package arg.mercadopago.mercadofood.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class Discount {

    @JsonProperty("amount")
    private Price amount;
    @JsonProperty("target")
    private String target;
    @JsonProperty("sponsorshipValues")
    private List<Merchant>sponsorshipValues;
    @JsonProperty("extension")
    private Merchant extension;


}
