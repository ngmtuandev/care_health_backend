package com.care_health.care_health.repositories;

import com.care_health.care_health.entity.ContentTitle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IContentTitleRepo extends JpaRepository<ContentTitle, UUID> {
}
