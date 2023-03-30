package arg.mercadopago.mercadofood.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@Data
@JsonPropertyOrder({"id","sender_id","hash_code","security_digit"})
public class Qr {

    @JsonProperty("id")
    private String id;
    @JsonProperty("sender_id")
    private int sender_id;
    @JsonProperty("hash_code")
    private String hash_code;
    @JsonProperty("security_digit")
    private String security_digit;


}
