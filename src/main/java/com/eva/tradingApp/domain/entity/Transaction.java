package com.eva.tradingApp.domain.entity;

import lombok.*;

import javax.persistence.*;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder @Setter @Getter
public class Transaction {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;

    private String traderName;

    private String stockSymbol;

    private String typeOfTransaction;
    private Double numberOfTransaction;

    private Double InitialPrice;
    private Double lastPrice;

    public Transaction(String traderName, String stockSymbol, String typeOfTransaction, Double numberOfTransaction) {
        this.traderName = traderName;
        this.stockSymbol = stockSymbol;
        this.typeOfTransaction = typeOfTransaction;
        this.numberOfTransaction = numberOfTransaction;
    }

    public Transaction(String traderName, String stockSymbol, String typeOfTransaction, Double numberOfTransaction, Double initialPrice, Double lastPrice) {
        this.traderName = traderName;
        this.stockSymbol = stockSymbol;
        this.typeOfTransaction = typeOfTransaction;
        this.numberOfTransaction = numberOfTransaction;
        InitialPrice = initialPrice;
        this.lastPrice = lastPrice;
    }
}
