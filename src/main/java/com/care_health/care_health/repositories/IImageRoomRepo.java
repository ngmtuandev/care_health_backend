package com.care_health.care_health.repositories;

import com.care_health.care_health.entity.ImageRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface IImageRoomRepo extends JpaRepository<ImageRoom, UUID> {

    List<ImageRoom> findByRoomId(UUID roomId);

    @Query("SELECT i FROM ImageRoom i WHERE i.room.id = :roomId AND i.typeImage.id = :typeImageId")
    List<ImageRoom> findImageByRoomIdAndTypeImageId(UUID roomId, UUID typeImageId);


}
