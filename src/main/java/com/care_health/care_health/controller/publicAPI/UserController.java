package com.care_health.care_health.controller.publicAPI;

import com.care_health.care_health.constant.SystemConstant;
import com.care_health.care_health.constant.UserConstant;
import com.care_health.care_health.dtos.request.user.EmailRequestDTO;
import com.care_health.care_health.dtos.request.user.LoginRequestDTO;
import com.care_health.care_health.dtos.request.user.RegisterRequestDTO;
import com.care_health.care_health.dtos.response.user.UserProfileDTO;
import com.care_health.care_health.services.ImplService.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(SystemConstant.API + SystemConstant.VERSION_1 + SystemConstant.API_PUBLIC + UserConstant.API_USER)
public class UserController {

    @Autowired
    UserServiceImpl userService;

    @PostMapping(UserConstant.API_REGISTER)
    public String registerUser(@RequestBody RegisterRequestDTO requestDTO) {
        String result =userService.register(requestDTO);
        return result;
    }

    @PostMapping(UserConstant.API_LOGIN)
    public String loginUser(@RequestBody LoginRequestDTO requestDTO) {
        String result =userService.login(requestDTO);
        return result;
    }

    @PostMapping(UserConstant.API_RESET_PASSWORD)
    public String resetPassword(@RequestBody EmailRequestDTO email) {
        System.out.println("login");
        String result =userService.resetPassword(email);
        return result;
    }

    @GetMapping(UserConstant.API_GET_PROFILE)
    public UserProfileDTO getProfile(@RequestHeader("Authorization") String token) {
        System.out.println("get profile");
        System.out.println("String token ->>>>>>>" + token);
        UserProfileDTO result = userService.getUserProfile(token);
        return result;
    }

}
