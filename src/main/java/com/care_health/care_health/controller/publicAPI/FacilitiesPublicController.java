package com.care_health.care_health.controller.publicAPI;

import com.care_health.care_health.constant.FacilitiesConstant;
import com.care_health.care_health.constant.SystemConstant;
import com.care_health.care_health.constant.TypeImageConstant;
import com.care_health.care_health.dtos.response.TypeImage.TypeImageResponse;
import com.care_health.care_health.dtos.response.facilities.FacilitiesResponse;
import com.care_health.care_health.services.ImplService.FacilitiesServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(SystemConstant.API + SystemConstant.VERSION_1 + SystemConstant.API_PUBLIC + FacilitiesConstant.API_FACILITIES)
public class FacilitiesPublicController {

    @Autowired
    FacilitiesServiceImpl facilitiesService;

    @GetMapping(FacilitiesConstant.API_GET_LIST_FACILITIES)
    public ResponseEntity<FacilitiesResponse> getAllFacilities() {

        FacilitiesResponse result = facilitiesService.getListFacilities();

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
