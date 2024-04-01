package com.care_health.care_health.controller.privateAPI;

import com.care_health.care_health.constant.CouponConstant;
import com.care_health.care_health.constant.FacilitiesConstant;
import com.care_health.care_health.constant.PartnerConstant;
import com.care_health.care_health.constant.SystemConstant;
import com.care_health.care_health.dtos.request.coupon.CouponCreateRequest;
import com.care_health.care_health.dtos.request.partner.PartnerRequest;
import com.care_health.care_health.dtos.response.coupon.CouponResponse;
import com.care_health.care_health.dtos.response.partner.PartnerResponse;
import com.care_health.care_health.services.ImplService.CouponServiceImpl;
import com.care_health.care_health.services.ImplService.PartnerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(SystemConstant.API + SystemConstant.VERSION_1 + SystemConstant.API_ADMIN + PartnerConstant.API_PARTNER)

public class PartnerController {

    @Autowired
    PartnerServiceImpl partnerService;

    //    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(PartnerConstant.API_PARTNER_CREATE)
    public ResponseEntity<PartnerResponse> createNewPartner(@RequestBody PartnerRequest partnerRequest) {
        return new ResponseEntity<>(partnerService.createNewPartner(partnerRequest), HttpStatus.OK);
    }

    //    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(PartnerConstant.API_PARTNER_DELETE)
    public ResponseEntity<PartnerResponse> deletePartnerId(@PathVariable UUID partnerId) {
        return new ResponseEntity<>(partnerService.deletePartner(partnerId), HttpStatus.OK);
    }

    //    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(PartnerConstant.API_PARTNER_GETS)
    public ResponseEntity<PartnerResponse> getPartners() {
        return new ResponseEntity<>(partnerService.getAllPartner(), HttpStatus.OK);
    }

}
