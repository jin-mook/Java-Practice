package com.example.ssedemo.ddd.controller;

import com.example.ssedemo.ddd.domain.Order;
import com.example.ssedemo.ddd.domain.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping("/make-order")
    public String makeOrder(@RequestParam("name") String name,
                           @RequestParam("price") int price) {
        log.info("name = {}, price = {}", name, price);

        orderService.makeOrder(name, price);
        return "ok";
    }
}
