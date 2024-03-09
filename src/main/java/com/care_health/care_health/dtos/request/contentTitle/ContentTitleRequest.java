package com.care_health.care_health.dtos.request.contentTitle;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@Data
public class ContentTitleRequest {

    @NotNull
    private String nameContent;

}
