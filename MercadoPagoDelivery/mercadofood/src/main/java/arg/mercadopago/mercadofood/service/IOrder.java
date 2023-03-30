package arg.mercadopago.mercadofood.service;

import arg.mercadopago.mercadofood.dto.ResponseDTO;
import arg.mercadopago.mercadofood.model.Notification;

import java.util.concurrent.CompletableFuture;

public interface IOrder {

    void obtieneOrden(Notification urlOrden) throws Exception;
   // CompletableFuture test() throws InterruptedException;

}
