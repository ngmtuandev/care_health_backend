package com.care_health.care_health.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.Date;
import java.util.List;
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
    @OneToMany(mappedBy = "room")
    @JsonIgnore
    private List<ImageRoom> imageRooms;



}
