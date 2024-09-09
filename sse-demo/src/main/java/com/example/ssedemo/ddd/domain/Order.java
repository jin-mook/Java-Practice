package com.example.ssedemo.ddd.domain;

import lombok.Getter;

@Getter
public class Order {

    private Long orderId;
    private String name;
    private int price;

    public Order(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public Order(Long orderId, String name, int price) {
        this.orderId = orderId;
        this.name = name;
        this.price = price;
    }
}
