package com.care_health.care_health.dtos;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Data
public class ContactFindRoomDTO {

    private String userName;

    private int phone;

    private String district;

    private Double rangePrice;

    private Date timeStart;

}
