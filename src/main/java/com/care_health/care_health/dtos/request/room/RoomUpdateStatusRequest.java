package com.care_health.care_health.dtos.request.room;

import com.care_health.care_health.enums.EStatusRoom;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class RoomUpdateStatusRequest {

    private EStatusRoom eStatusRoom;

}
