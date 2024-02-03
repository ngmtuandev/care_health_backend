package com.care_health.care_health.controller.privateAPI;

import com.care_health.care_health.constant.SystemConstant;
import com.care_health.care_health.constant.UserConstant;
import com.care_health.care_health.dtos.request.role.RoleRequestDTO;
import com.care_health.care_health.entity.Roles;
import com.care_health.care_health.enums.ERole;
import com.care_health.care_health.services.ImplService.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(SystemConstant.API + SystemConstant.VERSION_1 + SystemConstant.API_ADMIN + UserConstant.API_USER)
public class UserControllerPrivate {

    @Autowired
    UserServiceImpl userService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(UserConstant.API_ADD_ROLE)
    public String addRoleForUser(@PathVariable UUID id, @RequestBody RoleRequestDTO roles) {

        String result = userService.addRoleForUser(id, roles);

        return result;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(UserConstant.API_DELETE_ROLE)
    public String deleteRoleOfUser(@PathVariable UUID id, @RequestBody RoleRequestDTO roles) {

        String result = userService.deleteRoleOfUser(id, roles);

        return result;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(UserConstant.API_DELETE_USER)
    public String deleteUser(@PathVariable String username) {
        System.out.println("Delete user");
        String result = userService.deleteUser(username);

        return result;
    }
}
