package com.care_health.care_health.controller.publicAPI;

import com.care_health.care_health.constant.CouponConstant;
import com.care_health.care_health.constant.SystemConstant;
import com.care_health.care_health.constant.TypeImageConstant;
import com.care_health.care_health.dtos.response.coupon.CouponResponse;
import com.care_health.care_health.dtos.response.facilities.FacilitiesResponse;
import com.care_health.care_health.services.ImplService.CouponServiceImpl;
import com.care_health.care_health.services.ImplService.FacilitiesServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(SystemConstant.API + SystemConstant.VERSION_1 + SystemConstant.API_PUBLIC + CouponConstant.API_COUPON)
public class ConponPublicController {

    @Autowired
    CouponServiceImpl couponService;

    @GetMapping(CouponConstant.API_GET_COUPONS)
    public ResponseEntity<CouponResponse> getAllCoupon() {

        return new ResponseEntity<>(couponService.getAllCoupon(), HttpStatus.OK);
    }

}
