package com.venkat.controller;

import com.venkat.dao.StockInterface;
import com.venkat.dto.Stock;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping(produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
public class StockController {

    @Resource
    //@Qualifier("redis")
    //@Qualifier("jpa")
    @Qualifier("jdbc")
    private StockInterface stockInterface;

    @GetMapping("/stock/{id}")
    public Stock getInfo(@PathVariable("id") String id) {
        Stock stock = stockInterface.findById(id);
        if (stock == null) {
            throw new IllegalStateException("Sorry we do not have stock information for given company");
        }
        return stock;
    }

    @PutMapping("/stock/{id}")
    public void saveStock(@PathVariable("id") String id, @RequestBody Stock stock) {
        stockInterface.save(stock);
    }
}
