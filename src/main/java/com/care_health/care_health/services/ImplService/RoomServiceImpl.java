package com.care_health.care_health.services.ImplService;

import com.care_health.care_health.dtos.request.room.RoomCreateRequest;
import com.care_health.care_health.dtos.response.room.RoomResponse;
import com.care_health.care_health.services.IServices.IRoomService;
import org.springframework.stereotype.Service;

@Service
public class RoomServiceImpl implements IRoomService {
    @Override
    public RoomResponse createRoom(RoomCreateRequest roomCreateRequest) {
        return null;
    }
}
