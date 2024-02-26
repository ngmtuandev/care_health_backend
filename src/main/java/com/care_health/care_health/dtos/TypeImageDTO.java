package com.care_health.care_health.dtos;

import com.care_health.care_health.entity.ImageRoom;
import com.care_health.care_health.enums.ETypeImage;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
@Data
public class TypeImageDTO {

    private ETypeImage name;

    private List<ImageRoom> imageRooms;

}
