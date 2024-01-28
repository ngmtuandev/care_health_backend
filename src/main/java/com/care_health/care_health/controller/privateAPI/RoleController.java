package com.care_health.care_health.controller.privateAPI;

import com.care_health.care_health.dtos.request.role.RoleRequestDTO;
import com.care_health.care_health.dtos.request.user.RegisterRequestDTO;
import com.care_health.care_health.services.ImplService.RoleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/role")
public class RoleController {

    @Autowired
    RoleServiceImpl roleService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public String registerCreateRole(@RequestBody RoleRequestDTO role) {

        String result =roleService.createRole(role);

        return result;
    }

}
