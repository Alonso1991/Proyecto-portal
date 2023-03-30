package arg.mercadopago.mercadofood.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Options {


    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("unit")
    private String unit;

    @JsonProperty("quantity")
    private String quantity;

    @JsonProperty("unitPrice")
    private Price unitPrice;

    @JsonProperty("totalPrice")
    private Price totalPrice;

    @JsonProperty("extension")
    private Extension extension;

    @JsonProperty("externalCode")
    private String externalCode;





}
