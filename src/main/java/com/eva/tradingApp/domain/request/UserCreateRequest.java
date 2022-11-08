package com.eva.tradingApp.domain.request;

import com.eva.tradingApp.domain.entity.Role;
import lombok.*;

import java.util.Collection;


@AllArgsConstructor
@NoArgsConstructor
@Builder @Getter @Setter
public class UserCreateRequest {

    private String nameLastname;
    private String username;
    private String password;
    private Collection<Role> roles;

    public UserCreateRequest(String nameLastname, String username, String password) {
        this.nameLastname = nameLastname;
        this.username = username;
        this.password = password;
    }
}
