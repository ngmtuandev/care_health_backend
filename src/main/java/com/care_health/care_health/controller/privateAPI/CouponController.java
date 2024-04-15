package com.care_health.care_health.controller.privateAPI;

import com.care_health.care_health.constant.CouponConstant;
import com.care_health.care_health.constant.FacilitiesConstant;
import com.care_health.care_health.constant.SystemConstant;
import com.care_health.care_health.dtos.request.coupon.CouponCreateRequest;
import com.care_health.care_health.dtos.request.facilities.FacilitiesCreateRequest;
import com.care_health.care_health.dtos.response.coupon.CouponResponse;
import com.care_health.care_health.dtos.response.facilities.FacilitiesResponse;
import com.care_health.care_health.services.ImplService.CouponServiceImpl;
import com.care_health.care_health.services.ImplService.FacilitiesServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(SystemConstant.API + SystemConstant.VERSION_1 + SystemConstant.API_ADMIN + FacilitiesConstant.API_FACILITIES)

public class CouponController {

    @Autowired
    CouponServiceImpl couponService;

    //    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(CouponConstant.API_CREATE_COUPON)
    public ResponseEntity<CouponResponse> createNewCoupon(@RequestBody CouponCreateRequest couponCreateRequest) {

        return new ResponseEntity<>(couponService.newCoupon(couponCreateRequest), HttpStatus.OK);
    }

}
