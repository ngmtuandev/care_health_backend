package com.care_health.care_health.repositories;

import com.care_health.care_health.dtos.UserDTO;
import com.care_health.care_health.entity.Roles;
import com.care_health.care_health.entity.User;
import com.care_health.care_health.enums.ERole;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Repository
public interface IUsersRepo extends JpaRepository<User, UUID> {

    User findByUserName(String userName);
    boolean existsByUserName(String userName);
    boolean existsByEmail(String email);
    User findByEmail(String email);

    /* revoke role of user when delete role */

}
