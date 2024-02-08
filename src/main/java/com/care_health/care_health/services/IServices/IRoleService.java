package com.care_health.care_health.services.IServices;

import com.care_health.care_health.dtos.request.role.RoleRequestDTO;
import com.care_health.care_health.dtos.response.role.RoleResponse;
import com.care_health.care_health.entity.Roles;
import com.care_health.care_health.enums.ERole;

import javax.management.relation.Role;
import java.util.List;
import java.util.Optional;

public interface IRoleService {
    Optional<Roles> findByRoleName(ERole roleName);

    RoleResponse createRole(RoleRequestDTO role);

    RoleResponse getAllRoles();


}
