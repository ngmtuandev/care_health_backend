package com.care_health.care_health.controller.publicAPI;

import com.care_health.care_health.constant.ImageRoomConstant;
import com.care_health.care_health.constant.RoomConstant;
import com.care_health.care_health.constant.SystemConstant;
import com.care_health.care_health.constant.TypeImageConstant;
import com.care_health.care_health.dtos.response.TypeImage.TypeImageResponse;
import com.care_health.care_health.dtos.response.room.RoomResponse;
import com.care_health.care_health.services.ImplService.TypeImageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(SystemConstant.API + SystemConstant.VERSION_1 + SystemConstant.API_PUBLIC + TypeImageConstant.API_TYPE_IMAGE)
public class TypeImagePublicController {

    @Autowired
    TypeImageServiceImpl typeImageService;

    @GetMapping(TypeImageConstant.API_GET_TYPE_IMAGES)
    public ResponseEntity<TypeImageResponse> getAllTypeImages() {

        TypeImageResponse result = typeImageService.getListTypeImage();

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
