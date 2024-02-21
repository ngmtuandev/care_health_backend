package com.care_health.care_health.services.IServices;

import com.care_health.care_health.dtos.request.room.RoomCreateRequest;
import com.care_health.care_health.dtos.response.room.RoomResponse;

public interface IRoomService {

    RoomResponse createRoom(RoomCreateRequest roomCreateRequest);

}
