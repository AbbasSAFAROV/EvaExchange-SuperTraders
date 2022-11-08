package com.eva.tradingApp.domain.dto;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder @Getter @Setter
public class TraderDto {
    private Long id;
    private String name;
    private Long portfolioId;

    public TraderDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
