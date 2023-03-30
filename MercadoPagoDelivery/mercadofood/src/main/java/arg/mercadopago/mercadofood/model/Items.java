package arg.mercadopago.mercadofood.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import java.util.List;

@Data
@JsonPropertyOrder({"id","name","externalCode","unit","quantity","specialInstructions"})
public class Items {

    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("externalCode")
    private String externalCode;

    @JsonProperty("unit")
    private String unit;

    @JsonProperty("quantity")
    private int quantity;

    @JsonProperty("specialInstructions")
    private String specialInstructions;

    @JsonProperty("options")
    private List<Options> options;

    @JsonProperty("unitPrice")
    private Price unitPrice;

    @JsonProperty("optionsPrice")
    private Price optionsPrice;

    @JsonProperty("totalPrice")
    private Price totalPrice;

    @JsonProperty("extension")
    private Extension extension;


}
