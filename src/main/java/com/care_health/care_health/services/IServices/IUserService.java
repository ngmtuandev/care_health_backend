package com.care_health.care_health.services.IServices;

import com.care_health.care_health.dtos.request.role.RoleRequest;
import com.care_health.care_health.dtos.request.user.EmailRequest;
import com.care_health.care_health.dtos.request.user.LoginRequest;
import com.care_health.care_health.dtos.request.user.RegisterRequest;
import com.care_health.care_health.dtos.response.user.UserResponse;
import com.care_health.care_health.entity.User;

import java.util.UUID;

public interface IUserService {

    User findByUserName(String userName);

    boolean existsByUserName(String userName);

    boolean existsByEmail(String email);

    User findByEmail(String email);

    UserResponse register(RegisterRequest request);

    UserResponse login(LoginRequest requestLogin);

    UserResponse addRoleForUser(UUID idUser, RoleRequest roleName);

    UserResponse resetPassword(EmailRequest email);

    UserResponse deleteRoleOfUser (UUID idUser, RoleRequest roleName);

    UserResponse getUserProfile(String token);

    UserResponse deleteUser (String username);

    UserResponse getAllUser();


}
