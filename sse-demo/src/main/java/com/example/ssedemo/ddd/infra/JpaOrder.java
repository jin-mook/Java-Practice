package com.example.ssedemo.ddd.infra;

import com.example.ssedemo.ddd.domain.Order;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class JpaOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int price;

    public JpaOrder(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public Order toOrder() {
        return new Order(id, name, price);
    }

    public static JpaOrder fromOrder(Order order) {
        return new JpaOrder(order.getName(), order.getPrice());
    }
}
