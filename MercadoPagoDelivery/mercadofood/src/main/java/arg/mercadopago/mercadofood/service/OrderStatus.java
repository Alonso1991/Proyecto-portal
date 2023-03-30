package arg.mercadopago.mercadofood.service;

import org.springframework.beans.factory.annotation.Qualifier;

@Qualifier("orderStatus")
public interface OrderStatus {

    void acceptOrderMP(String orderId);
    void statusOrderCancelMP(String orderId);
}
