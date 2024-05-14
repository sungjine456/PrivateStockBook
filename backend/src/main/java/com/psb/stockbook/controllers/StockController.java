package com.psb.stockbook.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.psb.stockbook.domains.StockDto;
import com.psb.stockbook.domains.StockEntity;
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

        return stockRepository.findAll().stream().map(s -> new StockDto(s.getName())).toList();
    }

    @PostMapping("/stock")
    public String addStock(@RequestParam String name) {
        log.info("add stock, name: {}", name);

        stockRepository.save(new StockEntity(name));

        return "Success";
    }
}
