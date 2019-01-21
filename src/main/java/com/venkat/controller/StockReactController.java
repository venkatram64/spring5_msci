package com.venkat.controller;

import com.venkat.dao.StockInterface2;
import com.venkat.dto.Stock;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;

//Fully reactive with Spring WebFlux

@RestController
@RequestMapping(produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
public class StockReactController {

    @Resource
    @Qualifier("redisReactive")
    private StockInterface2 stockInterface;

    @GetMapping("/stockR/{id}")
    public Mono<Stock> getInfo(@PathVariable("id") String id){
        Mono<Stock> stock = stockInterface.findById(id);
        if (stock == null) {
            throw new IllegalStateException("Sorry we do not have stock information for given company");
        }
        return stock;
    }

    @PutMapping("/stockR/{id}")
    public void saveStock(@PathVariable("id") String id, @RequestBody Stock stock) {
        stockInterface.save(stock);
    }

    @GetMapping("/stocksR/{ids}")
    public Flux<Stock> findAllByIds(@PathVariable("ids") String ids){
        return stockInterface.findAllByIds(ids.split(","));
    }
}
