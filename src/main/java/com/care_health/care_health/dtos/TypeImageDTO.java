package com.care_health.care_health.dtos;

import com.care_health.care_health.entity.ImageRoom;
import com.care_health.care_health.enums.ETypeImage;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Data
public class TypeImageDTO {

    private ETypeImage name;

    private List<ImageRoom> imageRooms;

}
