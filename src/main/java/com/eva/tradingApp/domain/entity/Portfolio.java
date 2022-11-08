package com.eva.tradingApp.domain.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder @Getter @Setter
public class Portfolio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Trader trader;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name = "portfolios_stockItems",
            joinColumns = @JoinColumn(name = "portfolio_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn( name = "stockItem_id",referencedColumnName = "id")
    )
    private List<StockItem> stockItems;
    private Double amount;
    private Double balance;

    public Portfolio(Double balance) {
        this.balance = balance;
    }

    public Portfolio(Double amount, Double balance) {
        this.amount = amount;
        this.balance = balance;
    }

    public Portfolio(Trader trader, List<StockItem> stockItems, Double amount) {
        this.trader = trader;
        this.stockItems = stockItems;
        this.amount = amount;
    }

    public Portfolio(Long id, Trader trader, Double amount) {
        this.id = id;
        this.trader = trader;
        this.amount = amount;
    }

    public Portfolio(Trader trader, Double amount) {
        this.trader = trader;
        this.amount = amount;
    }

    public Portfolio(List<StockItem> stockItems, Double amount, Double balance) {
        this.stockItems = stockItems;
        this.amount = amount;
        this.balance = balance;
    }
}
