package com.care_health.care_health.controller.privateAPI;

import com.care_health.care_health.constant.SystemConstant;
import com.care_health.care_health.constant.TypeRoomConstant;
import com.care_health.care_health.dtos.request.typeRoom.TypeRoomCreateRequest;
import com.care_health.care_health.dtos.response.typeRoom.TypeRoomResponse;
import com.care_health.care_health.services.ImplService.TypeRoomServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(SystemConstant.API + SystemConstant.VERSION_1 + SystemConstant.API_ADMIN + TypeRoomConstant.API_TYPE_ROOM)
public class TypeRoomController {

    @Autowired
    TypeRoomServiceImpl typeRoomService;

//    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(TypeRoomConstant.API_CREATE_TYPE_ROOM)
    public ResponseEntity<TypeRoomResponse> createNewTypeRoom(@RequestBody TypeRoomCreateRequest typeRoomCreateRequest) {

        TypeRoomResponse result = typeRoomService.createTypeRoom(typeRoomCreateRequest);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
