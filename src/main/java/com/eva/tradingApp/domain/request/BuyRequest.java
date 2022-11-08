package com.eva.tradingApp.domain.request;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class BuyRequest {

    private Long traderId;
    private Long stockId;
    private Double amount;

}
