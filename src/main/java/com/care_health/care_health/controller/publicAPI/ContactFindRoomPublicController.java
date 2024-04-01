package com.care_health.care_health.controller.publicAPI;

import com.care_health.care_health.constant.ContactFindRoomConstant;
import com.care_health.care_health.constant.CouponConstant;
import com.care_health.care_health.constant.SystemConstant;
import com.care_health.care_health.dtos.request.contactFindRoom.ContactFindRoomRequest;
import com.care_health.care_health.dtos.response.contactFindRoom.ContactFindRoomResponse;
import com.care_health.care_health.dtos.response.coupon.CouponResponse;
import com.care_health.care_health.services.ImplService.ContactFindRoomServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(SystemConstant.API + SystemConstant.VERSION_1 + SystemConstant.API_PUBLIC + ContactFindRoomConstant.API_CONTACT_FIND_ROOM)
public class ContactFindRoomPublicController {

    @Autowired
    ContactFindRoomServiceImpl contactFindRoomService;

    @PostMapping(ContactFindRoomConstant.API_CREATE_CONTACT_FIND_ROOM)
    public ResponseEntity<ContactFindRoomResponse> getCreateContactFindRoom(@RequestBody ContactFindRoomRequest contactFindRoomRequest) {

        ContactFindRoomResponse result = contactFindRoomService.createNewContactFindRoom(contactFindRoomRequest);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
