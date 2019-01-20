package com.venkat.dao.redis;

import org.mapstruct.Mapper;

@Mapper
public interface StockRedisMapper {

    Stock toEntity(com.venkat.dto.Stock stock);

    com.venkat.dto.Stock toDto(Stock stock);
}
