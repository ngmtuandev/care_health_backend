package com.care_health.care_health.repositories;

import com.care_health.care_health.entity.Facility;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IFacilitiesRepo extends JpaRepository<Facility, UUID> {
}
