package com.care_health.care_health.entity;

import com.care_health.care_health.enums.ETypeImage;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "_contact_find_room")
@Data
@Entity
public class ContactFindRoom extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "UserName", nullable = false)
    private String userName;

    @Column(name = "Phone", nullable = false)
    private int phone;

    @Column(name = "District", nullable = false)
    private String district;

    @Column(name = "RangePrice", nullable = false)
    private Double rangePrice;

    @Column(name = "TimeStart", nullable = false)
    private Date timeStart;

}
