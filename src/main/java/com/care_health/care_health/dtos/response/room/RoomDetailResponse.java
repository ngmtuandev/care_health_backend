package com.care_health.care_health.dtos.response.room;

import com.care_health.care_health.dtos.response.base.BaseResponse;
import com.care_health.care_health.entity.*;
import com.care_health.care_health.enums.EStatusRoom;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
@SuperBuilder(toBuilder = true)
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

    private Set<Facility> facilities;

    private Coupon coupon;

    private TypeRoom typeRoom;

    private EStatusRoom statusRoom;

    private List<String> images;

}
