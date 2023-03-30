package arg.mercadopago.mercadofood.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import java.sql.Date;
import java.util.List;

@Data
@JsonPropertyOrder({"id","type","displayId","createdAt","orderTiming", "preparationStartDateTime","merchant","items",
        "total","payments","customer","delivery","extension"})
public class Order {

    @JsonProperty("id")
    private String id;
    @JsonProperty("type")
    private String type;
    @JsonProperty("displayId")
    private String displayId;
    @JsonProperty("createdAt")
    private Date createdAt;
    @JsonProperty("orderTiming")
    private String orderTiming;
    @JsonProperty("preparationStartDateTime")
    private String preparationStartDateTime;
    @JsonProperty("merchant")
    private Merchant merchant;
    @JsonProperty("items")
    private List<Items> items;
    @JsonProperty("discounts")
    private List<Discount> discounts;
    @JsonProperty("total")
    private Total total;
    @JsonProperty("payments")
    private Payments payments;
    @JsonProperty("customer")
    private Customer customer;
    @JsonProperty("delivery")
    private Delivery delivery;
    @JsonProperty("extension")
    private Extension extension;


}
