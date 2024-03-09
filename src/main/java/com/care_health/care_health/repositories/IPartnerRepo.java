package com.care_health.care_health.repositories;

import com.care_health.care_health.entity.Partner;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IPartnerRepo extends JpaRepository<Partner, UUID> {
}
