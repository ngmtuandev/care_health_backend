package com.care_health.care_health.services.ImplService;

import com.care_health.care_health.constant.ResourceBundleConstant;
import com.care_health.care_health.constant.SystemConstant;
import com.care_health.care_health.dtos.request.role.RoleRequest;
import com.care_health.care_health.dtos.response.role.RoleResponse;
import com.care_health.care_health.entity.Roles;
import com.care_health.care_health.enums.ERole;
import com.care_health.care_health.repositories.IRoleRepo;
import com.care_health.care_health.repositories.IUsersRepo;
import com.care_health.care_health.services.IServices.IRoleService;
import com.care_health.care_health.utils.BaseAmenityUtil;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements IRoleService {

    private final IRoleRepo roleRepo;

    private final IUsersRepo usersRepo;

    private final BaseAmenityUtil baseAmenityUtil;

    public RoleServiceImpl(IRoleRepo roleRepo, IUsersRepo usersRepo, BaseAmenityUtil baseAmenityUtil) {
        this.roleRepo = roleRepo;
        this.usersRepo = usersRepo;
        this.baseAmenityUtil = baseAmenityUtil;
    }

    @Override
    public Optional<Roles> findByRoleName(ERole roleName) {
        return roleRepo.findByRoleName(roleName);
    }

    private String getMessageBundle(String key) {
        return baseAmenityUtil.getMessageBundle(key);
    }

    @Override
    public RoleResponse createRole(RoleRequest role) {

        Optional<Roles> findRole = findByRoleName(role.getRoleName());

        if (findRole.isPresent()) {
            return RoleResponse.builder()
                    .code(ResourceBundleConstant.ROL_002)
                    .status(SystemConstant.STATUS_CODE_BAD_REQUEST)
                    .message(getMessageBundle(ResourceBundleConstant.ROL_002))
                    .responseTime(baseAmenityUtil.currentTimeSeconds())
                    .build();
        }

        Roles newRole = new Roles();
        newRole.setRoleName(role.getRoleName());

        roleRepo.save(newRole);

        return RoleResponse.builder()
                .code(ResourceBundleConstant.ROL_001)
                .status(SystemConstant.STATUS_CODE_SUCCESS)
                .message(getMessageBundle(ResourceBundleConstant.ROL_001))
                .responseTime(baseAmenityUtil.currentTimeSeconds())
                .build();
    }

    @Override
    public RoleResponse getAllRoles() {
        List<Roles> allRole = roleRepo.findAll();
        return RoleResponse.builder()
                .code(ResourceBundleConstant.ROL_003)
                .status(SystemConstant.STATUS_CODE_BAD_REQUEST)
                .message(getMessageBundle(ResourceBundleConstant.ROL_003))
                .data(allRole)
                .responseTime(baseAmenityUtil.currentTimeSeconds())
                .build();
    }

//    @Override
//    public RoleResponse deleteRole(ERole roleName) {
//        int roleDelete = roleRepo.deleteByRoleName(roleName);
//        List<User> usersWithRoleDelete = usersRepo.findByRolesContaining(roleName);
//        for (User user : usersWithRoleDelete) {
//            user.getListRoles().removeIf(role -> role.equals(roleName));
//        }
//        if (roleDelete > 0) {
//            usersRepo.saveAll(usersWithRoleDelete);
//            return RoleResponse.builder()
//                    .code(ResourceBundleConstant.ROL_004)
//                    .status(SystemConstant.STATUS_CODE_SUCCESS)
//                    .message(getMessageBundle(ResourceBundleConstant.ROL_004))
//                    .responseTime(baseAmenityUtil.currentTimeSeconds())
//                    .build();
//        }
//        return RoleResponse.builder()
//                .code(ResourceBundleConstant.ROL_005)
//                .status(SystemConstant.STATUS_CODE_BAD_REQUEST)
//                .message(getMessageBundle(ResourceBundleConstant.ROL_005))
//                .responseTime(baseAmenityUtil.currentTimeSeconds())
//                .build();
//    }
}
