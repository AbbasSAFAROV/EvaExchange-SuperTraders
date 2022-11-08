package com.eva.tradingApp.domain.converter;

import com.eva.tradingApp.domain.dto.StockDto;
import com.eva.tradingApp.domain.dto.TraderDto;
import com.eva.tradingApp.domain.dto.UserDto;
import com.eva.tradingApp.domain.entity.Portfolio;
import com.eva.tradingApp.domain.entity.Stock;
import com.eva.tradingApp.domain.entity.Trader;
import com.eva.tradingApp.domain.entity.User;
import com.eva.tradingApp.domain.request.PortfolioCreateRequest;
import com.eva.tradingApp.domain.request.StockCreateRequest;
import com.eva.tradingApp.domain.request.TraderCreateRequest;
import com.eva.tradingApp.domain.request.UserCreateRequest;
import com.eva.tradingApp.service.StockService;
import com.eva.tradingApp.service.UserService;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.stereotype.Component;

import javax.jws.soap.SOAPBinding;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class Converter {



    // USER
    public User toUserEntity(UserCreateRequest request){
        return new User(request.getNameLastname(),request.getUsername(), request.getPassword());
    }

    public UserDto toUserDto(User user){
        return new UserDto(user.getId(), user.getNameLastname(), user.getUsername(), user.getPassword(), user.getRoles());
    }

    public List<UserDto> toUserDtoList(List<User> users){
        return users.stream().map(this::toUserDto).collect(Collectors.toList());
    }

    // TRADER

    public Trader toTraderEntity(TraderCreateRequest request){
        return new Trader(request.getName());
    }

    public TraderDto toTraderDto(Trader trader){
        return new TraderDto(trader.getId(), trader.getName(),trader.getPortfolio().getId());
    }

    public List<TraderDto> toTraderDtoList(List<Trader> traders){
        return traders.stream().map(this::toTraderDto).collect(Collectors.toList());
    }

    // STOCK
    public Stock toStockEntity(StockCreateRequest request){
        return new Stock(request.getCompanyName(), request.getStockSymbol(), request.getInitialPrice(), request.getQuantity());
    }

    public StockDto toStockDto(Stock stock){
        return new StockDto(stock.getId(), stock.getCompanyName(), stock.getStockSymbol(), stock.getInitialPrice(), stock.getLastPrice(), stock.getQuantity());
    }

    public List<StockDto> toStockDtoList(List<Stock> stocks){
        return stocks.stream().map(this::toStockDto).collect(Collectors.toList());
    }

    // PORTFOLİO

    public Portfolio toPortfolioEntity(PortfolioCreateRequest request){
        return new Portfolio(null,null,null, null, request.getAmount());
    }


}
