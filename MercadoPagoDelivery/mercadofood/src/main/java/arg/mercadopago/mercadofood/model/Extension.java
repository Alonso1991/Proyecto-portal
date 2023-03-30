package arg.mercadopago.mercadofood.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Extension {

    @JsonProperty("unitNetPrice")
    private Price unitNetPrice;
    @JsonProperty("status")
    private String status;
    @JsonProperty("subStatus")
    private String subStatus;
    @JsonProperty("store")
    private Store store;
    @JsonProperty("qr")
    private Qr qr;
    @JsonProperty("group")
    private String group;
    @JsonProperty("cancellationDateTime")
    private String cancellationDateTime;








}
