package com.care_health.care_health.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
@Data
public class GuestDTO {

    private String userName;

    private Number phoneNumber;

    private String address;

    private String email;


}
