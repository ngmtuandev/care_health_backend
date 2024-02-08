package com.care_health.care_health.services.IServices;

import com.care_health.care_health.dtos.UserDTO;
import com.care_health.care_health.dtos.request.role.RoleRequestDTO;
import com.care_health.care_health.dtos.request.user.EmailRequestDTO;
import com.care_health.care_health.dtos.request.user.LoginRequestDTO;
import com.care_health.care_health.dtos.request.user.RegisterRequestDTO;
import com.care_health.care_health.dtos.response.user.UserProfileDTO;
import com.care_health.care_health.dtos.response.user.UserResponse;
import com.care_health.care_health.entity.Roles;
import com.care_health.care_health.entity.User;
import com.care_health.care_health.enums.ERole;

import java.util.List;
import java.util.UUID;

public interface IUserService {

    User findByUserName(String userName);

    boolean existsByUserName(String userName);

    boolean existsByEmail(String email);

    User findByEmail(String email);

    UserResponse register(RegisterRequestDTO request);

    UserResponse login(LoginRequestDTO requestLogin);

    UserResponse addRoleForUser(UUID idUser, RoleRequestDTO roleName);

    UserResponse resetPassword(EmailRequestDTO email);

    UserResponse deleteRoleOfUser (UUID idUser, RoleRequestDTO roleName);

    UserResponse getUserProfile(String token);

    UserResponse deleteUser (String username);

    UserResponse getAllUser();


}
