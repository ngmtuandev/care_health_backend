package com.care_health.care_health.services.IServices;

import com.care_health.care_health.dtos.request.typeRoom.TypeRoomCreateRequest;
import com.care_health.care_health.dtos.response.typeRoom.TypeRoomResponse;
import com.care_health.care_health.entity.TypeRoom;
import com.care_health.care_health.enums.ETypeRoom;

public interface ITypeRoomService {

    TypeRoomResponse createTypeRoom(TypeRoomCreateRequest typeRoomCreateRequest);

    TypeRoom findByName(ETypeRoom name);

}
