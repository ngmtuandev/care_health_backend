package com.care_health.care_health.dtos.request.room;

import com.care_health.care_health.dtos.*;
import com.care_health.care_health.entity.*;
import com.care_health.care_health.enums.EStatusRoom;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Data
public class RoomCreateRequest {

    @NotNull
    private Double price;

    @NotNull
    private int numberPerson;

    private String description;

    @NotNull
    private String title;

    private Double stakeMoney;

    @NotNull
    private String location;

    @NotNull
    private String district;

    @NotNull
    private int leaseTerm;

    private ConvenientNearAreaDTO convenientNearArea;

    private List<ImageRoomDTO> imageRooms;

    private List<UUID> facilities;

    private UUID coupon;

    @NotNull
    private UUID typeRoom;

    private EStatusRoom statusRoom;

}
