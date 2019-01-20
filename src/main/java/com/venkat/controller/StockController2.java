package com.venkat.controller;


import com.venkat.dao.StockInterface;
import com.venkat.dto.Stock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
public class StockController2 {

    private StockInterface stockInterface;

    public StockController2(@Autowired @Qualifier("jdbc") StockInterface stockInterface) {
        this.stockInterface = stockInterface;
    }

    @GetMapping("/stock2/{id}")
    public Stock getInfo(@PathVariable("id") String id) {
        Stock stock = stockInterface.findById(id);
        if (stock == null) {
            throw new IllegalStateException("Sorry we do not have stock information for given company");
        }
        return stock;
    }

    @PutMapping("/stock2/{id}")
    public void saveStock(@PathVariable("id") String id, @RequestBody Stock stock) {
        stockInterface.save(stock);
    }
}
