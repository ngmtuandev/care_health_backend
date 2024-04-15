package com.care_health.care_health.controller.publicAPI;

import com.care_health.care_health.constant.BookingConstant;
import com.care_health.care_health.constant.SystemConstant;
import com.care_health.care_health.dtos.response.booking.BookingResponse;
import com.care_health.care_health.enums.EStatusRoom;
import com.care_health.care_health.services.ImplService.BookingServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.UUID;

@RestController
@RequestMapping(SystemConstant.API + SystemConstant.VERSION_1 + SystemConstant.API_PUBLIC + BookingConstant.API_BOOKING)
public class BookingController {

    @Autowired
    BookingServiceImpl bookingService;

    @PostMapping(BookingConstant.API_FIRST_STEP_BOOKING)
    public ResponseEntity<BookingResponse> firstStepCheckBooking(@PathVariable UUID room_id, HttpSession session) {
        return new ResponseEntity<>(bookingService.firstBooking(room_id, session), HttpStatus.OK);
    }

}
