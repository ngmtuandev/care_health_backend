package com.care_health.care_health.dtos.response.imageRoom;

import com.care_health.care_health.dtos.ImageRoomDTO;
import com.care_health.care_health.dtos.response.base.BaseResponse;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@Setter
@SuperBuilder(toBuilder = true)
public class ListImageRoomResponse extends BaseResponse {

    private List<ImageRoomDTO> imagesRoom;

}
