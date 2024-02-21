package com.care_health.care_health.dtos;

import com.care_health.care_health.entity.Room;
import com.care_health.care_health.entity.TypeImage;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class ImageRoomDTO {

    private String filePath;

    private Room room;

    private TypeImage typeImage;

}
