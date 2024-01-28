package com.care_health.care_health.dtos.request.user;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.Set;

@Getter
@Setter
@Data
public class RegisterRequestDTO {

    private String userName;

    private String password;

    private String email;

    private boolean isUserStatus = true;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date created = new Date();

    private Set<String> listRoles;



}
