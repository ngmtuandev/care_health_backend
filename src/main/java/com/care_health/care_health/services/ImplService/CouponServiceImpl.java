package com.care_health.care_health.services.ImplService;

import com.care_health.care_health.constant.ResourceBundleConstant;
import com.care_health.care_health.constant.SystemConstant;
import com.care_health.care_health.dtos.CouponDTO;
import com.care_health.care_health.dtos.request.coupon.CouponCreateRequest;
import com.care_health.care_health.dtos.response.coupon.CouponResponse;
import com.care_health.care_health.dtos.response.facilities.FacilitiesResponse;
import com.care_health.care_health.entity.Coupon;
import com.care_health.care_health.repositories.ICouponRepo;
import com.care_health.care_health.services.IServices.ICouponService;
import com.care_health.care_health.utils.BaseAmenityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor

public class CouponServiceImpl implements ICouponService {

    final private ICouponRepo couponRepo;

    private final BaseAmenityUtil baseAmenityUtil;

    private String getMessageBundle(String key) {
        return baseAmenityUtil.getMessageBundle(key);
    }

    @Override
    public CouponResponse newCoupon(CouponCreateRequest couponCreateRequest) {
        Coupon newCoupon = Coupon.builder()
                .percentCoupon(couponCreateRequest.getPercentCoupon())
                .dayEnd(couponCreateRequest.getDayEnd())
                .dayStart(couponCreateRequest.getDayStart())
                .build();

        Coupon result = couponRepo.save(newCoupon);

        if (result != null) {
            return CouponResponse.builder()
                    .code(ResourceBundleConstant.COUPON_001)
                    .status(SystemConstant.STATUS_CODE_SUCCESS)
                    .message(getMessageBundle(ResourceBundleConstant.COUPON_001))
                    .responseTime(baseAmenityUtil.currentTimeSeconds())
                    .data(result)
                    .build();
        }

        return CouponResponse.builder()
                .code(ResourceBundleConstant.COUPON_002)
                .status(SystemConstant.STATUS_CODE_BAD_REQUEST)
                .message(getMessageBundle(ResourceBundleConstant.COUPON_002))
                .responseTime(baseAmenityUtil.currentTimeSeconds())
                .build();
    }

    @Override
    public CouponResponse getAllCoupon() {

        List<Coupon> coupons = couponRepo.findAll();
        List<CouponDTO> couponDTOS = new ArrayList<>();
        coupons.stream().forEach(item -> {
            CouponDTO couponDTO = new CouponDTO();
            couponDTO.setPercentCoupon(item.getPercentCoupon());
            couponDTO.setDayEnd(item.getDayEnd());
            couponDTO.setDayStart(item.getDayStart());
            couponDTO.setId(item.getId());

            couponDTOS.add(couponDTO);

        });

        return CouponResponse.builder()
                .code(ResourceBundleConstant.COUPON_010)
                .status(SystemConstant.STATUS_CODE_SUCCESS)
                .message(getMessageBundle(ResourceBundleConstant.COUPON_010))
                .responseTime(baseAmenityUtil.currentTimeSeconds())
                .data(couponDTOS)
                .build();
    }
}
