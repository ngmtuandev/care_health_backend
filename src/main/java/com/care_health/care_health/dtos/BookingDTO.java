package com.care_health.care_health.dtos;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Data
public class BookingDTO {

    private Boolean statusPayment;

    private Boolean totalPayment;

    private UUID room_id;

    private Number quanlityDay;

    private String userName;

    private Number phoneNumber;

    private String address;

    private String email;

    private LocalDateTime dayEnd;

}
