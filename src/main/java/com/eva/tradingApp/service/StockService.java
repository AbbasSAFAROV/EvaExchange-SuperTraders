package com.eva.tradingApp.service;

import com.eva.tradingApp.domain.converter.Converter;
import com.eva.tradingApp.domain.dto.StockDto;
import com.eva.tradingApp.domain.entity.*;
import com.eva.tradingApp.domain.request.StockCreateRequest;
import com.eva.tradingApp.exception.StockNotFoundException;
import com.eva.tradingApp.repository.StockRepository;
import com.eva.tradingApp.repository.TraderRepository;
import com.eva.tradingApp.repository.UserRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class StockService {

    private final StockRepository stockRepository;
    private final UserService userService;
    private final TraderService traderService;
    private final TraderRepository traderRepository;

    private final TransactionService transactionService;
    private final UserRepository userRepository;
    private final Converter converter;

    public StockService(StockRepository stockRepository, UserService userService, TraderService traderService, TraderRepository traderRepository, TransactionService transactionService, UserRepository userRepository, Converter converter) {
        this.stockRepository = stockRepository;
        this.userService = userService;
        this.traderService = traderService;
        this.traderRepository = traderRepository;
        this.transactionService = transactionService;
        this.userRepository = userRepository;
        this.converter = converter;
    }

    public List<StockDto> getAllStocks(){
        return converter.toStockDtoList(stockRepository.findAll());
    }

    public StockDto getStockById(Long id){
        return converter.toStockDto(findStockById(id));
    }

    public StockDto getStockByStockSymbol(String symbol){
        return converter.toStockDto(findStockBySymbol(symbol));
    }

    public List<StockItem> getStocksByTraderId(Long id){
        Trader trader = traderService.findTraderById(id);
        System.out.println(trader.getPortfolio().getStockItems().stream().collect(Collectors.toList()));

        return trader.getPortfolio().getStockItems().stream().collect(Collectors.toList());
    }

    public StockDto createStock(StockCreateRequest request){
        Boolean checkIsValid = isValid(request);
        if (checkIsValid) {
            request.setStockSymbol(request.getStockSymbol().toUpperCase());
            Stock stock = new Stock(request.getCompanyName(), request.getStockSymbol(), request.getInitialPrice(), request.getQuantity());
            stock.setLastPrice(request.getInitialPrice());
            return converter.toStockDto(stockRepository.save(stock));
        }
        throw new IllegalArgumentException("stock symbol is not valid");
    }

    public StockDto updateStockPrice(Double newPrice, Long id){

        LocalDateTime localDateTime = LocalDateTime.now();
        LocalDateTime updatedTime = localDateTime;

        System.out.println(updatedTime);

        Stock stock = findStockById(id);
        Stock newStock = new Stock(stock.getId(), stock.getCompanyName(),stock.getStockSymbol(), stock.getInitialPrice(), stock.getQuantity());
        newStock.setLastPrice(newPrice);
        return converter.toStockDto(stockRepository.save(newStock));

    }

    public StockDto updateStockById(StockCreateRequest request, Long id){
        Stock existStock = findStockById(id);
        Stock updatedStock = new Stock(existStock.getId(), request.getCompanyName(), request.getStockSymbol(), request.getInitialPrice(), request.getQuantity());
        return converter.toStockDto(stockRepository.save(updatedStock));
    }

    public String deleteStockById(Long id){
        Stock stock = findStockById(id);
        stockRepository.delete(stock);
        return "deleted stock with id:"+id;
    }

    private Stock findStockBySymbol(String symbol){
        return stockRepository.findStockByStockSymbol(symbol).orElseThrow(()-> new StockNotFoundException("Stock not available with symbol: "+symbol));
    }
    public Stock findStockById(Long id){
        return stockRepository.findById(id).orElseThrow(()->new StockNotFoundException("stock not available with id:"+id));
    }

    //                       BUY

    public String  buyStock(Long traderId, Long stockId, Double amount){
        Trader trader = traderService.findTraderById(traderId);
        Stock stock = findStockById(stockId);

        if (stock.getQuantity() >= amount) {

            if (trader.getPortfolio().getBalance()>= stock.getLastPrice()*amount) {
                //stock
                stock.setQuantity(stock.getQuantity()-amount);

                trader.getPortfolio().setBalance(trader.getPortfolio().getBalance() - stock.getLastPrice()*amount);
                StockItem stockItem = new StockItem(stock.getStockSymbol(), stock.getInitialPrice(), stock.getLastPrice(), stock.getQuantity(), amount);

                //trader
                trader.getPortfolio().getStockItems().add(stockItem);

                traderRepository.save(trader);
                stockRepository.save(stock);
                //(String traderName, String stockSymbol, String typeOfTransaction, Double numberOfTransaction, Double initialPrice, Double lastPrice)
                transactionService.createTransaction(new Transaction(trader.getName(),stock.getStockSymbol(),"BUY",amount,stock.getInitialPrice(),stock.getLastPrice()));
                return "Success";
            } else {
                throw new IllegalArgumentException("yetersiz bakiye");
            }

        }else {
            throw new IllegalArgumentException("high amount, not available");
        }

    }

    // SELL
    public String sellStock(Long traderId, Long stockId, Double amount){
        Trader trader = traderService.findTraderById(traderId);
        Stock stock = findStockById(stockId);
        StockItem foundStockItem = null;

        List<StockItem> stockItems = trader.getPortfolio().getStockItems();
        for (StockItem stockItem: stockItems) {
            if (stockItem.getStockSymbol()==stock.getStockSymbol()) {
                foundStockItem = stockItem;
            }
        }

        if (foundStockItem.getQuantity()>=amount) {
            trader.getPortfolio().setBalance(trader.getPortfolio().getBalance() + stock.getLastPrice()*amount);
            foundStockItem.setQuantity(foundStockItem.getQuantity()-amount);
            stock.setQuantity(stock.getQuantity()+amount);

            if (foundStockItem.getQuantity()<=0) {
                trader.getPortfolio().getStockItems().remove(foundStockItem);
                stockRepository.save(stock);
                traderRepository.save(trader);
            }

            traderRepository.save(trader);
            stockRepository.save(stock);
            transactionService.createTransaction(new Transaction(trader.getName(),stock.getStockSymbol(),"SELL",amount,stock.getInitialPrice(),stock.getLastPrice()));

        }

        return "ok";
    }


    private Boolean isValid(StockCreateRequest request){
        if (getAllStocks().contains(request.getStockSymbol())) {
            return false;
        } else if (request.getStockSymbol().length()>3) {
            return false;
        }
        return true;
    }

    private Boolean isStockSymbolPresent(String symbol){
        if (getStockByStockSymbol(symbol)!=null) {
            return true;
        }else {
            return false;
        }
    }

}
