package com.care_health.care_health.services.IServices;

import com.care_health.care_health.dtos.request.booking.BookingRequest;
import com.care_health.care_health.dtos.response.booking.BookingResponse;

import javax.servlet.http.HttpSession;
import java.util.UUID;

public interface IBookingService {

    BookingResponse firstBooking(UUID room_id, HttpSession session);

    BookingResponse createBooking(BookingRequest bookingRequest);

    BookingResponse confirmBooking(UUID bookingId);

}
