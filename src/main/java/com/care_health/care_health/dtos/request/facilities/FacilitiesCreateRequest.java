package com.care_health.care_health.dtos.request.facilities;

import com.care_health.care_health.entity.Room;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Data
public class FacilitiesCreateRequest {

    private String nameFacility;

    private double surcharge;

    private boolean isNew;

    private Set<Room> rooms;

}
