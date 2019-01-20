package com.venkat.dao.jdbc;

import com.venkat.dao.StockInterface;
import com.venkat.dto.Stock;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import javax.transaction.Transactional;


@Repository
@Qualifier("jdbc")
public class StockJdbcDao implements StockInterface {

    @Resource
    private JdbcTemplate jdbcTemplate;

    @Override
    public Stock findById(String id) {
        String sqlQuery = "SELECT id, name, price FROM stocks WHERE id = ?";
        return jdbcTemplate.queryForObject(sqlQuery, new Object[] { id }, new BeanPropertyRowMapper<>(Stock.class));
    }

    @Override
    @Transactional
    public void save(Stock stock) {
        String sqlQuery = "MERGE INTO stocks KEY(id) VALUES (?, ?, ?)";
        jdbcTemplate.update(sqlQuery, stock.getId(), stock.getName(), stock.getPrice());
    }
}
