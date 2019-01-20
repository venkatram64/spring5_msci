package com.venkat.dao.jpa;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.venkat.dao.StockInterface;
import com.venkat.dto.Stock;

@Repository
@Qualifier("jpa")
public class StockJpaDao implements StockInterface {

    @Resource
    private StockJpaRepository stockJpaRepository;

    @Resource
    private StockJpaMapper stockJpaMapper;

    @Override
    public Stock findById(String id) {
        return stockJpaRepository.findByNameIgnoreCaseStartingWith(id)
                .map(stockEntity -> stockJpaMapper.toDto(stockEntity))
                .orElse(null);
    }

    @Override
    @Transactional
    public void save(Stock stock) {
        stockJpaRepository.save(stockJpaMapper.toEntity(stock));
    }
}
