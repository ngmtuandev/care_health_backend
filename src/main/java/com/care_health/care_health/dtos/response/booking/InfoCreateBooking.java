package com.care_health.care_health.dtos.response.booking;

import com.care_health.care_health.entity.Room;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class InfoCreateBooking {

    private Room room;

    private String userName;

    private Number phoneNumber;

    private String address;

    private String email;

    private Double totalPayment;

}
