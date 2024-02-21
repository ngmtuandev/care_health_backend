package com.care_health.care_health.dtos;

import com.care_health.care_health.entity.Room;
import com.care_health.care_health.enums.EStatusRoom;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Data
public class StatusRoomDTO {

    private EStatusRoom nameStatus;

    private List<Room> rooms;

}
