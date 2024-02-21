package com.care_health.care_health.controller.privateAPI;

import com.care_health.care_health.constant.RoleConstant;
import com.care_health.care_health.constant.SystemConstant;
import com.care_health.care_health.dtos.request.role.RoleRequest;
import com.care_health.care_health.dtos.response.role.RoleResponse;
import com.care_health.care_health.services.ImplService.RoleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(SystemConstant.API + SystemConstant.VERSION_1 + SystemConstant.API_ADMIN + RoleConstant.API_ROLES)
public class RoleController {

    @Autowired
    RoleServiceImpl roleService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<RoleResponse> registerCreateRole(@RequestBody RoleRequest role) {

        RoleResponse result =roleService.createRole(role);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

//    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(RoleConstant.API_GET_ROLES)
    public ResponseEntity<RoleResponse> getAllRole() {

        RoleResponse result =roleService.getAllRoles();

        return new ResponseEntity<>(result, HttpStatus.OK);
    }


}
