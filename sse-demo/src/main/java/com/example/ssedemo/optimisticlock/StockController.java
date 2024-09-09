package com.example.ssedemo.optimisticlock;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
public class StockController {

    private final StockService stockService;

    @GetMapping("/stock/{id}")
    public Stock updateStock(@PathVariable("id") Long id, @RequestParam("quantity") int quantity) {
        log.info("id = {}, quantity = {}", id, quantity);
        return stockService.updateStock(id, quantity);
    }


}
