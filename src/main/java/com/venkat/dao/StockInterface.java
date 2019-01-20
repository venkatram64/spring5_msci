package com.venkat.dao;

import com.venkat.dto.Stock;

public interface StockInterface {
    Stock findById(String id);

    void save(Stock stock);
}
