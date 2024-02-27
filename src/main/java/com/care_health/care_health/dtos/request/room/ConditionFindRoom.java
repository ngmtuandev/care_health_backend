package com.care_health.care_health.dtos.request.room;

import com.care_health.care_health.entity.TypeRoom;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
public class ConditionFindRoom {

    @NotNull
    private Double minPrice;

    @NotNull
    private Double maxPrice;

    @NotNull
    private int numberPerson;

    @NotNull
    private String district;

}
