package com.care_health.care_health.dtos.request.room;

import com.care_health.care_health.dtos.ConvenientNearAreaDTO;
import com.care_health.care_health.dtos.ImageRoomDTO;
import com.care_health.care_health.enums.EStatusRoom;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Data
public class RoomEditRequest {

    private Double price;

    private int numberPerson;

    private String description;

    private String title;

    private Double stakeMoney;

    private String location;

    private String district;

    private int leaseTerm;

    private ConvenientNearAreaDTO convenientNearArea;

    private List<ImageRoomDTO> imageRooms;

    private List<UUID> facilities;

    private UUID coupon;

    private UUID typeRoom;

    private EStatusRoom statusRoom;

}
