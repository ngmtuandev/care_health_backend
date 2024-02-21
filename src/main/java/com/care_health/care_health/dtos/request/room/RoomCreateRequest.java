package com.care_health.care_health.dtos.request.room;

import com.care_health.care_health.dtos.*;
import com.care_health.care_health.entity.*;
import com.care_health.care_health.enums.EStatusRoom;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Data
public class RoomCreateRequest {

    private Double price;

    private int numberPerson;

    private String description;

    private String userName;

    private Double stakeMoney;

    private String location;

    private String district;

    private int leaseTerm;

    private ConvenientNearAreaDTO convenientNearArea;

    private List<ImageRoomDTO> imageRooms;

    private List<UUID> facilities;

    private CouponDTO coupon;

    private UUID typeRoom;

    private EStatusRoom statusRoom;

}
