package com.care_health.care_health.dtos.response.room;

import com.care_health.care_health.dtos.response.base.BaseResponse;
import com.care_health.care_health.entity.*;
import com.care_health.care_health.enums.EStatusRoom;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Data
@Builder
public class RoomDetailResponse extends BaseResponse {

    private UUID id;

    private Double price;

    private int numberPerson;

    private String description;

    private String title;

    private Double stakeMoney;

    private String location;

    private String district;

    private int leaseTerm;

    private ConvenientNearArea convenientNearArea;

    private List<ImageRoom> imageRooms;

    private List<Facility> facilities;

    private Coupon coupon;

    private TypeRoom typeRoom;

    private EStatusRoom statusRoom;

}
