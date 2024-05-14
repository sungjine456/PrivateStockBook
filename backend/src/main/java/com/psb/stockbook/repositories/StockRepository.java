package com.psb.stockbook.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.psb.stockbook.domains.StockEntity;

public interface StockRepository extends JpaRepository<StockEntity, Long> {

}
