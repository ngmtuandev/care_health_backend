package com.care_health.care_health.dtos.request.contactFindRoom;

import jakarta.persistence.Column;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ContactFindRoomRequest {

    @NotBlank(message = "Username cannot Null or Space")
    private String userName;

    @Size(min = 10, max = 11)
    private String phone;

    @NotNull(message = "District must not null")
    private String district;

    @NotNull(message = "RangePrice must not null")
    private Double rangePrice;

    @NotNull(message = "TimeStart must not null")
    private Date timeStart;

    @NotNull(message = "Descriptions must not null")
    private String descriptions;

}
