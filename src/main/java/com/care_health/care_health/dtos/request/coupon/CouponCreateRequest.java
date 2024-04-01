package com.care_health.care_health.dtos.request.coupon;

import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CouponCreateRequest {

    @NotNull
    private Date dayStart;

    @NotNull
    private Date dayEnd;

    @NotNull
    private int percentCoupon;

}
