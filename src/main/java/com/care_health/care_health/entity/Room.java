package com.care_health.care_health.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "_room")
@Data
@Entity
public class Room extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "Price", nullable = false)
    private Double price;

    @Column(name = "NumberPerson", nullable = false)
    private int numberPerson;

    @Column(name = "Description", nullable = false)
    private String description;

    @Column(name = "UserName", nullable = false)
    private String userName;

    @Column(name = "StakeMoney", nullable = false)
    private Double stakeMoney;

    @Column(name = "Location", nullable = false)
    private String location;

    @Column(name = "District", nullable = false)
    private String district;

    @Column(name = "LeaseTerm", nullable = false)
    private int leaseTerm;

    @OneToOne(mappedBy = "room")
    private ConvenientNearArea convenientNearArea;

    @OneToMany(mappedBy = "room")
    private List<ImageRoom> imageRooms;

    @ManyToMany
    @JoinTable(
            name = "room_facility",
            joinColumns = @JoinColumn(name = "room_id"),
            inverseJoinColumns = @JoinColumn(name = "facility_id")
    )
    private Set<Facility> facilities = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "coupon_id")
    private Coupon coupon;

    @ManyToOne
    @JoinColumn(name = "typeRoom_id")
    private TypeRoom typeRoom;

    @ManyToOne
    @JoinColumn(name = "statusRoom_id")
    private StatusRoom statusRoom;

}
