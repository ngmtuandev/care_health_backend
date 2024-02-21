package com.care_health.care_health.dtos;

import com.care_health.care_health.entity.*;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Data
@Builder
public class RoomDTO {

    private Double price;

    private int numberPerson;

    private String description;

    private String userName;

    private Double stakeMoney;

    private String location;

    private String district;

    private int leaseTerm;

    private ContactSeeRoom contactSeeRoom;

    private ConvenientNearArea convenientNearArea;

    private List<ImageRoom> imageRooms;

    private Set<Facility> facilities = new HashSet<>();

    private Coupon coupon;

    private TypeRoom typeRoom;

    private StatusRoom statusRoom;

}
