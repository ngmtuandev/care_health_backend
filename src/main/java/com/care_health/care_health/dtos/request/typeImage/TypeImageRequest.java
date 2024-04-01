package com.care_health.care_health.dtos.request.typeImage;

import com.care_health.care_health.enums.ETypeImage;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class TypeImageRequest {

    private ETypeImage name;

}
