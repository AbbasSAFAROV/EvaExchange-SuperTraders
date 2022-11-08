package com.eva.tradingApp.controller;


import com.eva.tradingApp.domain.entity.Portfolio;
import com.eva.tradingApp.domain.entity.Stock;
import com.eva.tradingApp.domain.request.DepositWithdrawRequest;
import com.eva.tradingApp.service.PortfolioService;
import com.eva.tradingApp.service.TraderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/portfolios")
public class PortfolioController {

    private final PortfolioService portfolioService;

    public PortfolioController(PortfolioService portfolioService) {
        this.portfolioService = portfolioService;
    }

    @GetMapping
    public ResponseEntity<List<Portfolio>> getAllPortfolios(){
        return new ResponseEntity<>(portfolioService.getAllPortfolios(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Portfolio> getStockWithTraderId(@PathVariable Long id){
        return new ResponseEntity<>(portfolioService.getStockWithTraderId(id), HttpStatus.OK);
    }

    @PutMapping("/withdraw/{id}")
    public String withdrawWithTraderId(@PathVariable Long id, @RequestBody DepositWithdrawRequest request){
        return portfolioService.withdrawWithTraderId(id, request.getAmount());
    }

    @PutMapping("/deposit/{id}")
    public String depositWithTraderId(@PathVariable Long id, @RequestBody DepositWithdrawRequest request){
        return portfolioService.depositWithTraderId(id, request.getAmount());
    }

}
