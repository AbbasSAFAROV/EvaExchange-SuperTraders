package com.eva.tradingApp.controller;


import com.eva.tradingApp.domain.converter.Converter;
import com.eva.tradingApp.domain.dto.StockDto;
import com.eva.tradingApp.domain.entity.Stock;
import com.eva.tradingApp.domain.entity.StockItem;
import com.eva.tradingApp.domain.request.BuyRequest;
import com.eva.tradingApp.domain.request.SellRequest;
import com.eva.tradingApp.domain.request.StockCreateRequest;
import com.eva.tradingApp.domain.request.UpdatePriceRequest;
import com.eva.tradingApp.service.StockService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/stocks")
public class StockController {

    private final StockService stockService;
    private final Converter converter;

    public StockController(StockService stockService, Converter converter) {
        this.stockService = stockService;
        this.converter = converter;
    }

    @GetMapping()
    public ResponseEntity<List<StockDto>> getAllStocks(){
        return new ResponseEntity<>(stockService.getAllStocks(),HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<StockDto>  createStock(@RequestBody StockCreateRequest request){
        //Boolean isValid = validateStock(request);
        return new ResponseEntity<>(stockService.createStock(request), HttpStatus.CREATED);
    }

    @PostMapping("/buy")
    public ResponseEntity<String> buyStock(@RequestBody BuyRequest request){
        stockService.buyStock(request.getTraderId(), request.getStockId(), request.getAmount());
        return new ResponseEntity<>("successful",HttpStatus.CREATED);
    }

    @PostMapping("/sell")
    public ResponseEntity<String> sellStock(@RequestBody SellRequest request){
        stockService.sellStock(request.getTraderId(), request.getStockId(), request.getAmount());
        return new ResponseEntity<>("successful",HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public StockDto updateStockPriceWithId(@RequestBody UpdatePriceRequest request, @PathVariable Long id){
        return stockService.updateStockPrice(request.getPrice(), id);
    }

    @GetMapping("/{id}")
    public List<StockItem> getStocksByTraderId(@PathVariable Long id){
        return stockService.getStocksByTraderId(id);
    }

}
