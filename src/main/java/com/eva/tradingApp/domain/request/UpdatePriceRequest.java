package com.eva.tradingApp.domain.request;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder @Getter @Setter
public class UpdatePriceRequest {
    private Double price;
}
