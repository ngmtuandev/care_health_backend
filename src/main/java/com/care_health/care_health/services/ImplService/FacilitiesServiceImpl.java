package com.care_health.care_health.services.ImplService;

import com.care_health.care_health.constant.ResourceBundleConstant;
import com.care_health.care_health.constant.SystemConstant;
import com.care_health.care_health.dtos.request.facilities.FacilitiesCreateRequest;
import com.care_health.care_health.dtos.response.facilities.FacilitiesResponse;
import com.care_health.care_health.entity.Facility;
import com.care_health.care_health.repositories.IFacilitiesRepo;
import com.care_health.care_health.services.IServices.IFacilitiesServices;
import com.care_health.care_health.utils.BaseAmenityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FacilitiesServiceImpl implements IFacilitiesServices {

    final private IFacilitiesRepo facilitiesRepo;

    private final BaseAmenityUtil baseAmenityUtil;

    private String getMessageBundle(String key) {
        return baseAmenityUtil.getMessageBundle(key);
    }

    @Override
    public FacilitiesResponse createFacilities(FacilitiesCreateRequest facilitiesCreateRequest) {
        if (facilitiesCreateRequest  != null ) {

            Facility newFacilities = new Facility();
            newFacilities.setNameFacility(facilitiesCreateRequest.getNameFacility());
            newFacilities.setNew(facilitiesCreateRequest.isNew());
            newFacilities.setSurcharge(facilitiesCreateRequest.getSurcharge());

            Facility result = facilitiesRepo.save(newFacilities);

            if (result == null) {
                return FacilitiesResponse.builder()
                        .code(ResourceBundleConstant.FACILITIES_002)
                        .status(SystemConstant.STATUS_CODE_BAD_REQUEST)
                        .message(getMessageBundle(ResourceBundleConstant.FACILITIES_002))
                        .responseTime(baseAmenityUtil.currentTimeSeconds())
                        .build();
            }

            return FacilitiesResponse.builder()
                    .code(ResourceBundleConstant.FACILITIES_001)
                    .status(SystemConstant.STATUS_CODE_BAD_REQUEST)
                    .message(getMessageBundle(ResourceBundleConstant.FACILITIES_001))
                    .data(newFacilities)
                    .responseTime(baseAmenityUtil.currentTimeSeconds())
                    .build();

        }
        return FacilitiesResponse.builder()
                .code(ResourceBundleConstant.FACILITIES_002)
                .status(SystemConstant.STATUS_CODE_BAD_REQUEST)
                .message(getMessageBundle(ResourceBundleConstant.FACILITIES_002))
                .responseTime(baseAmenityUtil.currentTimeSeconds())
                .build();
    }
}
