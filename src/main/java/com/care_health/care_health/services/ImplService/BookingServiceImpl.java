package com.care_health.care_health.services.ImplService;

import com.care_health.care_health.constant.ResourceBundleConstant;
import com.care_health.care_health.constant.SystemConstant;
import com.care_health.care_health.dtos.request.booking.BookingRequest;
import com.care_health.care_health.dtos.response.booking.BookingResponse;
import com.care_health.care_health.entity.BookingSessions;
import com.care_health.care_health.entity.Room;
import com.care_health.care_health.enums.EStatusRoom;
import com.care_health.care_health.enums.ETypeRoom;
import com.care_health.care_health.repositories.IBookingRepo;
import com.care_health.care_health.repositories.IBookingSessions;
import com.care_health.care_health.repositories.IRoomRepo;
import com.care_health.care_health.services.IServices.IBookingService;
import com.care_health.care_health.utils.BaseAmenityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import javax.servlet.http.HttpSession;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements IBookingService {

    final private IRoomRepo roomRepo;

    final private IBookingRepo bookingRepo;

    final private IBookingSessions bookingSessionsRepo;

    private final BaseAmenityUtil baseAmenityUtil;

    private String getMessageBundle(String key) {
        return baseAmenityUtil.getMessageBundle(key);
    }

    EStatusRoom statusRoom = EStatusRoom.EMPTY;
    @Override
    public BookingResponse firstBooking(UUID room_id, HttpSession session) {

        Room findRoomCheck = roomRepo.findById(room_id).orElse(null);
        if (findRoomCheck == null || findRoomCheck.getStatusRoom() != EStatusRoom.EMPTY) {
            return BookingResponse.builder()
                    .code(ResourceBundleConstant.BOOKING_06)
                    .status(SystemConstant.STATUS_CODE_BAD_REQUEST)
                    .message(getMessageBundle(ResourceBundleConstant.BOOKING_06))
                    .responseTime(baseAmenityUtil.currentTimeSeconds())
                    .build();
        }

        LocalDateTime startTime = LocalDateTime.now();
        LocalDateTime endTime = startTime.plusMinutes(10);

        BookingSessions createSessionsBooking = new BookingSessions();
        createSessionsBooking.setEndTime(endTime);
        createSessionsBooking.setStartTime(startTime);
        createSessionsBooking.setRoomId(room_id);

        bookingSessionsRepo.save(createSessionsBooking);

//        session.setAttribute("bookingSessionId", createSessionsBooking.getId());

        return BookingResponse.builder()
                .code(ResourceBundleConstant.BOOKING_07)
                .status(SystemConstant.STATUS_CODE_SUCCESS)
                .data(createSessionsBooking)
                .message(getMessageBundle(ResourceBundleConstant.BOOKING_07))
                .responseTime(baseAmenityUtil.currentTimeSeconds())
                .build();

    }

    @Override
    public BookingResponse createBooking(BookingRequest bookingRequest) {

        return null;
    }

    @Override
    public BookingResponse confirmBooking(UUID bookingId) {
        return null;
    }


//    @Scheduled(fixedRate = 30000)
//    public void cleanupExpiredSessions() {
//        LocalDateTime nowTimeSession = LocalDateTime.now();
//
//        UUID bookingSessionId = (UUID) session.getAttribute("bookingSessionId");
//
//        Optional<BookingSessions> findSessionBookingNow = bookingSessionsRepo.findById(bookingSessionId);
//
//        if (findSessionBookingNow.isPresent()) {
//            BookingSessions bookingSessions = findSessionBookingNow.get();
//            if (bookingSessions.getEndTime().isEqual(nowTimeSession)) {
//                bookingSessionsRepo.delete(bookingSessions);
//            }
//        }
//
//    }

}
