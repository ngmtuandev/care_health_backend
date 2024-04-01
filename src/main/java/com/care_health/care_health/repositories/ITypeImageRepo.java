package com.care_health.care_health.repositories;

import com.care_health.care_health.entity.TypeImage;
import com.care_health.care_health.enums.ETypeImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ITypeImageRepo extends JpaRepository<TypeImage, UUID> {

    TypeImage findByName(ETypeImage typeImage);

}
