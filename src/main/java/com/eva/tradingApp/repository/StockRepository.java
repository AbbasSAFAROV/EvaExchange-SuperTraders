package com.eva.tradingApp.repository;

import com.eva.tradingApp.domain.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StockRepository extends JpaRepository<Stock, Long> {

    Optional<Stock> findStockByStockSymbol(String name);

}
