package com.eva.tradingApp.domain.request;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder @Getter @Setter
public class StockCreateRequest {

    private String companyName;
    private String stockSymbol;
    private Double initialPrice;
    private Double quantity;

}
