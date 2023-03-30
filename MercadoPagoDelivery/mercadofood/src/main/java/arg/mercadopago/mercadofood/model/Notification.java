package arg.mercadopago.mercadofood.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import java.util.Date;

@Data
@JsonPropertyOrder({"_id","topic","resource","user_id","application_id","received"})
public class Notification {

    @JsonProperty("_id")
    private String id;

    @JsonProperty("topic")
    private String topic;

    @JsonProperty("resource")
    private String resource;

    @JsonProperty("user_id")
    private String userId;

    @JsonProperty("application_id")
    private Long app_id;

    @JsonProperty("received")
    private Date receivedDate;




}
