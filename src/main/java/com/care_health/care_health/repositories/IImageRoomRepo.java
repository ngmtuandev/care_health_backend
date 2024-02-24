package com.care_health.care_health.repositories;

import com.care_health.care_health.entity.ImageRoom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IImageRoomRepo extends JpaRepository<ImageRoom, UUID> {
}
