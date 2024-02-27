package com.care_health.care_health.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "_contact_see_room")
@Data
@Entity
public class ContactSeeRoom extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "UserName", nullable = false)
    private String userName;

    private UUID room;

    @Column(name = "PhoneNumber", nullable = false)
    private int phoneNumber;

    @Column(name = "TimeSee", nullable = false)
    private Date timeSee;

    @Column(name = "IsDelete", nullable = false)
    @ColumnDefault("false")
    private boolean isDelete;

}
