package arg.mercadopago.mercadofood.entity;

import com.sun.istack.NotNull;
import lombok.Data;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Table(name = "ORDENES")
@Entity(name = "orden")
@Qualifier("orden")
public class OrderValidateEntity implements Serializable {

    private static final long serialVersionUID = -5826565839103687170L;

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="id")
    private long idOrder;

    @NotNull
    @Column(name = "mensaje")
    private String orderRequest;

    @NotNull
    @Column(name = "ingreso_date")
    private Date date;

    @NotNull
    @Column(name = "date_modify")
    private Date date_mod;

    @NotNull
    @Column(name = "order_agregador")
    private String orderAgregador;

    @NotNull
    @Column(name = "estado")
    private String status;

    @NotNull
    @Column(name = "marca")
    private String marca;

    @Column(name = "store_id")
    private String store_id;

    public OrderValidateEntity() {
    }

    public OrderValidateEntity(long idOrder, String orderRequest, Date date, Date date_mod, String orderAgregador, String status, String marca, String store_id) {
        this.idOrder = idOrder;
        this.orderRequest = orderRequest;
        this.date = date;
        this.date_mod = date_mod;
        this.orderAgregador = orderAgregador;
        this.status = status;
        this.marca = marca;
        this.store_id = store_id;
    }
}
