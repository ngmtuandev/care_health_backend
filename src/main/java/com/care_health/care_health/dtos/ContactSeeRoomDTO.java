package com.care_health.care_health.dtos;

import com.care_health.care_health.entity.Room;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Data
public class ContactSeeRoomDTO {

    private String userName;

    private Room room;

    private int phoneNumber;

    private Date timeSee;

}
