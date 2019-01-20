package com.venkat.dao.redis;

import com.venkat.dao.StockInterface;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import com.venkat.dto.Stock;

import javax.annotation.Resource;
import javax.transaction.Transactional;

@Repository
@Qualifier("redis")
public class StockRedisDao implements StockInterface {

    @Resource
    private StockRedisRepository stockRedisRepository;

    @Resource
    private StockRedisMapper stockRedisMapper;

    @Override
    public Stock findById(String id) {
        return stockRedisRepository.findById(id)
                .map(stockEntity -> stockRedisMapper.toDto(stockEntity))
                .orElse(null);
    }

    @Override
    @Transactional
    public void save(Stock stock) {
        stockRedisRepository.save(stockRedisMapper.toEntity(stock));
    }
}

