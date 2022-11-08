package com.eva.tradingApp.domain.request;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder @Setter @Getter
public class TraderCreateRequest {
    private String name;
}
