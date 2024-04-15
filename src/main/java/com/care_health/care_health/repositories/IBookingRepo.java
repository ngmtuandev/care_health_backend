package com.care_health.care_health.repositories;

import com.care_health.care_health.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IBookingRepo extends JpaRepository<Booking, UUID> {
}
