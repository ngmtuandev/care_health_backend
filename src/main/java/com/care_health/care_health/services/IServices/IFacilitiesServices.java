package com.care_health.care_health.services.IServices;

import com.care_health.care_health.dtos.request.facilities.FacilitiesCreateRequest;
import com.care_health.care_health.dtos.response.facilities.FacilitiesResponse;

public interface IFacilitiesServices {

    FacilitiesResponse createFacilities (FacilitiesCreateRequest facilitiesCreateRequest);

}
