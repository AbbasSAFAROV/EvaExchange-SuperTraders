package com.eva.tradingApp.domain.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Builder @Getter @Setter
public class Stock {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;
    private String companyName;
    private String stockSymbol;
    private Double initialPrice;
    private Double lastPrice;
    private Double quantity;

    public Stock(Long id, String companyName, String stockSymbol, Double initialPrice, Double quantity) {
        this.id = id;
        this.companyName = companyName;
        this.stockSymbol = stockSymbol;
        this.initialPrice = initialPrice;
        this.quantity = quantity;
    }

    public Stock(String companyName, String stockSymbol, Double initialPrice, Double quantity) {
        this.companyName = companyName;
        this.stockSymbol = stockSymbol;
        this.initialPrice = initialPrice;
        this.quantity = quantity;
    }

    public Stock(Long id, String companyName, String stockSymbol, Double initialPrice, Double lastPrice, Double quantity) {
        this.id = id;
        this.companyName = companyName;
        this.stockSymbol = stockSymbol;
        this.initialPrice = initialPrice;
        this.lastPrice = lastPrice;
        this.quantity = quantity;
    }
}
