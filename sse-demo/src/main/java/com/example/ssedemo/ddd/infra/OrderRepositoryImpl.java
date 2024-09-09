package com.example.ssedemo.ddd.infra;

import com.example.ssedemo.ddd.domain.Order;
import com.example.ssedemo.ddd.domain.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.NoSuchElementException;

@Component
class OrderRepositoryImpl implements OrderRepository {

    private final JpaOrderRepository jpaOrderRepository;

    @Autowired
    OrderRepositoryImpl(JpaOrderRepository jpaOrderRepository) {
        this.jpaOrderRepository = jpaOrderRepository;
    }

    @Override
    public Order findById(Long id) {
        JpaOrder jpaOrder = jpaOrderRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("해당하는 Order가 없습니다."));
        return jpaOrder.toOrder();
    }

    @Override
    public void save(Order order) {
        JpaOrder jpaOrder = JpaOrder.fromOrder(order);
        jpaOrderRepository.save(jpaOrder);
    }
}
