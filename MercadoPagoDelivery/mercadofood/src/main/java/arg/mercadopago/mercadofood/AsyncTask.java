package arg.mercadopago.mercadofood;

import arg.mercadopago.mercadofood.dto.ResponseDTO;
import arg.mercadopago.mercadofood.model.Notification;
import arg.mercadopago.mercadofood.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@Qualifier("asyncTask")
public class AsyncTask {

    final
    OrderService orderService;

    public AsyncTask(OrderService orderService) {
        this.orderService = orderService;
    }

    @Async
    public void obtieneOrden(Notification notify) throws Exception {

        orderService.obtieneOrden(notify);

    }

    @Async
    public void test() throws Exception {

        Thread.sleep(5000);
        System.out.println("TEST ASYNC 5 sec");

    }
}
