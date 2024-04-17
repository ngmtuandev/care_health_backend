package com.care_health.care_health.services.ImplService;

import com.care_health.care_health.constant.ResourceBundleConstant;
import com.care_health.care_health.constant.SystemConstant;
import com.care_health.care_health.dtos.request.booking.BookingRequest;
import com.care_health.care_health.dtos.response.booking.BookingResponse;
import com.care_health.care_health.dtos.response.booking.InfoCreateBooking;
import com.care_health.care_health.entity.BookingSessions;
import com.care_health.care_health.entity.Room;
import com.care_health.care_health.enums.EStatusRoom;
import com.care_health.care_health.repositories.IBookingRepo;
import com.care_health.care_health.repositories.IBookingSessions;
import com.care_health.care_health.repositories.IRoomRepo;
import com.care_health.care_health.services.IServices.IBookingService;
import com.care_health.care_health.utils.BaseAmenityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements IBookingService {

    final private IRoomRepo roomRepo;

    final private IBookingRepo bookingRepo;

    final private IBookingSessions bookingSessionsRepo;

    private final BaseAmenityUtil baseAmenityUtil;

    private final Set<UUID> bookingSessionIds = new HashSet<>();

    private String getMessageBundle(String key) {
        return baseAmenityUtil.getMessageBundle(key);
    }

    EStatusRoom statusRoom = EStatusRoom.EMPTY;


    @Override
    public BookingResponse firstBooking(UUID room_id) {

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
        LocalDateTime endTime = startTime.plusSeconds(60);

        BookingSessions createSessionsBooking = new BookingSessions();
        createSessionsBooking.setEndTime(endTime);
        createSessionsBooking.setStartTime(startTime);
        createSessionsBooking.setRoomId(room_id);

        bookingSessionsRepo.save(createSessionsBooking);

//        session.setAttribute("bookingSessionId", createSessionsBooking.getId());


        bookingSessionIds.add(createSessionsBooking.getId());


        return BookingResponse.builder()
                .code(ResourceBundleConstant.BOOKING_07)
                .status(SystemConstant.STATUS_CODE_SUCCESS)
                .data(createSessionsBooking)
                .message(getMessageBundle(ResourceBundleConstant.BOOKING_07))
                .responseTime(baseAmenityUtil.currentTimeSeconds())
                .build();

    }

    @Override
    public BookingResponse createBooking(BookingRequest bookingRequest, UUID idSession) {

        LocalDateTime nowTimeSession = LocalDateTime.now();

        Optional<BookingSessions> bookingSessionsNow = bookingSessionsRepo.findById(idSession);

        if (!bookingSessionsNow.isPresent() || nowTimeSession.isAfter(bookingSessionsNow.get().getEndTime())) {
            return BookingResponse.builder()
                    .code(ResourceBundleConstant.BOOKING_08)
                    .status(SystemConstant.STATUS_CODE_BAD_REQUEST)
                    .message(getMessageBundle(ResourceBundleConstant.BOOKING_08))
                    .responseTime(baseAmenityUtil.currentTimeSeconds())
                    .build();

        }

        Room roomBooking = roomRepo.findById(bookingSessionsNow.get().getRoomId()).get();
        if (!roomBooking.getStatusRoom().equals(EStatusRoom.EMPTY))
        {
            return BookingResponse.builder()
                    .code(ResourceBundleConstant.BOOKING_06)
                    .status(SystemConstant.STATUS_CODE_BAD_REQUEST)
                    .message(getMessageBundle(ResourceBundleConstant.BOOKING_06))
                    .responseTime(baseAmenityUtil.currentTimeSeconds())
                    .build();
        }

        double totalPayMent = roomBooking.getPrice() * bookingRequest.getQuanlityDay().doubleValue();

        InfoCreateBooking infoCreateBooking = new InfoCreateBooking();
        infoCreateBooking.setAddress(bookingRequest.getAddress());
        infoCreateBooking.setEmail(bookingRequest.getEmail());
        infoCreateBooking.setRoom(roomBooking);
        infoCreateBooking.setTotalPayment(totalPayMent);
        infoCreateBooking.setPhoneNumber(bookingRequest.getPhoneNumber());
        infoCreateBooking.setUserName(bookingRequest.getUserName());

        return BookingResponse.builder()
                .code(ResourceBundleConstant.BOOKING_01)
                .status(SystemConstant.STATUS_CODE_SUCCESS)
                .message(getMessageBundle(ResourceBundleConstant.BOOKING_01))
                .responseTime(baseAmenityUtil.currentTimeSeconds())
                .data(infoCreateBooking)
                .build();

    }

    @Override
    public BookingResponse confirmBooking(UUID bookingId) {
        return null;
    }


    @Scheduled(fixedRate = 300)
    public void cleanupExpiredSessions() {
        System.out.println("cleanupExpiredSessions run....");
        LocalDateTime nowTimeSession = LocalDateTime.now();


        Iterator<UUID> iterator = bookingSessionIds.iterator();

        while (iterator.hasNext()) {
            UUID bookingSessionId = iterator.next();

            Optional<BookingSessions> findSessionBookingNow = bookingSessionsRepo.findById(bookingSessionId);

            if (findSessionBookingNow.isPresent()) {
                BookingSessions bookingSessions = findSessionBookingNow.get();
                if (nowTimeSession.isAfter(bookingSessions.getEndTime())) {
                    bookingSessionsRepo.delete(bookingSessions);

                    Room room = roomRepo.findById(bookingSessions.getRoomId()).orElse(null);
                    if (room != null) {
                        room.setStatusRoom(EStatusRoom.EMPTY);
                        roomRepo.save(room);
                    }
                    iterator.remove();
                }
            }
        }

//        if (idBookingInSession != null) {
//            Optional<BookingSessions> bookingSessionOptional = bookingSessionsRepo.findById(idBookingInSession);
//            if (bookingSessionOptional.isPresent()) {
//                BookingSessions bookingSessions = bookingSessionOptional.get();
//                if (bookingSessions.getEndTime().isEqual(LocalDateTime.now())) {
//                    bookingSessionsRepo.delete(bookingSessions);
//                    session.removeAttribute("bookingSessionId");
//                }
//            }
//        }

    }

}
