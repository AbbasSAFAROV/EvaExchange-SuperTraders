package com.eva.tradingApp.domain.entity;


import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder @Getter @Setter
public class StockItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String stockSymbol;
    private Double initialPrice;
    private Double lastPrice;

    private Double supply;
    private Double quantity;

    public StockItem(String stockSymbol, Double initialPrice, Double lastPrice, Double quantity) {
        this.stockSymbol = stockSymbol;
        this.initialPrice = initialPrice;
        this.lastPrice = lastPrice;
        this.quantity = quantity;
    }

    public StockItem(String stockSymbol, Double initialPrice, Double lastPrice, Double supply, Double quantity) {
        this.stockSymbol = stockSymbol;
        this.initialPrice = initialPrice;
        this.lastPrice = lastPrice;
        this.supply = supply;
        this.quantity = quantity;
    }
}
