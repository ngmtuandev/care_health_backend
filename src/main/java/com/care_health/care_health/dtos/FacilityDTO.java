package com.care_health.care_health.dtos;

import com.care_health.care_health.entity.Room;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
@Data
public class FacilityDTO {

    private String nameFacility;

    private double surcharge;

    private boolean isNew;

    private Set<Room> rooms;

}
