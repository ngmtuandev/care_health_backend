package com.care_health.care_health.services.IServices;

import com.care_health.care_health.dtos.request.role.RoleRequest;
import com.care_health.care_health.dtos.response.role.RoleResponse;
import com.care_health.care_health.entity.Roles;
import com.care_health.care_health.enums.ERole;

import java.util.Optional;

public interface IRoleService {
    Optional<Roles> findByRoleName(ERole roleName);

    RoleResponse createRole(RoleRequest role);

    RoleResponse getAllRoles();


}
