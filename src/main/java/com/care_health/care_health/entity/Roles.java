package com.care_health.care_health.entity;

import com.care_health.care_health.enums.ERole;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "_roles")
@Data
@Entity
public class Roles extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "RoleID", nullable = false)
    private UUID id;

    @Column(name = "RoleName")
    @Enumerated(EnumType.STRING)
    private ERole roleName;

}
