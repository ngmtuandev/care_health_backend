package com.care_health.care_health.dtos.response.jwt;

import lombok.*;

import java.util.List;

@Setter
@Getter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtResponseDTO {

    private String token;

    private String type = "Bearer";

    private String userName;

    private String email;

    private List<String> listRoles;


}
