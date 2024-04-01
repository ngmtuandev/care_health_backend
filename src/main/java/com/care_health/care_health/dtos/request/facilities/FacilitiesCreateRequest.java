package com.care_health.care_health.dtos.request.facilities;

import com.care_health.care_health.entity.Room;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.Set;

@Getter
@Setter
@Data
public class FacilitiesCreateRequest {

    @NotNull
    private String nameFacility;

    @NotNull
    private double surcharge;

    @NotNull
    private boolean isNew;

    private Set<Room> rooms;

}
