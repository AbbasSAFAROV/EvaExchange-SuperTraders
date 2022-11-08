package com.eva.tradingApp.domain.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder @Getter @Setter
public class StockDto {

    private Long id;
    private String companyName;
    private String stockSymbol;
    private Double initialPrice;
    private Double lastPrice;
    private Double quantity;

}
