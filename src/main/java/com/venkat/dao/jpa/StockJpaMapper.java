package com.venkat.dao.jpa;

import org.mapstruct.Mapper;

@Mapper
public interface StockJpaMapper {

    Stock toEntity(com.venkat.dto.Stock stock);

    com.venkat.dto.Stock toDto(Stock stock);
}
