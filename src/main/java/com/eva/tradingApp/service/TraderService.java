package com.eva.tradingApp.service;


import com.eva.tradingApp.domain.converter.Converter;
import com.eva.tradingApp.domain.dto.TraderDto;
import com.eva.tradingApp.domain.entity.Portfolio;
import com.eva.tradingApp.domain.entity.Trader;
import com.eva.tradingApp.domain.request.TraderCreateRequest;
import com.eva.tradingApp.exception.TraderNotFoundException;
import com.eva.tradingApp.repository.TraderRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TraderService {

    private final TraderRepository repository;
    private final Converter converter;

    public TraderService(TraderRepository repository, Converter converter) {
        this.repository = repository;
        this.converter = converter;
    }

    public List<TraderDto> getAllTraders(){
        return converter.toTraderDtoList(repository.findAll());
    }

    public TraderDto getTraderById(Long id){
        return converter.toTraderDto(findTraderById(id));
    }

    public TraderDto createTrader(TraderCreateRequest request){
        Trader trader = new Trader(request.getName());
        trader.setPortfolio(new Portfolio(0.0,10000.0));
        return converter.toTraderDto(repository.save(trader));
    }

    public TraderDto updateTraderById(TraderCreateRequest request, Long id){
        Trader trader = findTraderById(id);
        Trader updatedTrader = new Trader(trader.getId(), request.getName());
        return converter.toTraderDto(repository.save(updatedTrader));
    }

    public String deleteTraderById(Long id){
        repository.delete(findTraderById(id));
        return "trader deleted with id: "+id;
    }

    public Trader findTraderById(Long id){
        return repository.findById(id).orElseThrow(()->new TraderNotFoundException("trader not found with id: "+id));
    }

}
