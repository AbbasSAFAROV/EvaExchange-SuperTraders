package com.eva.tradingApp.domain.dto;

import com.eva.tradingApp.domain.entity.Role;
import lombok.*;

import java.util.Collection;

@AllArgsConstructor
@NoArgsConstructor
@Builder @Getter @Setter
public class UserDto {

    private Long id;
    private String nameLastname;
    private String username;
    private String password;
    private Collection<Role> roles;

}
