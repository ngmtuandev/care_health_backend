package com.care_health.care_health.dtos.request.coupon;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CouponCreateRequest {

    private Date dayStart;

    private Date dayEnd;

    private int percentCoupon;

}
