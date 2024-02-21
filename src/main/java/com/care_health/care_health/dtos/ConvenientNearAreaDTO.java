package com.care_health.care_health.dtos;

import com.care_health.care_health.entity.Room;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Data
public class ConvenientNearAreaDTO {

    private int distance;

    private Date name;

    private Room room;

}
