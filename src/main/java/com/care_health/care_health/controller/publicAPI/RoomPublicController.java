package com.care_health.care_health.controller.publicAPI;

import com.care_health.care_health.constant.RoomConstant;
import com.care_health.care_health.constant.SystemConstant;
import com.care_health.care_health.dtos.response.room.RoomResponse;
import com.care_health.care_health.services.ImplService.RoomServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(SystemConstant.API + SystemConstant.VERSION_1 + SystemConstant.API_PUBLIC + RoomConstant.API_ROOM)
public class RoomPublicController {

    @Autowired
    RoomServiceImpl roomService;

    //    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(RoomConstant.API_GET_ITEM_ROOM)
    public ResponseEntity<RoomResponse> createRoom(@PathVariable UUID roomId) {

        RoomResponse result = roomService.getItemRoomId(roomId);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    //    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(RoomConstant.API_GET_ROOMS)
    public ResponseEntity<RoomResponse> getAllRooms(@RequestParam(defaultValue = "0") Integer page,
                                                    @RequestParam(defaultValue = "10") Integer size) {

        RoomResponse result = roomService.getAllRooms(page, size);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
