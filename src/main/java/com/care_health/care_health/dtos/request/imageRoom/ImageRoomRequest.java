package com.care_health.care_health.dtos.request.imageRoom;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Data
public class ImageRoomRequest {

    private String filePath;

    private UUID room;

    private UUID typeImage;

}
