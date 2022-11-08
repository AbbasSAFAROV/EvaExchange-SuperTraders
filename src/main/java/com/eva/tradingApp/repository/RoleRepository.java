package com.eva.tradingApp.repository;

import com.eva.tradingApp.domain.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
