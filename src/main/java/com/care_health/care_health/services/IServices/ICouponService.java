package com.care_health.care_health.services.IServices;

import com.care_health.care_health.dtos.request.coupon.CouponCreateRequest;
import com.care_health.care_health.dtos.response.coupon.CouponResponse;

public interface ICouponService {

    CouponResponse newCoupon(CouponCreateRequest couponCreateRequest);

}
