package com.eva.tradingApp.repository;

import com.eva.tradingApp.domain.entity.Trader;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TraderRepository extends JpaRepository<Trader, Long> {
}
