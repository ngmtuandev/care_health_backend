package com.care_health.care_health.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "_coupon")
@Data
@Entity
public class Coupon extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "CouponID", nullable = false)
    private UUID id;

    @Column(name = "DayStart", nullable = false)
    private Date dayStart;

    @Column(name = "DayEnd", nullable = false)
    private Date dayEnd;

    @Column(name = "PercentCoupon", nullable = false)
    private int percentCoupon;

    @OneToMany(mappedBy = "coupon")
    private List<Room> rooms;

}
