package com.eva.tradingApp.domain.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Collection;


@Entity @Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Builder @Getter @Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nameLastname;
    private String username;
    private String password;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Portfolio portfolio;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "users_role",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id")
    )
    private Collection<Role> roles;

    public User(String nameLastname, String username, String password, Collection<Role> roles) {
        this.nameLastname = nameLastname;
        this.username = username;
        this.password = password;
        this.roles = roles;
    }

    public User(String nameLastname, String username, String password) {
        this.nameLastname = nameLastname;
        this.username = username;
        this.password = password;
    }

    public User(String nameLastname, String username, String password, Portfolio portfolio, Collection<Role> roles) {
        this.nameLastname = nameLastname;
        this.username = username;
        this.password = password;
        this.portfolio = portfolio;
        this.roles = roles;
    }

    public User(String nameLastname, String username, String password, Portfolio portfolio) {
        this.nameLastname = nameLastname;
        this.username = username;
        this.password = password;
        this.portfolio = portfolio;
    }
}
