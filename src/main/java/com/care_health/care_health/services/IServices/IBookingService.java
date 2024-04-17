package com.care_health.care_health.services.IServices;

import com.care_health.care_health.dtos.request.booking.BookingRequest;
import com.care_health.care_health.dtos.response.booking.BookingResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.UUID;

public interface IBookingService {

    BookingResponse firstBooking(UUID room_id);

    BookingResponse createBooking(BookingRequest bookingRequest, UUID idSession);

    BookingResponse confirmBooking(UUID bookingId);

}
