package com.care_health.care_health.dtos.response.base;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder(toBuilder = true)
public abstract class BaseResponse {
    private String code;

    private Integer status;

    private String message;

    private long responseTime;

    private Object data;
}
