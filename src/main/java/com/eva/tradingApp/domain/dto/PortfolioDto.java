package com.eva.tradingApp.domain.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder @Getter @Setter
public class PortfolioDto {

    private Long id;
    private String user_id;
    private String stock_id;
    private Double amount;

}
