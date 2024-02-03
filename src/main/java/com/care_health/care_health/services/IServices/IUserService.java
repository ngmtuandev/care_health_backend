package com.care_health.care_health.services.IServices;

import com.care_health.care_health.dtos.UserDTO;
import com.care_health.care_health.dtos.request.role.RoleRequestDTO;
import com.care_health.care_health.dtos.request.user.EmailRequestDTO;
import com.care_health.care_health.dtos.request.user.LoginRequestDTO;
import com.care_health.care_health.dtos.request.user.RegisterRequestDTO;
import com.care_health.care_health.dtos.response.user.UserProfileDTO;
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

    String register(RegisterRequestDTO request);

    String login(LoginRequestDTO requestLogin);

    String addRoleForUser(UUID idUser, RoleRequestDTO roleName);

    String resetPassword(EmailRequestDTO email);

    String deleteRoleOfUser (UUID idUser, RoleRequestDTO roleName);

    UserProfileDTO getUserProfile(String token);

    String deleteUser (String username);


}
