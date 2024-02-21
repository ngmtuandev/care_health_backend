package com.care_health.care_health.dtos.request.typeRoom;

import com.care_health.care_health.enums.ETypeRoom;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class TypeRoomCreateRequest {

    private ETypeRoom name;

    private String description;

}
