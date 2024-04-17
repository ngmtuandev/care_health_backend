package com.care_health.care_health.controller.publicAPI;

import com.care_health.care_health.constant.BookingConstant;
import com.care_health.care_health.constant.SystemConstant;
import com.care_health.care_health.dtos.request.booking.BookingRequest;
import com.care_health.care_health.dtos.response.booking.BookingResponse;
import com.care_health.care_health.dtos.response.booking.InfoCreateBooking;
import com.care_health.care_health.services.ImplService.BookingServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.UUID;

@RestController
@RequestMapping(SystemConstant.API + SystemConstant.VERSION_1 + SystemConstant.API_PUBLIC + BookingConstant.API_BOOKING)
public class BookingController {

    @Autowired
    BookingServiceImpl bookingService;

    @PostMapping(BookingConstant.API_FIRST_STEP_BOOKING)
    public ResponseEntity<BookingResponse> firstStepCheckBooking(@PathVariable UUID room_id) {
        return new ResponseEntity<>(bookingService.firstBooking(room_id), HttpStatus.OK);
    }

    @PostMapping(BookingConstant.API_CREATE_BOOKING)
    public ResponseEntity<BookingResponse> createBooking(@RequestBody BookingRequest bookingRequest, @PathVariable UUID session_id) {
        return new ResponseEntity<>(bookingService.createBooking(bookingRequest, session_id), HttpStatus.OK);
    }

    @PostMapping(BookingConstant.API_CONFIRM_BOOKING)
    public ResponseEntity<BookingResponse> confirmBooking(@PathVariable UUID session_id, @RequestBody InfoCreateBooking infoCreateBooking) {
        return new ResponseEntity<>(bookingService.confirmBooking(session_id, infoCreateBooking), HttpStatus.OK);
    }

}
