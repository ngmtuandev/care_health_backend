package com.care_health.care_health.dtos.request.booking;

import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class BookingRequest {

    private UUID room_id;

    private Number quanlityDay;

    private String userName;

    private Number phoneNumber;

    private String address;

    private String email;

    private LocalDateTime dayStart;

}
