package com.care_health.care_health.controller.privateAPI;

import com.care_health.care_health.constant.ImageRoomConstant;
import com.care_health.care_health.constant.SystemConstant;
import com.care_health.care_health.dtos.response.imageRoom.ImageRoomResponse;
import com.care_health.care_health.services.ImplService.ImageRoomServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(SystemConstant.API + SystemConstant.VERSION_1 + SystemConstant.API_ADMIN + ImageRoomConstant.API_IMAGEROOM)

public class ImageRoomController {

    @Autowired
    ImageRoomServiceImpl imageRoomService;

    //    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(ImageRoomConstant.API_IMAGEROOM_CREATE)
    public ResponseEntity<ImageRoomResponse> createImageForRoom(@RequestParam("files") List<MultipartFile> files, @PathVariable UUID roomId, @PathVariable UUID typeImage) {
        ImageRoomResponse result = imageRoomService.uploadImageRoom(files, roomId, typeImage);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping(ImageRoomConstant.API_IMAGEROOM_DELETE)
    public ResponseEntity<ImageRoomResponse> deleteImageRoom(@PathVariable UUID imageId) {
        ImageRoomResponse result = imageRoomService.deleteImageRoom(imageId);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
