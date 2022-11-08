package com.eva.tradingApp.controller;


import com.eva.tradingApp.domain.dto.StockDto;
import com.eva.tradingApp.domain.request.BuyRequest;
import com.eva.tradingApp.service.StockService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/buy")
public class BuySellController {

    private final StockService stockService;

    public BuySellController(StockService stockService) {
        this.stockService = stockService;
    }

    @PostMapping
    public ResponseEntity<String> buyStock(@RequestBody BuyRequest request){
        stockService.buyStock(request.getTraderId(), request.getStockId(), request.getAmount());
        return new ResponseEntity<>("successful", HttpStatus.CREATED);
    }

}
