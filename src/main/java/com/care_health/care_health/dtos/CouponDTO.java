package com.care_health.care_health.dtos;

import com.care_health.care_health.entity.Room;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Data
public class CouponDTO {

    private UUID id;

    private Date dayStart;

    private Date dayEnd;

    private int percentCoupon;

}
