package com.care_health.care_health.services.IServices;

import com.care_health.care_health.dtos.request.room.ConditionFindRoom;
import com.care_health.care_health.dtos.request.room.RoomCreateRequest;
import com.care_health.care_health.dtos.request.room.RoomEditRequest;
import com.care_health.care_health.dtos.request.room.RoomUpdateStatusRequest;
import com.care_health.care_health.dtos.response.imageRoom.ListImageRoomResponse;
import com.care_health.care_health.dtos.response.room.RoomDetailResponse;
import com.care_health.care_health.dtos.response.room.RoomResponse;
import com.care_health.care_health.entity.ImageRoom;
import com.care_health.care_health.enums.EStatusRoom;

import java.util.List;
import java.util.UUID;

public interface IRoomService {

    RoomResponse createRoom(RoomCreateRequest roomCreateRequest);

    RoomResponse getAllRooms(Integer page, Integer size);

    RoomResponse getItemRoomId(UUID roomId);

    RoomResponse findRoomByCondition(ConditionFindRoom conditionFindRoom);

    List<ImageRoom> findImageByRoomId(UUID roomId);

    RoomResponse updateStatusRoom(UUID roomId, EStatusRoom eStatusRoom);

    RoomResponse editRoom(UUID roomEditId, RoomEditRequest roomEditRequest);

}
