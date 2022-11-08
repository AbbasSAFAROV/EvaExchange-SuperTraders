package com.eva.tradingApp.domain.entity;


import lombok.*;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder @Getter @Setter
public class Trader {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Portfolio portfolio;

    public Trader(String name) {
        this.name = name;
    }

    public Trader(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
