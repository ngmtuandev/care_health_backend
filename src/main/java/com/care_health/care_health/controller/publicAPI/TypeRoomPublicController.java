package com.care_health.care_health.controller.publicAPI;

import com.care_health.care_health.constant.SystemConstant;
import com.care_health.care_health.constant.TypeRoomConstant;
import com.care_health.care_health.dtos.response.typeRoom.TypeRoomResponse;
import com.care_health.care_health.services.ImplService.TypeRoomServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(SystemConstant.API + SystemConstant.VERSION_1 + SystemConstant.API_PUBLIC + TypeRoomConstant.API_TYPE_ROOM)

public class TypeRoomPublicController {

    @Autowired
    TypeRoomServiceImpl typeRoomService;

    @GetMapping(TypeRoomConstant.API_LIST_TYPE_ROOM)
    public ResponseEntity<TypeRoomResponse> getAllTypeRoom() {

        return new ResponseEntity<>(typeRoomService.getListTypeRoom(), HttpStatus.OK);
    }

}
