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

        // TODO: 코드가 중복되는 경우를 대비한 코드 필요
        stockRepository.save(new StockEntity(stock));

        return "Success";
    }
}
