package com.venkat.dao;

import com.venkat.dto.Stock;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface StockInterface2 {

    Mono<Stock> findById(String id);

    void save(Stock stock);

    Flux<Stock> findAllByIds(String[] split);
}
