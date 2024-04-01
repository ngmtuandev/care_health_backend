package com.care_health.care_health.controller.privateAPI;

import com.care_health.care_health.constant.RoomConstant;
import com.care_health.care_health.constant.SystemConstant;
import com.care_health.care_health.dtos.request.room.RoomCreateRequest;
import com.care_health.care_health.dtos.request.room.RoomEditRequest;
import com.care_health.care_health.dtos.request.room.RoomUpdateStatusRequest;
import com.care_health.care_health.dtos.response.room.RoomResponse;
import com.care_health.care_health.enums.EStatusRoom;
import com.care_health.care_health.services.ImplService.RoomServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(SystemConstant.API + SystemConstant.VERSION_1 + SystemConstant.API_ADMIN + RoomConstant.API_ROOM)
public class RoomController {

    @Autowired
    RoomServiceImpl roomService;

//    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(RoomConstant.API_CREATE_ROOM)
    public ResponseEntity<RoomResponse> createRoom(@RequestBody RoomCreateRequest roomCreateRequest) {

        RoomResponse result = roomService.createRoom(roomCreateRequest);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    //    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(RoomConstant.API_UPDATE_STATUS_ROOM)
    public ResponseEntity<RoomResponse> updateStatusRoom(@PathVariable UUID roomId, @PathVariable EStatusRoom statusRoom) {
        System.out.println("roomUpdateStatusRequest : " + statusRoom);
        RoomResponse result = roomService.updateStatusRoom(roomId, statusRoom);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    //    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(RoomConstant.API_UPDATE_ROOM)
    public ResponseEntity<RoomResponse> editRoom(@PathVariable UUID roomId, @RequestBody RoomEditRequest roomEditRequest) {
        RoomResponse result = roomService.editRoom(roomId, roomEditRequest);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
