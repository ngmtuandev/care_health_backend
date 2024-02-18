package com.care_health.care_health.controller.privateAPI;

import com.care_health.care_health.constant.SystemConstant;
import com.care_health.care_health.constant.UserConstant;
import com.care_health.care_health.dtos.request.role.RoleRequestDTO;
import com.care_health.care_health.dtos.response.user.UserResponse;
import com.care_health.care_health.entity.Roles;
import com.care_health.care_health.enums.ERole;
import com.care_health.care_health.services.ImplService.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<UserResponse> addRoleForUser(@PathVariable UUID id, @RequestBody RoleRequestDTO roles) {

        UserResponse result = userService.addRoleForUser(id, roles);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(UserConstant.API_DELETE_ROLE)
    public ResponseEntity<UserResponse> deleteRoleOfUser(@PathVariable UUID id, @RequestBody RoleRequestDTO roles) {

        System.out.println("edit delete role");

        UserResponse result = userService.deleteRoleOfUser(id, roles);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

//    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(UserConstant.API_DELETE_USER)
    public ResponseEntity<UserResponse> deleteUser(@PathVariable String username) {
        System.out.println("Delete user");
        UserResponse result = userService.deleteUser(username);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

//    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(UserConstant.API_LIST_USER)
    public ResponseEntity<UserResponse> getAlllUser() {
        System.out.println("get all user");
        UserResponse result = userService.getAllUser();

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    
}
