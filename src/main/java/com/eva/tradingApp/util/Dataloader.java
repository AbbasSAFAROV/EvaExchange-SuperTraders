package com.eva.tradingApp.util;


import com.eva.tradingApp.domain.entity.Role;
import com.eva.tradingApp.domain.request.PortfolioCreateRequest;
import com.eva.tradingApp.domain.request.StockCreateRequest;
import com.eva.tradingApp.domain.request.TraderCreateRequest;
import com.eva.tradingApp.domain.request.UserCreateRequest;
import com.eva.tradingApp.service.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;

@Component
public class Dataloader implements CommandLineRunner {

    private final UserService userService;
    private final RoleService roleService;
    private final PortfolioService portfolioService;
    private final TraderService traderService;
    private final StockService stockService;

    public Dataloader(UserService userService, RoleService roleService, PortfolioService portfolioService, TraderService traderService, StockService stockService) {
        this.userService = userService;
        this.roleService = roleService;
        this.portfolioService = portfolioService;
        this.traderService = traderService;
        this.stockService = stockService;
    }

    @Override
    public void run(String... args) throws Exception {

        //User
        UserCreateRequest admin = new UserCreateRequest("qorya","banduuq","12343", Arrays.asList(new Role("ADMIN")));
        UserCreateRequest trader = new UserCreateRequest("abbas","safarka","12343", Arrays.asList(new Role("ADMIN>")));

        userService.createUser(admin);
        userService.createUser(trader);

        //Stock
        StockCreateRequest btc = new StockCreateRequest("Bitcoin", "BTC",10.99,120.0);
        StockCreateRequest eth = new StockCreateRequest("Ethereum", "ETH",8.99,150.0);
        StockCreateRequest xrp = new StockCreateRequest("Ripple", "XRP",8.99,150.0);
        //StockCreateRequest btg = new StockCreateRequest("Bitcoin Gold", "BTG",80000.99,150.0);

        stockService.createStock(btc);
        stockService.createStock(eth);
        stockService.createStock(xrp);
        //stockService.createStock(btg);

        //Trader
        TraderCreateRequest abdi = new TraderCreateRequest("Ahmet");
        TraderCreateRequest ahmet = new TraderCreateRequest("Abdi");

        traderService.createTrader(abdi);
        traderService.createTrader(ahmet);

        // BUY

        stockService.buyStock(1L,1L,5.9);
        stockService.buyStock(1L,2L,5.8);
        stockService.buyStock(1L,3L,4.5);
        //stockService.buyStock(4L,1L,12.0);

        stockService.buyStock(2L,1L,5.9);
        stockService.buyStock(2L,2L,5.8);
        stockService.buyStock(2L,3L,4.5);
        stockService.sellStock(1L,1L,2.9);

    }
}
