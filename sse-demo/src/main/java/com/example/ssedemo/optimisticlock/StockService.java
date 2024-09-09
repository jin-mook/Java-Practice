package com.example.ssedemo.optimisticlock;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class StockService {

    private final StockRepository stockRepository;

    @Transactional
    public Stock updateStock(Long id, int quantity) {
        Stock stock = stockRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("상품이 없습니다."));

        stock.update(quantity);
        return stock;
    }
}
