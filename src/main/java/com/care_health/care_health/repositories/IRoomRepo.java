package com.care_health.care_health.repositories;

import com.care_health.care_health.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IRoomRepo extends JpaRepository<Room, UUID> {
}
