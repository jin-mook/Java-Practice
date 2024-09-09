package com.example.ssedemo.ddd.domain;

public interface OrderRepository {

    Order findById(Long id);
    void save(Order order);
}
