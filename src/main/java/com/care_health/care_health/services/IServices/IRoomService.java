package com.care_health.care_health.services.IServices;

import com.care_health.care_health.dtos.request.room.RoomCreateRequest;
import com.care_health.care_health.dtos.response.room.RoomDetailResponse;
import com.care_health.care_health.dtos.response.room.RoomResponse;

import java.util.List;
import java.util.UUID;

public interface IRoomService {

    RoomResponse createRoom(RoomCreateRequest roomCreateRequest);

    RoomResponse getAllRooms(Integer page, Integer size);

    RoomResponse getItemRoomId(UUID roomId);

}
