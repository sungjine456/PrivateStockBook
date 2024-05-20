package com.psb.stockbook.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.psb.stockbook.domains.stocks.StockEntity;

public interface StockRepository extends JpaRepository<StockEntity, Long> {

    public boolean existsByCode(String code);
}
