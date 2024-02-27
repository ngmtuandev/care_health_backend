package com.care_health.care_health.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.ColumnDefault;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
@Table(name = "_coupon")
@Data
@Entity
public class Coupon extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "DayStart", nullable = false)
    private Date dayStart;

    @Column(name = "DayEnd", nullable = false)
    private Date dayEnd;

    @Column(name = "PercentCoupon", nullable = false)
    private int percentCoupon;

    @OneToMany(mappedBy = "coupon")
    @JsonIgnore
    private List<Room> rooms;

    @Column(name = "IsDelete", nullable = false)
    @ColumnDefault("false")
    private boolean isDelete;

}
