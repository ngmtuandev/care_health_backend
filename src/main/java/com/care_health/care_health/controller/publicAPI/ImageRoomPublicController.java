package com.care_health.care_health.controller.publicAPI;

import com.care_health.care_health.constant.ImageRoomConstant;
import com.care_health.care_health.constant.SystemConstant;
import com.care_health.care_health.dtos.response.imageRoom.ListImageRoomResponse;
import com.care_health.care_health.services.ImplService.ImageRoomServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(SystemConstant.API + SystemConstant.VERSION_1 + SystemConstant.API_PUBLIC + ImageRoomConstant.API_IMAGEROOM)

public class ImageRoomPublicController {

    @Autowired
    ImageRoomServiceImpl imageRoomService;

    @GetMapping(ImageRoomConstant.API_IMAGEROOM_GET_ALL)
    public ResponseEntity<ListImageRoomResponse> getImagesOfRoom(@PathVariable UUID roomId) {

        ListImageRoomResponse result = imageRoomService.getListImageOfRoom(roomId);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(ImageRoomConstant.API_IMAGEROOM_GET_TYPE)
    public ResponseEntity<ListImageRoomResponse> getImagesOfRoomForType(@PathVariable UUID roomId, @PathVariable UUID typeImage) {

        ListImageRoomResponse result = imageRoomService.findImageByRoomIdAndTypeImageId(roomId, typeImage);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
