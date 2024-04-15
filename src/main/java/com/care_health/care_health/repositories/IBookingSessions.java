package com.care_health.care_health.repositories;

import com.care_health.care_health.entity.BookingSessions;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IBookingSessions extends JpaRepository<BookingSessions, UUID> {
}
