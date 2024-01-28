package com.care_health.care_health.controller.publicAPI;

import com.care_health.care_health.dtos.request.user.LoginRequestDTO;
import com.care_health.care_health.dtos.request.user.RegisterRequestDTO;
import com.care_health.care_health.services.ImplService.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/auth")
public class UserController {

    @Autowired
    UserServiceImpl userService;

    @PostMapping("/register")
    public String registerUser(@RequestBody RegisterRequestDTO requestDTO) {
        String result =userService.register(requestDTO);
        return result;
    }

    @PostMapping("/login")
    public String loginUser(@RequestBody LoginRequestDTO requestDTO) {
        String result =userService.login(requestDTO);
        return result;
    }

}
