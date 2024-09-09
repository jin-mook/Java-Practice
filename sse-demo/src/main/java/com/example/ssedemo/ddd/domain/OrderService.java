package com.example.ssedemo.ddd.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public void makeOrder(String name, int price) {
        Order order = new Order(name, price);
        orderRepository.save(order);
    }
}
