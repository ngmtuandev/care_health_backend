package com.care_health.care_health.services.ImplService;

import com.care_health.care_health.constant.ResourceBundleConstant;
import com.care_health.care_health.constant.SystemConstant;
import com.care_health.care_health.dtos.TypeImageDTO;
import com.care_health.care_health.dtos.request.typeImage.TypeImageRequest;
import com.care_health.care_health.dtos.response.TypeImage.TypeImageResponse;
import com.care_health.care_health.entity.TypeImage;
import com.care_health.care_health.enums.ETypeImage;
import com.care_health.care_health.repositories.ITypeImageRepo;
import com.care_health.care_health.services.IServices.ITypeImageService;
import com.care_health.care_health.utils.BaseAmenityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TypeImageServiceImpl implements ITypeImageService {

    final private ITypeImageRepo typeImageRepo;

    private final BaseAmenityUtil baseAmenityUtil;

    private String getMessageBundle(String key) {
        return baseAmenityUtil.getMessageBundle(key);
    }

    @Override
    public TypeImage findByName(ETypeImage typeImage) {
        return typeImageRepo.findByName(typeImage);
    }

    @Override
    public TypeImageResponse newTypeImage(TypeImageRequest typeImageRequest) {

        TypeImage findTypeImage = findByName(typeImageRequest.getName());
        if (findTypeImage == null) {
            TypeImage newTypeImage = new TypeImage();
            newTypeImage.setName(typeImageRequest.getName());
            try {
                typeImageRepo.save(newTypeImage);
                return TypeImageResponse.builder()
                        .code(ResourceBundleConstant.TYPEIMG_001)
                        .status(SystemConstant.STATUS_CODE_SUCCESS)
                        .message(getMessageBundle(ResourceBundleConstant.TYPEIMG_001))
                        .responseTime(baseAmenityUtil.currentTimeSeconds())
                        .data(newTypeImage)
                        .build();
            }
            catch (Exception e) {
                return TypeImageResponse.builder()
                        .code(ResourceBundleConstant.TYPEIMG_002)
                        .status(SystemConstant.STATUS_CODE_BAD_REQUEST)
                        .message(getMessageBundle(ResourceBundleConstant.TYPEIMG_002))
                        .responseTime(baseAmenityUtil.currentTimeSeconds())
                        .build();
            }
        }

        return TypeImageResponse.builder()
                .code(ResourceBundleConstant.TYPEIMG_002)
                .status(SystemConstant.STATUS_CODE_BAD_REQUEST)
                .message(getMessageBundle(ResourceBundleConstant.TYPEIMG_002))
                .responseTime(baseAmenityUtil.currentTimeSeconds())
                .build();
    }

    @Override
    public TypeImageResponse getListTypeImage() {

        List<TypeImage> listTypeImage = typeImageRepo.findAll();
        List<TypeImageDTO> typeImages = new ArrayList<>();

        listTypeImage.stream().forEach(item -> {
            TypeImageDTO typeImageDTO = new TypeImageDTO();
            typeImageDTO.setName(item.getName());
            typeImageDTO.setId(item.getId());
            typeImages.add(typeImageDTO);
        });
        if (listTypeImage.isEmpty()) {
            return TypeImageResponse.builder()
                    .code(ResourceBundleConstant.TYPEIMG_008)
                    .status(SystemConstant.STATUS_CODE_BAD_REQUEST)
                    .message(getMessageBundle(ResourceBundleConstant.TYPEIMG_008))
                    .responseTime(baseAmenityUtil.currentTimeSeconds())
                    .build();
        }

        return TypeImageResponse.builder()
                .code(ResourceBundleConstant.TYPEIMG_004)
                .status(SystemConstant.STATUS_CODE_SUCCESS)
                .message(getMessageBundle(ResourceBundleConstant.TYPEIMG_004))
                .responseTime(baseAmenityUtil.currentTimeSeconds())
                .data(typeImages)
                .build();
    }
}
