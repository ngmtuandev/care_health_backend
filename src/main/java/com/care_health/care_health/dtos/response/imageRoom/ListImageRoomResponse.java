package com.care_health.care_health.dtos.response.imageRoom;

import com.care_health.care_health.dtos.ImageRoomDTO;
import com.care_health.care_health.dtos.response.base.BaseResponse;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
@SuperBuilder(toBuilder = true)
public class ListImageRoomResponse extends BaseResponse {

    private List<ImageRoomDTO> imagesRoom;

}
