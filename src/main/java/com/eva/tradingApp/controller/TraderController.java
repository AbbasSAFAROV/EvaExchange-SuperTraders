package com.eva.tradingApp.controller;


import com.eva.tradingApp.domain.dto.TraderDto;
import com.eva.tradingApp.domain.request.TraderCreateRequest;
import com.eva.tradingApp.service.TraderService;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/traders")
public class TraderController {
    private final TraderService traderService;

    public TraderController(TraderService traderService) {
        this.traderService = traderService;
    }

    @GetMapping
    public ResponseEntity<List<TraderDto>> getAllTraders(){
        return new ResponseEntity<>(traderService.getAllTraders(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TraderDto> getTraderById(Long id){
        return new ResponseEntity<>(traderService.getTraderById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<TraderDto> createTrader(@RequestBody TraderCreateRequest request){
        return new ResponseEntity<>(traderService.createTrader(request), HttpStatus.CREATED);
    }


}
