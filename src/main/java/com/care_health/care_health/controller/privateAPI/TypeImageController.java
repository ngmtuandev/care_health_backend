package com.care_health.care_health.controller.privateAPI;

import com.care_health.care_health.constant.SystemConstant;
import com.care_health.care_health.constant.TypeImageConstant;
import com.care_health.care_health.dtos.request.typeImage.TypeImageRequest;
import com.care_health.care_health.dtos.response.TypeImage.TypeImageResponse;
import com.care_health.care_health.services.ImplService.TypeImageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(SystemConstant.API + SystemConstant.VERSION_1 + SystemConstant.API_ADMIN + TypeImageConstant.API_TYPE_IMAGE)
public class TypeImageController {

    @Autowired
    TypeImageServiceImpl typeImageService;

    //    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(TypeImageConstant.API_CREATE_TYPE_IMAGE)
    public ResponseEntity<TypeImageResponse> createTypeImage(@RequestBody TypeImageRequest typeImageRequest) {


        return new ResponseEntity<>(typeImageService.newTypeImage(typeImageRequest), HttpStatus.OK);
    }


}
