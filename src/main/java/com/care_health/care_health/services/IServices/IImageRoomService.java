package com.care_health.care_health.services.IServices;

import com.care_health.care_health.dtos.response.imageRoom.ImageRoomResponse;
import com.care_health.care_health.dtos.response.imageRoom.ListImageRoomResponse;
import com.care_health.care_health.entity.ImageRoom;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

public interface IImageRoomService {

    List<ImageRoom> findImageByRoomId(UUID roomId);

    ImageRoomResponse uploadImageRoom(List<MultipartFile> files, UUID roomID, UUID typeImage);

    ListImageRoomResponse getListImageOfRoom(UUID roomID);

    ListImageRoomResponse findImageByRoomIdAndTypeImageId(UUID roomId, UUID typeImageId);


}
