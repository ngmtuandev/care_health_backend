package com.care_health.care_health.controller.privateAPI;

import com.care_health.care_health.constant.ContactFindRoomConstant;
import com.care_health.care_health.constant.SystemConstant;
import com.care_health.care_health.dtos.response.contactFindRoom.ContactFindRoomResponse;
import com.care_health.care_health.services.ImplService.ContactFindRoomServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(SystemConstant.API + SystemConstant.VERSION_1 + SystemConstant.API_ADMIN + ContactFindRoomConstant.API_CONTACT_FIND_ROOM)

public class ContactFindRoomController {

    @Autowired
    ContactFindRoomServiceImpl contactFindRoomService;

    //    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(ContactFindRoomConstant.API_GET_CONTACT_FIND_ROOMS)
    public ResponseEntity<ContactFindRoomResponse> getAllContactFindRoom() {
        ContactFindRoomResponse result = contactFindRoomService.getListContactFindRooms();

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    //    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(ContactFindRoomConstant.API_GET_CONTACT_FIND_ROOM)
    public ResponseEntity<ContactFindRoomResponse> getItemContactFindRoom(@PathVariable UUID contactFindRoomId) {
        ContactFindRoomResponse result = contactFindRoomService.getItemContactFindRoom(contactFindRoomId);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
