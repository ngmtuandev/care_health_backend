package com.care_health.care_health.repositories;

import com.care_health.care_health.dtos.UserDTO;
import com.care_health.care_health.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IUsersRepo extends JpaRepository<User, UUID> {

    User findByUserName(String userName);
    boolean existsByUserName(String userName);
    boolean existsByEmail(String email);

    User findByEmail(String email);

}
