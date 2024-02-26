package com.care_health.care_health.dtos.request.room;

import com.care_health.care_health.entity.TypeRoom;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ConditionFindRoom {

    private Double minPrice;
    private Double maxPrice;
    private int numberPerson;
    private String district;

}
