package com.care_health.care_health.controller.publicAPI;

import com.care_health.care_health.constant.SystemConstant;
import com.care_health.care_health.constant.UserConstant;
import com.care_health.care_health.dtos.request.user.EmailRequestDTO;
import com.care_health.care_health.dtos.request.user.LoginRequestDTO;
import com.care_health.care_health.dtos.request.user.RegisterRequestDTO;
import com.care_health.care_health.dtos.response.user.UserProfileDTO;
import com.care_health.care_health.dtos.response.user.UserResponse;
import com.care_health.care_health.services.ImplService.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(
        origins = {
                "http://localhost:3000"
        },
        methods = {
                RequestMethod.OPTIONS,
                RequestMethod.GET,
                RequestMethod.PUT,
                RequestMethod.DELETE,
                RequestMethod.POST
        })
@RestController
@RequestMapping(SystemConstant.API + SystemConstant.VERSION_1 + SystemConstant.API_PUBLIC + UserConstant.API_USER)

public class UserController {

    @Autowired
    UserServiceImpl userService;

    @PostMapping(UserConstant.API_REGISTER)
    public ResponseEntity<UserResponse> registerUser(@RequestBody RegisterRequestDTO requestDTO) {
        UserResponse result =userService.register(requestDTO);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping(UserConstant.API_LOGIN)
    public ResponseEntity<UserResponse> loginUser(@RequestBody LoginRequestDTO requestDTO) {
        UserResponse result =userService.login(requestDTO);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping(UserConstant.API_RESET_PASSWORD)
    public ResponseEntity<UserResponse> resetPassword(@RequestBody EmailRequestDTO email) {
        System.out.println("login");
        UserResponse result =userService.resetPassword(email);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(UserConstant.API_GET_PROFILE)
    public ResponseEntity<UserResponse> getProfile(@RequestHeader("Authorization") String token) {
        System.out.println("get profile");
        System.out.println("String token ->>>>>>>" + token);
        UserResponse result = userService.getUserProfile(token);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
