package com.care_health.care_health.entity;

import com.care_health.care_health.dtos.GuestDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;


import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
@Table(name = "_booking")
@Data
@Entity
public class Booking extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "StatusPayment", nullable = false)
    private Boolean statusPayment;

    @Column(name = "TotalPayment", nullable = false)
    private Boolean totalPayment;

    @Column(name = "RoomId", nullable = false)
    private UUID room_id;

    @Column(name = "QuanlityDay", nullable = false)
    private Number quanlityDay;

    @Column(name = "UserName", unique = true, nullable = false)
    private String userName;

    @Column (name = "Phone", nullable = false)
    private Number phoneNumber;

    @Column (name = "Address", nullable = false)
    private String address;

    @Column (name = "Email", nullable = false)
    private String email;

}
