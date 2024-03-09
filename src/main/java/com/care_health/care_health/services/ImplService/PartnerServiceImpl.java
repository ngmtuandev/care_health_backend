package com.care_health.care_health.services.ImplService;

import com.care_health.care_health.constant.ResourceBundleConstant;
import com.care_health.care_health.constant.SystemConstant;
import com.care_health.care_health.dtos.PartnerDTO;
import com.care_health.care_health.dtos.request.partner.PartnerRequest;
import com.care_health.care_health.dtos.response.partner.PartnerResponse;
import com.care_health.care_health.entity.Partner;
import com.care_health.care_health.repositories.IPartnerRepo;
import com.care_health.care_health.services.IServices.IPartnerService;
import com.care_health.care_health.utils.BaseAmenityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PartnerServiceImpl implements IPartnerService {

    private final IPartnerRepo partnerRepo;

    private final BaseAmenityUtil baseAmenityUtil;

    private String getMessageBundle(String key) {
        return baseAmenityUtil.getMessageBundle(key);
    }
    @Override
    public PartnerResponse createNewPartner(PartnerRequest partnerRequest) {
        try {
            Partner partner = new Partner();
            partner.setNamePartner(partnerRequest.getNamePartner());
            partner.setDescriptions(partnerRequest.getDescriptions());
            partnerRepo.save(partner);
            return  PartnerResponse.builder()
                    .code(ResourceBundleConstant.PARTNER_01)
                    .status(SystemConstant.STATUS_CODE_SUCCESS)
                    .message(getMessageBundle(ResourceBundleConstant.PARTNER_01))
                    .responseTime(baseAmenityUtil.currentTimeSeconds())
                    .build();
        } catch (Exception e) {
            return  PartnerResponse.builder()
                    .code(ResourceBundleConstant.PARTNER_02)
                    .status(SystemConstant.STATUS_CODE_BAD_REQUEST)
                    .message(getMessageBundle(ResourceBundleConstant.PARTNER_02))
                    .responseTime(baseAmenityUtil.currentTimeSeconds())
                    .data(e)
                    .build();
        }
    }

    @Override
    public PartnerResponse getAllPartner() {
        List<Partner> listPartners = partnerRepo.findAll();
        List<PartnerDTO> partnerDTOList = new ArrayList<>();
        listPartners.forEach(item -> {
            PartnerDTO partnerDTO = new PartnerDTO();
            partnerDTO.setNamePartner(item.getNamePartner());
            partnerDTO.setDescriptions(item.getDescriptions());
            partnerDTO.setId(item.getId());

            partnerDTOList.add(partnerDTO);

        });
        if (!partnerDTOList.isEmpty()) {
            return  PartnerResponse.builder()
                    .code(ResourceBundleConstant.PARTNER_03)
                    .status(SystemConstant.STATUS_CODE_SUCCESS)
                    .message(getMessageBundle(ResourceBundleConstant.PARTNER_03))
                    .responseTime(baseAmenityUtil.currentTimeSeconds())
                    .data(partnerDTOList)
                    .build();
        }
        else {
            return  PartnerResponse.builder()
                    .code(ResourceBundleConstant.PARTNER_04)
                    .status(SystemConstant.STATUS_CODE_BAD_REQUEST)
                    .message(getMessageBundle(ResourceBundleConstant.PARTNER_04))
                    .responseTime(baseAmenityUtil.currentTimeSeconds())
                    .build();
        }
    }

    @Override
    public PartnerResponse deletePartner(UUID partnerId) {

        Optional<Partner> findPartner = partnerRepo.findById(partnerId);

        if (findPartner.isPresent()) {

            try {
                partnerRepo.deleteById(partnerId);
                return  PartnerResponse.builder()
                        .code(ResourceBundleConstant.PARTNER_05)
                        .status(SystemConstant.STATUS_CODE_SUCCESS)
                        .message(getMessageBundle(ResourceBundleConstant.PARTNER_05))
                        .responseTime(baseAmenityUtil.currentTimeSeconds())
                        .build();
            }
            catch (Exception e) {
                partnerRepo.deleteById(partnerId);
                return  PartnerResponse.builder()
                        .code(ResourceBundleConstant.PARTNER_06)
                        .status(SystemConstant.STATUS_CODE_SUCCESS)
                        .message(getMessageBundle(ResourceBundleConstant.PARTNER_06))
                        .responseTime(baseAmenityUtil.currentTimeSeconds())
                        .data(e)
                        .build();
            }
        }

        return  PartnerResponse.builder()
                .code(ResourceBundleConstant.PARTNER_06)
                .status(SystemConstant.STATUS_CODE_SUCCESS)
                .message(getMessageBundle(ResourceBundleConstant.PARTNER_06))
                .responseTime(baseAmenityUtil.currentTimeSeconds())
                .build();
    }
}
