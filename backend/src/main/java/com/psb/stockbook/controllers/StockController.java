package com.psb.stockbook.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.psb.stockbook.domains.stocks.StockDto;
import com.psb.stockbook.domains.stocks.StockEntity;
import com.psb.stockbook.repositories.StockRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class StockController {

    @Autowired
    StockRepository stockRepository;

    @GetMapping("/stock")
    public List<StockDto> getStock() {
        log.info("get stock");

        return stockRepository.findAll().stream().map(s -> s.getDto()).toList();
    }

    @PostMapping("/stock")
    public String addStock(@RequestBody StockDto stock) {
        log.info("add stock, {}", stock);

        if (!stockRepository.existsByCode(stock.getCode())) {
            stockRepository.save(new StockEntity(stock));

            return "Success";
        } else {
            return "Failure";
        }
    }
}
