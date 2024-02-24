package com.care_health.care_health.dtos.response.base;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
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
