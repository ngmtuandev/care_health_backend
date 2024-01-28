package com.care_health.care_health.services.IServices;

import com.care_health.care_health.dtos.UserDTO;
import com.care_health.care_health.dtos.request.user.LoginRequestDTO;
import com.care_health.care_health.dtos.request.user.RegisterRequestDTO;
import com.care_health.care_health.entity.User;

import java.util.List;

public interface IUserService {

    public User findByUserName(String userName);

    boolean existsByUserName(String userName);

    boolean existsByEmail(String email);

    String register(RegisterRequestDTO request);

    String login(LoginRequestDTO requestLogin);

}
