package com.care_health.care_health.services.ImplService;

import com.care_health.care_health.dtos.request.role.RoleRequestDTO;
import com.care_health.care_health.entity.Roles;
import com.care_health.care_health.enums.ERole;
import com.care_health.care_health.repositories.IRoleRepo;
import com.care_health.care_health.services.IServices.IRoleService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements IRoleService {

    private final IRoleRepo roleRepo;

    public RoleServiceImpl(IRoleRepo roleRepo) {
        this.roleRepo = roleRepo;
    }

    @Override
    public Optional<Roles> findByRoleName(ERole roleName) {
        return roleRepo.findByRoleName(roleName);
    }

    @Override
    public String createRole(RoleRequestDTO role) {

        Optional<Roles> findRole = findByRoleName(role.getRoleName());

        if (findRole.isPresent()) {
            return "role da ton tai";
        }

        Roles newRole = new Roles();
        newRole.setRoleName(role.getRoleName());

        roleRepo.save(newRole);

        return "tao role thanh cong";
    }

    @Override
    public List<Roles> getAllRoles() {
        List<Roles> allRole = roleRepo.findAll();
        return allRole;
    }
}
