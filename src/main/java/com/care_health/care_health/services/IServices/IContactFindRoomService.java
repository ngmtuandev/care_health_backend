package com.care_health.care_health.services.IServices;

import com.care_health.care_health.dtos.request.contactFindRoom.ContactFindRoomRequest;
import com.care_health.care_health.dtos.response.contactFindRoom.ContactFindRoomResponse;

import java.util.UUID;

public interface IContactFindRoomService {

    ContactFindRoomResponse createNewContactFindRoom(ContactFindRoomRequest contactFindRoomRequest);

    ContactFindRoomResponse getListContactFindRooms();

    ContactFindRoomResponse getItemContactFindRoom(UUID contactFindRoomId);

}
