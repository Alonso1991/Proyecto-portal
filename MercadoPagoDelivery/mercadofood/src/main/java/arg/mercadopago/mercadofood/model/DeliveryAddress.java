package arg.mercadopago.mercadofood.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@Data
@JsonPropertyOrder({""})
public class DeliveryAddress {

    @JsonProperty("country")
    private String country;
    @JsonProperty("state")
    private String state;
    @JsonProperty("city")
    private String city;
    @JsonProperty("street")
    private String street;
    @JsonProperty("number")
    private String number;
    @JsonProperty("reference")
    private String reference;
    @JsonProperty("formattedAddress")
    private String formattedAddress;
    @JsonProperty("postalCode")
    private String postalCode;
    @JsonProperty("coordinates")
    private Coordinates coordinates;


}
