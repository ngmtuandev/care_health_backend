package com.care_health.care_health.dtos;

import com.care_health.care_health.entity.Room;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Data
public class FacilityDTO {

    private int nameFacility;

    private double surcharge;

    private boolean isNew;

    private Set<Room> rooms;

}
