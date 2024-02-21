package com.care_health.care_health.repositories;

import com.care_health.care_health.entity.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ICouponRepo extends JpaRepository<Coupon, UUID> {
}
