package com.care_health.care_health.dtos.response.user;

import com.care_health.care_health.entity.Roles;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Data
@Builder
public class UserProfileDTO {

    private UUID id;

    private String userName;

    private String email;

    private boolean isUserStatus;

    private Set<Roles> listRoles = new HashSet<>();

}
