package com.eva.tradingApp.domain.request;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder @Getter @Setter
public class PortfolioCreateRequest {

    private Long traderId;
    private Double amount;

}
