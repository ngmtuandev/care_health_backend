package com.care_health.care_health.entity;

import com.care_health.care_health.enums.ETypeImage;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.ColumnDefault;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "_contact_find_room")
@Entity
@SuperBuilder(toBuilder = true)
public class ContactFindRoom extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "UserName", nullable = false)
    private String userName;

    @Column(name = "Phone", nullable = false)
    private String phone;

    @Column(name = "District", nullable = false)
    private String district;

    @Column(name = "RangePrice", nullable = false)
    private Double rangePrice;

    @Column(name = "TimeStart", nullable = false)
    private String timeStart;

    @Column(name = "IsDelete", nullable = false)
    @ColumnDefault("false")
    private boolean isDelete;

    @Column(name = "Descriptions", nullable = false)
    private String descriptions;

}
