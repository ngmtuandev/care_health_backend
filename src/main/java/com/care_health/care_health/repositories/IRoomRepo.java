package com.care_health.care_health.repositories;

import com.care_health.care_health.dtos.request.room.ConditionFindRoom;
import com.care_health.care_health.entity.Facility;
import com.care_health.care_health.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface IRoomRepo extends JpaRepository<Room, UUID> {

    @Query("SELECT r FROM Room r JOIN FETCH r.facilities f WHERE r.id = :id")
    Room findRoomWithFacilitiesById(@Param("id") UUID roomId);

    @Query("""
            SELECT r FROM Room r
            WHERE r.price BETWEEN :#{#conditionFindRoom.minPrice} AND :#{#conditionFindRoom.maxPrice}
            AND r.numberPerson = :#{#conditionFindRoom.numberPerson}
            AND LOWER(r.district) LIKE LOWER(CONCAT('%', :#{#conditionFindRoom.district}, '%'))
            """)
    List<Room> roomsByCondition(ConditionFindRoom conditionFindRoom);

//    @Query("""
//            UPDATE Room r
//            INNER JOIN Booking b ON r.id = b.room_id
//            SET r.statusRoom = 'EMPTY'
//            WHERE b.dayEnd > NOW()
//            """)
//    List<Room> roomsByCondition(ConditionFindRoom conditionFindRoom);

}
