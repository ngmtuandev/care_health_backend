package com.care_health.care_health.repositories;

import com.care_health.care_health.entity.TypeRoom;
import com.care_health.care_health.enums.ETypeRoom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ITypeRoomRepo extends JpaRepository<TypeRoom, UUID> {
    TypeRoom findByName(ETypeRoom name);
}
