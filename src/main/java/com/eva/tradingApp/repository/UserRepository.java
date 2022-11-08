package com.eva.tradingApp.repository;

import com.eva.tradingApp.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findUserByUsername(String name);
}
