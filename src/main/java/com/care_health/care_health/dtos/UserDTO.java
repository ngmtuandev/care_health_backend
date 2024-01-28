package com.care_health.care_health.dtos;

import com.care_health.care_health.entity.Roles;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private String userName;

    private String password;

    private String email;

    private boolean isUserStatus;

    private Set<Roles> listRoles = new HashSet<>();
}
