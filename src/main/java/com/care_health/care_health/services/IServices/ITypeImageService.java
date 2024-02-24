package com.care_health.care_health.services.IServices;

import com.care_health.care_health.dtos.request.typeImage.TypeImageRequest;
import com.care_health.care_health.dtos.response.TypeImage.TypeImageResponse;
import com.care_health.care_health.entity.TypeImage;
import com.care_health.care_health.enums.ETypeImage;

public interface ITypeImageService {

    TypeImage findByName(ETypeImage typeImage);

    TypeImageResponse newTypeImage(TypeImageRequest typeImageRequest);

}
