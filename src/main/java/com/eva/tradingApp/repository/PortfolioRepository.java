package com.eva.tradingApp.repository;

import com.eva.tradingApp.domain.entity.Portfolio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PortfolioRepository extends JpaRepository<Portfolio, Long> {
}
