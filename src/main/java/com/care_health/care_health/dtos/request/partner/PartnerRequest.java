package com.care_health.care_health.dtos.request.partner;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@Data
public class PartnerRequest {

    @NotNull
    private String namePartner;

    @NotNull
    private String descriptions;

}
