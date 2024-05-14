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

@RestController
public class StockController {

    @Autowired
    StockRepository stockRepository;

    @GetMapping("/stock")
    public List<StockDto> getStock() {
        System.out.println("get stock");

        return stockRepository.findAll().stream().map(s -> new StockDto(s.getName())).toList();
    }

    @PostMapping("/stock")
    public String addStock(@RequestParam String name) {
        System.out.println("add stock");

        stockRepository.save(new StockEntity(name));

        return "Success";
    }
}
