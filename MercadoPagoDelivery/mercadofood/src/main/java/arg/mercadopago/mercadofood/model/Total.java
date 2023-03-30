package arg.mercadopago.mercadofood.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@Data
@JsonPropertyOrder({"itemsPrice","otherFees","discount","orderAmount"})
public class Total {

    @JsonProperty("itemsPrice")
    private Price itemsPrice;
    @JsonProperty("otherFees")
    private Price otherFees;
    @JsonProperty("discount")
    private Price discount;
    @JsonProperty("orderAmount")
    private Price orderAmount;
}
