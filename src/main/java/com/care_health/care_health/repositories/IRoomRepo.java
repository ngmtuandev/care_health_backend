package com.care_health.care_health.repositories;

import com.care_health.care_health.entity.Facility;
import com.care_health.care_health.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Set;
import java.util.UUID;

public interface IRoomRepo extends JpaRepository<Room, UUID> {

    @Query("SELECT r FROM Room r JOIN FETCH r.facilities f WHERE r.id = :id")
    Room findRoomWithFacilitiesById(@Param("id") UUID roomId);

}
