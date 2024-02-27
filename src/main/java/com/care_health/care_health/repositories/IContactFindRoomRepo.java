package com.care_health.care_health.repositories;

import com.care_health.care_health.entity.ContactFindRoom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IContactFindRoomRepo extends JpaRepository<ContactFindRoom, UUID> {
}
