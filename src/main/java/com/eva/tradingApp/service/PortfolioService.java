package com.eva.tradingApp.service;


import com.eva.tradingApp.domain.entity.Portfolio;
import com.eva.tradingApp.domain.entity.Trader;
import com.eva.tradingApp.domain.request.PortfolioCreateRequest;
import com.eva.tradingApp.domain.request.TraderCreateRequest;
import com.eva.tradingApp.exception.PortfolioNotFoundException;
import com.eva.tradingApp.repository.PortfolioRepository;
import com.eva.tradingApp.repository.TraderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PortfolioService {

    private final PortfolioRepository portfolioRepository;
    private final TraderService traderService;
    private final StockService stockService;
    private final TraderRepository traderRepository;
    private final UserService userService;

    public PortfolioService(PortfolioRepository portfolioRepository, TraderService traderService, StockService stockService, TraderRepository traderRepository, UserService userService) {
        this.portfolioRepository = portfolioRepository;
        this.traderService = traderService;
        this.stockService = stockService;
        this.traderRepository = traderRepository;
        this.userService = userService;
    }

    public List<Portfolio> getAllPortfolios(){
        return portfolioRepository.findAll();
    }

    public Portfolio findPortfolioById(Long id){
        return portfolioRepository.findById(id).orElseThrow(()->new PortfolioNotFoundException("portfolio not found with id: "+id));
    }

    public Portfolio createPortfolio(PortfolioCreateRequest request){
        Trader trader = traderService.findTraderById(request.getTraderId());
        Portfolio portfolio = new Portfolio(trader,request.getAmount());
        return portfolioRepository.save(portfolio);
    }

    public Portfolio updatePortfolioById(PortfolioCreateRequest request, Long id){
        Portfolio portfolio = findPortfolioById(id);
        Portfolio updatedPortfolio = new Portfolio(portfolio.getId(),portfolio.getTrader(),request.getAmount());
        return portfolioRepository.save(updatedPortfolio);
    }

    public String deletePortfolioById(Long id){
        portfolioRepository.delete(findPortfolioById(id));
        return "delete portfolio with id: "+id;
    }

    public Portfolio getStockWithTraderId(Long id){
        Trader trader = traderService.findTraderById(id);
        return trader.getPortfolio();
    }

    public String withdrawWithTraderId(Long id, Double amount){
        Trader trader = traderService.findTraderById(id);
        if (trader.getPortfolio().getBalance()>=amount) {
            trader.getPortfolio().setBalance(trader.getPortfolio().getBalance()-amount);
            traderRepository.save(trader);
            return "Success";
        }else return "you cant withdraw not enough your balance";

    }

    public String  depositWithTraderId(Long id, Double amount){
        Trader trader = traderService.findTraderById(id);
        trader.getPortfolio().setBalance(trader.getPortfolio().getBalance()+amount);
        traderRepository.save(trader);
        return "success";
    }

}
