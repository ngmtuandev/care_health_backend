package com.care_health.care_health.dtos.request.role;

import com.care_health.care_health.enums.ERole;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class RoleRequestDTO {
    private ERole roleName;
}
