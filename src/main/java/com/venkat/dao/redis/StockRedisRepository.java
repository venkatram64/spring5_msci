package com.venkat.dao.redis;

import org.springframework.data.repository.CrudRepository;

public interface StockRedisRepository extends CrudRepository<Stock, String> {
}
