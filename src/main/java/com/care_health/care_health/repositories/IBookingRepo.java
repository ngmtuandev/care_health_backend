package com.care_health.care_health.repositories;

import com.care_health.care_health.entity.Booking;
import com.care_health.care_health.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface IBookingRepo extends JpaRepository<Booking, UUID> {

    @Query("""
        SELECT r
        FROM Booking b
        INNER JOIN Room r ON r.id = b.room_id
        WHERE b.dayEnd < CURRENT_TIMESTAMP
        """)
    List<Room> roomsExpire();

}
