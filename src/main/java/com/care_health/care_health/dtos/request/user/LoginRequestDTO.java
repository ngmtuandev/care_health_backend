package com.care_health.care_health.dtos.request.user;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class LoginRequestDTO {

    private String userName;

    private String password;

}
