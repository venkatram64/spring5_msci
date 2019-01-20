package com.venkat.dao.jdbc;

import static org.assertj.core.api.Assertions.assertThat;

import com.venkat.dao.StockInterface;
import com.venkat.dto.Stock;
import com.venkat.test.SpringTestSupport;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTestContextBootstrapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.BootstrapWith;
import org.springframework.test.context.transaction.AfterTransaction;
import org.springframework.test.jdbc.JdbcTestUtils;

import javax.annotation.Resource;

@SpringTestSupport
@BootstrapWith(SpringBootTestContextBootstrapper.class)
public class StockJdbcDaoTest {

    @Resource
    private JdbcTemplate jdbcTemplate;

    @Resource
    @Qualifier("jdbc")
    private StockInterface stockInterface;

    @Test
    void shouldFindExistingStock() {
        assertThat(stockInterface.findById("NASDAQ:GOOG")).isNotNull();
    }

    @Test
    void shouldCreateStock() throws Exception {
        int stocksNumBefore = retrieveStocksNum();
        Stock stock = new Stock();
        stock.setId("NASDAQ:FB");
        stock.setName("Facebook Inc.");
        stock.setPrice(135.5);
        stockInterface.save(stock);
        int stocksNumAfter = retrieveStocksNum();
        assertThat(stocksNumAfter).isGreaterThan(stocksNumBefore);
    }

    @AfterTransaction
    void shouldCountInitialNumberOfStocks() {
        int stocksNum = retrieveStocksNum();
        assertThat(stocksNum).isEqualTo(3);
        System.out.println(String.format("Number of rows verified %s", stocksNum));
    }

    private int retrieveStocksNum() {
        return JdbcTestUtils.countRowsInTable(jdbcTemplate, "stocks");
    }


}
