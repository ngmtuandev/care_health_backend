package com.care_health.care_health.controller.privateAPI;

import com.care_health.care_health.constant.FacilitiesConstant;
import com.care_health.care_health.constant.SystemConstant;
import com.care_health.care_health.dtos.request.facilities.FacilitiesCreateRequest;
import com.care_health.care_health.dtos.response.facilities.FacilitiesResponse;
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

public class FacilitiesController {

    @Autowired
    FacilitiesServiceImpl facilitiesService;

    //    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(FacilitiesConstant.API_CREATE_FACILITIES)
    public ResponseEntity<FacilitiesResponse> createNewFacilities(@RequestBody FacilitiesCreateRequest facilitiesCreateRequest) {
        System.out.println("start create");
        FacilitiesResponse result = facilitiesService.createFacilities(facilitiesCreateRequest);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
