package arg.mercadopago.mercadofood.service;

import arg.mercadopago.mercadofood.entity.OrderValidateEntity;
import arg.mercadopago.mercadofood.repositories.IOrderValidateRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Service
public class OrderValidateService implements IOrderValidateRepository
{
    @Override
    public OrderValidateEntity findByOrderAgregador(String idOrder) {
        return null;
    }

    @Override
    public void setOrderForIdAgregador(String status, String date, String agregadorId) {

    }

    @Override
    public <S extends OrderValidateEntity> S save(S entity) {
        return null;
    }

    @Override
    public List<OrderValidateEntity> findAll() {
        return null;
    }

    @Override
    public List<OrderValidateEntity> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<OrderValidateEntity> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<OrderValidateEntity> findAllById(Iterable<String> strings) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(String s) {

    }

    @Override
    public void delete(OrderValidateEntity entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends String> strings) {

    }

    @Override
    public void deleteAll(Iterable<? extends OrderValidateEntity> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public <S extends OrderValidateEntity> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<OrderValidateEntity> findById(String s) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(String s) {
        return false;
    }

    @Override
    public void flush() {

    }

    @Override
    public <S extends OrderValidateEntity> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public <S extends OrderValidateEntity> List<S> saveAllAndFlush(Iterable<S> entities) {
        return null;
    }

    @Override
    public void deleteAllInBatch(Iterable<OrderValidateEntity> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<String> strings) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public OrderValidateEntity getOne(String s) {
        return null;
    }

    @Override
    public OrderValidateEntity getById(String s) {
        return null;
    }

    @Override
    public OrderValidateEntity getReferenceById(String s) {
        return null;
    }

    @Override
    public <S extends OrderValidateEntity> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends OrderValidateEntity> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends OrderValidateEntity> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends OrderValidateEntity> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends OrderValidateEntity> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends OrderValidateEntity> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends OrderValidateEntity, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }

    @Override
    public Optional<OrderValidateEntity> findOne(Specification<OrderValidateEntity> spec) {
        return Optional.empty();
    }

    @Override
    public List<OrderValidateEntity> findAll(Specification<OrderValidateEntity> spec) {
        return null;
    }

    @Override
    public Page<OrderValidateEntity> findAll(Specification<OrderValidateEntity> spec, Pageable pageable) {
        return null;
    }

    @Override
    public List<OrderValidateEntity> findAll(Specification<OrderValidateEntity> spec, Sort sort) {
        return null;
    }

    @Override
    public long count(Specification<OrderValidateEntity> spec) {
        return 0;
    }

    @Override
    public boolean exists(Specification<OrderValidateEntity> spec) {
        return false;
    }
}
