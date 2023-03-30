package arg.mercadopago.mercadofood.repositories;

import arg.mercadopago.mercadofood.entity.OrderValidateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;


public interface IOrderValidateRepository extends JpaRepository<OrderValidateEntity, String>, JpaSpecificationExecutor<OrderValidateEntity> {

    @Modifying
    @Transactional
    OrderValidateEntity findByOrderAgregador(String idOrder);

    @Modifying
    @Transactional
    @Query("update orden o set o.status = ?1, o.date_mod = ?2 where o.orderAgregador = ?3")
    void setOrderForIdAgregador(String status, String date, String agregadorId);


}
