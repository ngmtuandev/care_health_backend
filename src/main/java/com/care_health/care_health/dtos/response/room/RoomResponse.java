package com.care_health.care_health.dtos.response.room;

import com.care_health.care_health.dtos.response.base.BaseResponse;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
@SuperBuilder(toBuilder = true)
public class RoomResponse extends BaseResponse {

    private int totalPages;

    private String message;

}
