package com.venkat.dao.redis;

import javax.annotation.Resource;

import com.venkat.dao.StockInterface2;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
@Qualifier("redisReactive")
public class StockRedisReactiveDao implements StockInterface2 {

    @Resource
    private StockRedisMapper stockRedisMapper;

    @Resource
    private ReactiveRedisTemplate<String, Stock> reactiveRedisTemplate;

    @Override
    public Mono<com.venkat.dto.Stock> findById(String id) {
        return reactiveRedisTemplate
                .opsForValue()
                .get(id)
                .map(stock -> stockRedisMapper.toDto(stock));
    }

    @Override
    @Transactional
    public void save(com.venkat.dto.Stock stock) {
        reactiveRedisTemplate
                .opsForValue()
                .set(stock.getId(), stockRedisMapper.toEntity(stock))
                .subscribe();
    }

    @Override
    public Flux<com.venkat.dto.Stock> findAllByIds(String[] ids) {
        return Flux.fromArray(ids)
                .map(id -> reactiveRedisTemplate.opsForValue().get(id))
                .flatMap(Flux::from)
                .map(stock -> stockRedisMapper.toDto(stock));
    }

}

