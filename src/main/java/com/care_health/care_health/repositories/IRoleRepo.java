package com.care_health.care_health.repositories;

import com.care_health.care_health.entity.Roles;
import com.care_health.care_health.enums.ERole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface IRoleRepo extends JpaRepository<Roles, UUID> {
    Optional<Roles> findByRoleName(ERole roleName); // Optional -> user not have role
}
