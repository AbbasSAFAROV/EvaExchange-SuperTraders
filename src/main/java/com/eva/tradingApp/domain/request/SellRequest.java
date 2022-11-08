package com.eva.tradingApp.domain.request;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder @Getter @Setter
public class SellRequest {
    private Long traderId;
    private Long stockId;
    private Double amount;
}
