package com.venkat.controller;

import com.venkat.dao.StockInterface;
import com.venkat.dto.Stock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.util.NestedServletException;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
public class StockController2Test {

    private MockMvc mockMvc;
    private StockInterface stockInterface;

    @BeforeEach
    void setup(){

        stockInterface = Mockito.mock(StockInterface.class);
        StockController2 stockController = new StockController2(stockInterface);
        this.mockMvc = MockMvcBuilders.standaloneSetup(stockController).build();
    }

    @Test
    void shouldGetStockById() throws Exception{

        String stockId = "NASDAQ:GOOG";
        Stock stock = new Stock();
        stock.setId(stockId);
        stock.setName("Alphabet Inc.");
        stock.setPrice(975.63);
        when(stockInterface.findById(stockId)).thenReturn(stock);
        mockMvc.perform(get("/stock2/{id}", stockId)).andExpect(status().isOk());
    }

    @Test
    void shouldHandleAbsentGetStockById() throws Exception {

        Throwable exception = assertThrows(NestedServletException.class, () ->{
            mockMvc.perform(get("stock2/{id}", "NASDAQ:GOOG")).andExpect(status().isOk());
        });
        assertTrue(exception.getMessage().contains("Sorry we do not have stock information for given company"));
    }
}
