package com.care_health.care_health.dtos;

import jakarta.persistence.Column;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Data
public class PartnerDTO {

    private UUID id;

    private String namePartner;

    private String Descriptions;


}
